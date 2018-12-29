package com.osrs.io.packets.outgoing.impl;

import com.osrs.io.packets.outgoing.OutgoingPacket;
import com.osrs.io.packets.outgoing.PacketBuilder;

public class DeleteFriend implements OutgoingPacket {

    private long friend;

    public DeleteFriend(long friend) {
        this.friend = friend;
    }

    @Override
    public PacketBuilder create() {
    	return new PacketBuilder(215).putLong(friend);
    }


}
