package com.osrs.scene.object;

import com.osrs.collection.Linkable;

public final class SpawnedObject extends Linkable {

    public int id;
    public int orientation;
    public int type;
    public int getLongetivity;
    public int plane;
    public int group;
    public int x;
    public int y;
    public int getPreviousId;
    public int previousOrientation;
    public int previousType;
    public int delay;

    public SpawnedObject() {
        getLongetivity = -1;
    }
}
