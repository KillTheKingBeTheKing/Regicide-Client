package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class Command implements OutgoingPacket {

	private String cmd;

	public Command(String cmd) {
		this.cmd = cmd;
	}

	@Override
	public PacketBuilder create() {
		return new PacketBuilder(103).putString(cmd);
	}

}
