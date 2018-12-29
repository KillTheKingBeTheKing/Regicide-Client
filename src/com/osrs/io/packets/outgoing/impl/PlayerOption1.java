package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class PlayerOption1 implements OutgoingPacket {

    int nodeId;

    public PlayerOption1(int nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public PacketBuilder create() {
    	PacketBuilder buf = new PacketBuilder(128);
        buf.putShort(nodeId);
        return buf;
    }
}
