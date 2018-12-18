package com.runescape.cache.anim;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import com.runescape.Client;
import com.runescape.cache.FileArchive;
import com.runescape.io.Buffer;

public final class Animation {

    public static Animation animations[];
    public static int anInt367;
    public int frameCount;
    public int primaryFrames[];
    public int secondaryFrames[];
    public int[] durations;
    public int loopOffset;
    public int interleaveOrder[];
    public boolean stretches;
    public int forcedPriority;
    public int playerOffhand;
    public int playerMainhand;
    public int maximumLoops;
    public int animatingPrecedence;
    public int priority;
    public int replayMode;

    private Animation() {
        loopOffset = -1;
        stretches = false;
        forcedPriority = 5;
        playerOffhand = -1; //Removes shield
        playerMainhand = -1; //Removes weapon
        maximumLoops = 99;
        animatingPrecedence = -1; //Stops character from moving
        priority = -1;
        replayMode = 1;
    }

    public static void init(FileArchive streamLoader) {
        Buffer stream = new Buffer(streamLoader.readFile("seq.dat"));
        int length = stream.readUShort();
        if (animations == null)
            animations = new Animation[length + 5000];
        for (int j = 0; j < length; j++) {
            if (animations[j] == null)
                animations[j] = new Animation();
            animations[j].decode(stream);
        }
        
        System.out.println("Animations Amount: " + length);
        
    }

    public int duration(int i) {
        int j = durations[i];
        if (j == 0) {
            Frame frame = Frame.method531(primaryFrames[i]);
            if (frame != null) {
                j = durations[i] = frame.duration;
            }
        }
        if (j == 0)
            j = 1;
        return j;
    }

    private void decode(Buffer buffer) {
		while(true) {
			final int opcode = buffer.readUByte();

			if (opcode == 0) {
				break;
			} else if (opcode == 1) {
				frameCount = buffer.readUShort();
				primaryFrames = new int[frameCount];
				secondaryFrames = new int[frameCount];
				durations = new int[frameCount];

				for (int i = 0; i < frameCount; i++) {
					durations[i] = buffer.readUShort();
				}

				for (int i = 0; i < frameCount; i++) {
					primaryFrames[i] = buffer.readUShort();
					secondaryFrames[i] = -1;
				}

				for (int i = 0; i < frameCount; i++) {
					primaryFrames[i] += buffer.readUShort() << 16;
				}
			} else if (opcode == 2) {
				loopOffset = buffer.readUShort();
			} else if (opcode == 3) {
				int len = buffer.readUByte();
				interleaveOrder = new int[len + 1];
				for (int i = 0; i < len; i++) {
					interleaveOrder[i] = buffer.readUByte();
				}
				interleaveOrder[len] = 9999999;
			} else if (opcode == 4) {
				stretches = true;
			} else if (opcode == 5) {
				forcedPriority = buffer.readUByte();
			} else if (opcode == 6) {
				playerOffhand = buffer.readUShort();
			} else if (opcode == 7) {
				playerMainhand = buffer.readUShort();
			} else if (opcode == 8) {
				maximumLoops = buffer.readUByte();
			} else if (opcode == 9) {
				animatingPrecedence = buffer.readUByte();
			} else if (opcode == 10) {
				priority = buffer.readUByte();
			} else if (opcode == 11) {
				replayMode = buffer.readUByte();
			} else if (opcode == 12) {
				int len = buffer.readUByte();

				for (int i = 0; i < len; i++) {
					buffer.readUShort();
				}

				for (int i = 0; i < len; i++) {
					buffer.readUShort();
				}
			} else if (opcode == 13) {
				int len = buffer.readUByte();

				for (int i = 0; i < len; i++) {
					buffer.readTriByte();
				}
			}
		}

		if (frameCount == 0) {
			frameCount = 1;
			primaryFrames = new int[1];
			primaryFrames[0] = -1;
			secondaryFrames = new int[1];
			secondaryFrames[0] = -1;
			durations = new int[1];
			durations[0] = -1;
		}

		if (animatingPrecedence == -1) {
			animatingPrecedence = (interleaveOrder == null) ? 0 : 2;
		}

		if (priority == -1) {
			priority = (interleaveOrder == null) ? 0 : 2;
		}
	}
    
    
    /**
     * Dumps animations
     */
	public static void dumpValues(int startLength, int endLength) {
		System.out.println("Dumping Animations..");
		String[] variablesNames = new String[] { "frameCount", "frameIDs", "frameIDs2", "delays", "loopDelay",
				"animationFlowControl", "oneSquareAnimation", "forcedPriority", "leftHandItem", "rightHandItem",
				"frameStep", "resetWhenWalk", "priority", "delayType" };
		File f = new File("anims.txt");
		try {
			f.createNewFile();
			BufferedWriter bf = new BufferedWriter(new FileWriter(f));
			for (int j = startLength; j <= endLength; j++) {
				if(animations[j] != null){
					String frameCount = animations[j].frameCount + "";
					String frameIDs = Arrays.toString(animations[j].primaryFrames);
					String frameIDs2 = Arrays.toString(animations[j].secondaryFrames);
					String delays = Arrays.toString(animations[j].durations);
					String loopDelay = animations[j].loopOffset + "";
					String animationFlowControl = Arrays.toString(animations[j].interleaveOrder);
					String oneSquareAnimation = animations[j].stretches + "";
					String forcedPriority = animations[j].forcedPriority + "";
					String leftHandItem = animations[j].playerOffhand + "";
					String rightHandItem = animations[j].playerMainhand + "";
					String frameStep = animations[j].maximumLoops + "";
					String resetWhenWalk = animations[j].animatingPrecedence + "";
					String priority = animations[j].priority + "";
					String delayType = animations[j].replayMode + "";
	
					String[] variables = new String[] { frameCount, frameIDs, frameIDs2, delays, loopDelay,
							animationFlowControl, oneSquareAnimation, forcedPriority, leftHandItem, rightHandItem,
							frameStep, resetWhenWalk, priority, delayType };
					bf.write("if (j == " + j + ") {\n");
					for (int k = 0; k < variables.length; k++) {
						bf.write("	animations[" + j + "]." + variablesNames[k] + " = " + variables[k] + ";\n");
					}
					bf.write("}\n\n");
				}
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Dumping Complete!");
	}

}