package com.nukkitx.protocol.bedrock.codec.v527.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ToastRequestPacket;
import io.netty.buffer.ByteBuf;

public class ToastRequestSerializer_v527 implements BedrockPacketSerializer<ToastRequestPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ToastRequestPacket packet) {
      helper.writeString(buffer, packet.getTitle());
      helper.writeString(buffer, packet.getContent());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ToastRequestPacket packet) {
      packet.setTitle(helper.readString(buffer));
      packet.setContent(helper.readString(buffer));
   }
}
