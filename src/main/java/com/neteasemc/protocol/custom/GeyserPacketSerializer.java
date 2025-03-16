package com.neteasemc.protocol.custom;

import io.netty.buffer.ByteBuf;

public interface GeyserPacketSerializer<T extends GeyserBasePacket> extends IGeyserPacketSerializer<T> {
   default void serialize(ByteBuf buffer, T packet) {
   }

   default void deserialize(ByteBuf buffer, T packet) {
   }
}
