package com.nukkitx.protocol.bedrock.codec.v419.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.ResourcePackStackSerializer_v291;
import com.nukkitx.protocol.bedrock.packet.ResourcePackStackPacket;
import io.netty.buffer.ByteBuf;

public class ResourcePackStackSerializer_v419 extends ResourcePackStackSerializer_v291 {
   public static final ResourcePackStackSerializer_v419 INSTANCE = new ResourcePackStackSerializer_v419();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
      super.serialize(buffer, helper, packet);
      helper.writeString(buffer, packet.getGameVersion());
      helper.writeExperiments(buffer, packet.getExperiments());
      buffer.writeBoolean(packet.isExperimentsPreviouslyToggled());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setGameVersion(helper.readString(buffer));
      helper.readExperiments(buffer, packet.getExperiments());
      packet.setExperimentsPreviouslyToggled(buffer.readBoolean());
   }

   protected ResourcePackStackSerializer_v419() {
   }
}
