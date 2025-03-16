package com.nukkitx.protocol.bedrock.codec.v503.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.definitions.DimensionDefinition;
import com.nukkitx.protocol.bedrock.packet.DimensionDataPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class DimensionDataSerializer_v503 implements BedrockPacketSerializer<DimensionDataPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DimensionDataPacket packet) {
      helper.writeArray(buffer, packet.getDefinitions(), this::writeDefinition);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DimensionDataPacket packet) {
      helper.readArray(buffer, packet.getDefinitions(), this::readDefinition);
   }

   protected void writeDefinition(ByteBuf buffer, BedrockCodecHelper helper, DimensionDefinition definition) {
      helper.writeString(buffer, definition.getId());
      VarInts.writeInt(buffer, definition.getMaximumHeight());
      VarInts.writeInt(buffer, definition.getMinimumHeight());
      VarInts.writeInt(buffer, definition.getGeneratorType());
   }

   protected DimensionDefinition readDefinition(ByteBuf buffer, BedrockCodecHelper helper) {
      String id = helper.readString(buffer);
      int maximumHeight = VarInts.readInt(buffer);
      int minimumHeight = VarInts.readInt(buffer);
      int generatorType = VarInts.readInt(buffer);
      return new DimensionDefinition(id, maximumHeight, minimumHeight, generatorType);
   }
}
