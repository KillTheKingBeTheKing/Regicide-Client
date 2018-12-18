package com.runescape.cache.anim;

import com.runescape.Client;
import com.runescape.cache.FileArchive;
import com.runescape.collection.ReferenceCache;
import com.runescape.entity.model.Model;
import com.runescape.io.Buffer;

public final class Graphic {

	public static void init(FileArchive archive) {
		Buffer buffer = new Buffer(archive.readFile("spotanim.dat"));
		int length = buffer.readUShort();

		if (cache == null) {
			cache = new Graphic[length];
		}

		for (int i = 0; i < length; i++) {
			if (cache[i] == null) {
				cache[i] = new Graphic();
			}
			cache[i].id = i;
			cache[i].decode(buffer);
		}

		System.out.println("Graphic Amount: " + length);

	}





	private void decode(Buffer buffer) {
		while(true) {
			final int opcode = buffer.readUByte();

			if (opcode == 0) {
				return;
			} else if (opcode == 1) {
				modelId = buffer.readUShort();
			} else if (opcode == 2) {
				animationId = buffer.readUShort();

				if (Animation.animations != null) {
					aAnimation_407 = Animation.animations[animationId];
				}
			} else if (opcode == 4) {
				resizeX = buffer.readUShort();
			} else if (opcode == 5) {
				resizeY = buffer.readUShort();
			} else if (opcode == 6) {
				rotation = buffer.readUShort();
			} else if (opcode == 7) {
				ambience = buffer.readUByte();
			} else if (opcode == 8) {
				contrast = buffer.readUByte();
			} else if (opcode == 40) {
				final int len = buffer.readUByte();
				colorToFind = new short[len];
				colorToReplace = new short[len];
				for (int i = 0; i < len; i++) {
					colorToFind[i] = (short) buffer.readUShort();
					colorToReplace[i] = (short) buffer.readUShort();
				}
			} else if (opcode == 41) {
				final int len = buffer.readUByte();
				textureToFind = new short[len];
				textureToReplace = new short[len];
				for (int i = 0; i < len; i++) {
					textureToFind[i] = (short) buffer.readUShort();
					textureToReplace[i] = (short) buffer.readUShort();
				}
			} else {
				System.out.println("gfx invalid opcode: " + opcode);
			}
		}
	}

	public Model getModel() {
		Model model = (Model) models.get(id);
		if (model != null)
			return model;
		model = Model.getModel(modelId);
		if (model == null)
			return null;

		// osrs new way of recoloring
		if (colorToFind != null) {
			for (int i = 0; i < colorToReplace.length; i++) {
				model.recolor(colorToFind[i], colorToReplace[i]);
			}
		}



		models.put(model, id);
		return model;
	}

	private Graphic() {
		animationId = -1;
		resizeX = 128;
		resizeY = 128;
	}

	public static Graphic cache[];
	private int id;
	public int modelId;
	private int animationId;
	public Animation aAnimation_407;
	private short[] colorToFind;
	private short[] colorToReplace;
	private short[] textureToFind;
	private short[] textureToReplace;
	public int resizeX;
	public int resizeY;
	public int rotation;
	public int ambience;
	public int contrast;
	public static ReferenceCache models = new ReferenceCache(30);

}