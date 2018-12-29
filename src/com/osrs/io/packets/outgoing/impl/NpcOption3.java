package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class NpcOption3 implements OutgoingPacket {

    int nodeId;

    public NpcOption3(int nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public PacketBuilder create() {
    	PacketBuilder buf = new PacketBuilder(21);
        buf.putShort(nodeId);
        return buf;
    }
}
