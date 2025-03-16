package com.nukkitx.network;

import io.netty.buffer.ByteBuf;

public interface PacketCodec<T extends NetworkPacket> {
   T tryDecode(ByteBuf var1);

   ByteBuf tryEncode(T var1);

   int getId(T var1);
}
