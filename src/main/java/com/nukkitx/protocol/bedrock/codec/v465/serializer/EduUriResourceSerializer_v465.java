package com.nukkitx.protocol.bedrock.codec.v465.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.EduSharedUriResource;
import com.nukkitx.protocol.bedrock.packet.EduUriResourcePacket;
import io.netty.buffer.ByteBuf;

public class EduUriResourceSerializer_v465 implements BedrockPacketSerializer<EduUriResourcePacket> {
   public static final EduUriResourceSerializer_v465 INSTANCE = new EduUriResourceSerializer_v465();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EduUriResourcePacket packet) {
      helper.writeString(buffer, packet.getEduSharedUriResource().getButtonName());
      helper.writeString(buffer, packet.getEduSharedUriResource().getLinkUri());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EduUriResourcePacket packet) {
      packet.setEduSharedUriResource(new EduSharedUriResource(helper.readString(buffer), helper.readString(buffer)));
   }

   protected EduUriResourceSerializer_v465() {
   }
}
