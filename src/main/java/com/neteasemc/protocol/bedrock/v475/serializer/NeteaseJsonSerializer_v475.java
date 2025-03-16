package com.neteasemc.protocol.bedrock.v475.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.NeteaseJsonPacket;
import io.netty.buffer.ByteBuf;

public class NeteaseJsonSerializer_v475 implements BedrockPacketSerializer<NeteaseJsonPacket> {
   public static final NeteaseJsonSerializer_v475 INSTANCE = new NeteaseJsonSerializer_v475();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NeteaseJsonPacket packet) {
      helper.writeString(buffer, packet.getJsonString());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NeteaseJsonPacket packet) {
      packet.setJsonString(helper.readString(buffer));
   }
}
