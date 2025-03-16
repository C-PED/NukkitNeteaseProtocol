package com.nukkitx.protocol.bedrock.codec.v419.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ContainerClosePacket;
import io.netty.buffer.ByteBuf;

public class ContainerCloseSerializer_v419 implements BedrockPacketSerializer<ContainerClosePacket> {
   public static final ContainerCloseSerializer_v419 INSTANCE = new ContainerCloseSerializer_v419();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ContainerClosePacket packet) {
      buffer.writeByte(packet.getId());
      buffer.writeBoolean(packet.isServerInitiated());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ContainerClosePacket packet) {
      packet.setId(buffer.readByte());
      packet.setServerInitiated(buffer.readBoolean());
   }

   private ContainerCloseSerializer_v419() {
   }
}
