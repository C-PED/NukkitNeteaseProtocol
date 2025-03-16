package com.nukkitx.protocol.bedrock.codec.v582.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.OpenSignPacket;
import io.netty.buffer.ByteBuf;

public class OpenSignSerializer_v582 implements BedrockPacketSerializer<OpenSignPacket> {
   public static final OpenSignSerializer_v582 INSTANCE = new OpenSignSerializer_v582();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, OpenSignPacket packet) {
      helper.writeBlockPosition(buffer, packet.getPosition());
      buffer.writeBoolean(packet.isFrontSide());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, OpenSignPacket packet) {
      packet.setPosition(helper.readBlockPosition(buffer));
      packet.setFrontSide(buffer.readBoolean());
   }

   protected OpenSignSerializer_v582() {
   }
}
