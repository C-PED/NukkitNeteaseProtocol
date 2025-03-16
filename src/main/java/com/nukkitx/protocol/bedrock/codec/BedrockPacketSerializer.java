package com.nukkitx.protocol.bedrock.codec;

import com.nukkitx.protocol.bedrock.BedrockSession;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import io.netty.buffer.ByteBuf;

public interface BedrockPacketSerializer<T extends BedrockPacket> {
   void serialize(ByteBuf var1, BedrockCodecHelper var2, T var3);

   default void serialize(ByteBuf buffer, BedrockCodecHelper helper, T packet, BedrockSession session) {
      this.serialize(buffer, helper, packet);
   }

   void deserialize(ByteBuf var1, BedrockCodecHelper var2, T var3);

   default void deserialize(ByteBuf buffer, BedrockCodecHelper helper, T packet, BedrockSession session) {
      this.deserialize(buffer, helper, packet);
   }
}
