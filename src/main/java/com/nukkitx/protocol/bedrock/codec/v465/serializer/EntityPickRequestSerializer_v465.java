package com.nukkitx.protocol.bedrock.codec.v465.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.EntityPickRequestSerializer_v291;
import com.nukkitx.protocol.bedrock.packet.EntityPickRequestPacket;
import io.netty.buffer.ByteBuf;

public class EntityPickRequestSerializer_v465 extends EntityPickRequestSerializer_v291 {
   public static final EntityPickRequestSerializer_v465 INSTANCE = new EntityPickRequestSerializer_v465();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EntityPickRequestPacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeBoolean(packet.isWithData());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EntityPickRequestPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setWithData(buffer.readBoolean());
   }

   protected EntityPickRequestSerializer_v465() {
   }
}
