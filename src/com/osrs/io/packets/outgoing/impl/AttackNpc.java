package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class AttackNpc implements OutgoingPacket {

    int nodeId;

    public AttackNpc(int nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public PacketBuilder create() {
    	return new PacketBuilder(72).putUnsignedWordA(nodeId);
    }
}
