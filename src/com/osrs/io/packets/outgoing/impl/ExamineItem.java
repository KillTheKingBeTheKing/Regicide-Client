package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class ExamineItem implements OutgoingPacket {

	int nodeId;
	int interfaceId;

	public ExamineItem(int nodeId, int interfaceId) {
		this.nodeId = nodeId;
		this.interfaceId = interfaceId;
	}

	@Override
	public PacketBuilder create() {
		return new PacketBuilder(2).putShort(nodeId).putInt(interfaceId);
	}
}
