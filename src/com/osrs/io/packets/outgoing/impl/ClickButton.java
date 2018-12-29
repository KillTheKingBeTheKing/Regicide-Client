package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class ClickButton implements OutgoingPacket {

    private int buttonId;

    public ClickButton(int button) {
        this.buttonId = button;
    }

    @Override
    public PacketBuilder create() {
    	return new PacketBuilder(185).putInt(buttonId);
    }

}
