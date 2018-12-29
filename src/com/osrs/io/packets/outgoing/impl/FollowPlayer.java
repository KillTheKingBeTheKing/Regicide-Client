package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class FollowPlayer implements OutgoingPacket {

    int nodeId;

    public FollowPlayer(int nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public PacketBuilder create() {
    	return new PacketBuilder(73).putUnsignedWordBigEndian(nodeId);
    }
}
