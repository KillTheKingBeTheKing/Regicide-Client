package com.osrs.net.requester;

import com.osrs.collection.Cacheable;

public final class Resource extends Cacheable {

    public int dataType;
    public byte buffer[];
    public int ID;
    boolean incomplete;
    int loopCycle;

    public Resource() {
        incomplete = true;
    }
}
