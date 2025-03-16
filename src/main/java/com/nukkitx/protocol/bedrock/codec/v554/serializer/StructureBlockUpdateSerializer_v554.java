package com.nukkitx.protocol.bedrock.codec.v554.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v388.serializer.StructureBlockUpdateSerializer_v388;
import com.nukkitx.protocol.bedrock.packet.StructureBlockUpdatePacket;
import io.netty.buffer.ByteBuf;

public class StructureBlockUpdateSerializer_v554 extends StructureBlockUpdateSerializer_v388 {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StructureBlockUpdatePacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeBoolean(packet.isWaterlogged());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StructureBlockUpdatePacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setWaterlogged(buffer.readBoolean());
   }
}
