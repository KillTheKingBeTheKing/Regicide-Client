package com.osrs.cache.anim;

import com.osrs.cache.FileArchive;
import com.osrs.collection.ReferenceCache;
import com.osrs.entity.model.Model;
import com.osrs.io.Buffer;

public final class Graphic {

    public static Graphic cache[];
    public static ReferenceCache models = new ReferenceCache(30);
    private final int[] originalModelColours;
    private final int[] modifiedModelColours;
    public Animation animationSequence;
    public int resizeXY;
    public int resizeZ;
    public int rotation;
    public int modelBrightness;
    public int modelShadow;
    private int anInt404;
    private int modelId;
    private int animationId;

    private Graphic() {
        animationId = -1;
        originalModelColours = new int[6];
        modifiedModelColours = new int[6];
        resizeXY = 128;
        resizeZ = 128;
    }

    public static void init(FileArchive streamLoader) {
        Buffer stream = new Buffer(streamLoader.readFile("spotanim.dat"));
        int length = stream.readUShort();
        if (cache == null)
            cache = new Graphic[length + 1];
        for (int index = 0; index < length; index++) {
            if (cache[index] == null)
                cache[index] = new Graphic();
            cache[index].anInt404 = index;
            cache[index].readValues(stream);
        }
        System.err.println("[OSRS] Loaded: " + length + " graphics");
    }

    public void readValues(Buffer stream) {
        do {
            int opcode = stream.readUnsignedByte();
            if (opcode == 0)
                return;
            if (opcode == 1)
                modelId = stream.readUShort();
            else if (opcode == 2) {
                animationId = stream.readUShort();
                if (Animation.animations != null)
                    animationSequence = Animation.animations[animationId];
            } else if (opcode == 4)
                resizeXY = stream.readUShort();
            else if (opcode == 5)
                resizeZ = stream.readUShort();
            else if (opcode == 6)
                rotation = stream.readUShort();
            else if (opcode == 7)
                modelBrightness = stream.readUShort();
            else if (opcode == 8)
                modelShadow = stream.readUShort();
            else if (opcode == 40) {
                int j = stream.readUnsignedByte();
                for (int k = 0; k < j; k++) {
                    originalModelColours[k] = stream.readUShort();
                    modifiedModelColours[k] = stream.readUShort();
                }
            } else
                System.out.println("Error unrecognised spotanim config code: "
                        + opcode);
        } while (true);
    }

    public Model getModel() {
        Model model = (Model) models.get(anInt404);
        if (model != null)
            return model;
        model = Model.getModel(modelId);
        if (model == null)
            return null;
        for (int i = 0; i < 6; i++)
            if (originalModelColours[0] != 0)
                model.recolor(originalModelColours[i], modifiedModelColours[i]);

        models.put(model, anInt404);
        return model;
    }
}
