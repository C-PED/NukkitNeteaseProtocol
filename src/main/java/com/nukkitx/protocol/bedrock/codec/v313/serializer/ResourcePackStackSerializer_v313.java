package com.nukkitx.protocol.bedrock.codec.v313.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.ResourcePackStackSerializer_v291;
import com.nukkitx.protocol.bedrock.data.ExperimentData;
import com.nukkitx.protocol.bedrock.packet.ResourcePackStackPacket;
import io.netty.buffer.ByteBuf;

public class ResourcePackStackSerializer_v313 extends ResourcePackStackSerializer_v291 {
   public static final ResourcePackStackSerializer_v313 INSTANCE = new ResourcePackStackSerializer_v313();
   private static final ExperimentData LEGACY_EXPERIMENT_DATA = new ExperimentData("legacy_experiment", true);

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeBoolean(packet.getExperiments().contains(LEGACY_EXPERIMENT_DATA));
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
      super.deserialize(buffer, helper, packet);
      if (buffer.readBoolean()) {
         packet.getExperiments().add(LEGACY_EXPERIMENT_DATA);
      }

   }

   protected ResourcePackStackSerializer_v313() {
   }
}
