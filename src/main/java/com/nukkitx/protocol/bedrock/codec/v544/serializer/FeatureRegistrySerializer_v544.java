package com.nukkitx.protocol.bedrock.codec.v544.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.definitions.FeatureDefinition;
import com.nukkitx.protocol.bedrock.packet.FeatureRegistryPacket;
import com.nukkitx.protocol.common.util.TriConsumer;
import io.netty.buffer.ByteBuf;
import java.util.function.BiFunction;

public class FeatureRegistrySerializer_v544 implements BedrockPacketSerializer<FeatureRegistryPacket> {
   @Override
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, FeatureRegistryPacket packet) {
      helper.writeArray(buffer, packet.getFeatures(), (buf, aHelper, data) -> {
         helper.writeString(buf, data.getName());
         helper.writeString(buf, data.getJson());
      });
   }

   @Override
   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, FeatureRegistryPacket packet) {
      helper.readArray(buffer, packet.getFeatures(), (buf, aHelper) -> {
         String name = helper.readString(buf);
         String json = helper.readString(buf);
         return new FeatureDefinition(name, json);
      });
   }
}
