package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.ResourcePackStackSerializer_v313;
import com.nukkitx.protocol.bedrock.packet.ResourcePackStackPacket;
import io.netty.buffer.ByteBuf;

public class ResourcePackStackSerializer_v388 extends ResourcePackStackSerializer_v313 {
   public static final ResourcePackStackSerializer_v388 INSTANCE = new ResourcePackStackSerializer_v388();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
      super.serialize(buffer, helper, packet);
      helper.writeString(buffer, packet.getGameVersion());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setGameVersion(helper.readString(buffer));
   }

   protected ResourcePackStackSerializer_v388() {
   }
}
