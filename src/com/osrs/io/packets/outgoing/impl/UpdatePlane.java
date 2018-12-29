package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class UpdatePlane implements OutgoingPacket {

    int plane;

    public UpdatePlane(int plane) {
        this.plane = plane;
    }

    @Override
    public PacketBuilder create() {
    	PacketBuilder buf = new PacketBuilder(229);
        buf.putByte(plane);
        return buf;
    }
}
