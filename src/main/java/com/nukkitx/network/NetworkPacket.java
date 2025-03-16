package com.nukkitx.network;

import io.netty.buffer.ByteBuf;

public interface NetworkPacket {
   void encode(ByteBuf var1);

   void decode(ByteBuf var1);
}
