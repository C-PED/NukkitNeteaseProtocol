package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.CodeBuilderPacket;
import io.netty.buffer.ByteBuf;

public class CodeBuilderSerializer_v407 implements BedrockPacketSerializer<CodeBuilderPacket> {
   public static final CodeBuilderSerializer_v407 INSTANCE = new CodeBuilderSerializer_v407();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CodeBuilderPacket packet) {
      helper.writeString(buffer, packet.getUrl());
      buffer.writeBoolean(packet.isOpening());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CodeBuilderPacket packet) {
      packet.setUrl(helper.readString(buffer));
      packet.setOpening(buffer.readBoolean());
   }

   protected CodeBuilderSerializer_v407() {
   }
}
