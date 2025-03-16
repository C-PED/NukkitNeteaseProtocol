package com.neteasemc.protocol.custom;

import io.netty.buffer.ByteBuf;

public interface IGeyserPacketSerializer<P> {
   void serialize(ByteBuf var1, P var2);

   void deserialize(ByteBuf var1, P var2);
}
