package com.nukkitx.protocol.serializer;

import io.netty.buffer.ByteBuf;

public interface PacketSerializer<P, H> {
   void serialize(ByteBuf var1, H var2, P var3);

   void deserialize(ByteBuf var1, H var2, P var3);
}
