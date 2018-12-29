package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class NextDialogue implements OutgoingPacket {

    int interfaceId;

    public NextDialogue(int interfaceId) {
        this.interfaceId = interfaceId;
    }

    @Override
    public PacketBuilder create() {
    	PacketBuilder buf = new PacketBuilder(40);
        buf.putShort(interfaceId);
        return buf;
    }


}
