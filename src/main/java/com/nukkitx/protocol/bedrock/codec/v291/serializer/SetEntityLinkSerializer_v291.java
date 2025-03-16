package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetEntityLinkPacket;
import io.netty.buffer.ByteBuf;

public class SetEntityLinkSerializer_v291 implements BedrockPacketSerializer<SetEntityLinkPacket> {
   public static final SetEntityLinkSerializer_v291 INSTANCE = new SetEntityLinkSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetEntityLinkPacket packet) {
      helper.writeEntityLink(buffer, packet.getEntityLink());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetEntityLinkPacket packet) {
      packet.setEntityLink(helper.readEntityLink(buffer));
   }

   protected SetEntityLinkSerializer_v291() {
   }
}
