package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class FinalizedRegionChange implements OutgoingPacket {

	@Override
    public PacketBuilder create() {
		return new PacketBuilder(121);
    }


}
