package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class AddIgnore implements OutgoingPacket {

	private long friend;

	public AddIgnore(long ignore) {
		this.friend = ignore;
	}

	@Override
	public PacketBuilder create() {
		return new PacketBuilder(133).putLong(friend);
	}

}
