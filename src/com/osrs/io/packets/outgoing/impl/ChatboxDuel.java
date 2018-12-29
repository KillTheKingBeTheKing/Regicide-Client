package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class ChatboxDuel implements OutgoingPacket {

    int plr;

    public ChatboxDuel(int plr) {
        this.plr = plr;
    }

    @Override
    public PacketBuilder create() {
    	return new PacketBuilder(128).putShort(plr);
    }
}
