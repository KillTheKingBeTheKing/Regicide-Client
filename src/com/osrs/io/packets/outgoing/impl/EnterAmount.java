package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class EnterAmount implements OutgoingPacket {

    private int amount;

    public EnterAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public PacketBuilder create() {
    	return new PacketBuilder(208).putInt(amount);
    }

}
