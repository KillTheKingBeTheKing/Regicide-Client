package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class ExamineNpc implements OutgoingPacket {

    int nodeId;

    public ExamineNpc(int nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public PacketBuilder create() {
    	return new PacketBuilder(6).putShort(nodeId);
    }
}
