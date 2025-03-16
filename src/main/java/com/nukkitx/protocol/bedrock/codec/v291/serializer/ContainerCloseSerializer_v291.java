package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ContainerClosePacket;
import io.netty.buffer.ByteBuf;

public class ContainerCloseSerializer_v291 implements BedrockPacketSerializer<ContainerClosePacket> {
   public static final ContainerCloseSerializer_v291 INSTANCE = new ContainerCloseSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ContainerClosePacket packet) {
      buffer.writeByte(packet.getId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ContainerClosePacket packet) {
      packet.setId(buffer.readByte());
   }

   protected ContainerCloseSerializer_v291() {
   }
}
