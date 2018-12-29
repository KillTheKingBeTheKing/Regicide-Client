package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class ChatSettings implements OutgoingPacket {

    int publicMode;
    int privateMode;
    int tradeMode;

    public ChatSettings(int publicMode, int privateMode, int tradeMode) {
        this.publicMode = publicMode;
        this.privateMode = privateMode;
        this.tradeMode = tradeMode;
    }

    @Override
    public PacketBuilder create() {
    	PacketBuilder buf = new PacketBuilder(95);
        buf.putByte(publicMode);
        buf.putByte(privateMode);
        buf.putByte(tradeMode);
        return buf;
    }


}
