package com.nukkitx.protocol.bedrock.codec.v361.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.structure.StructureTemplateRequestOperation;
import com.nukkitx.protocol.bedrock.packet.StructureTemplateDataRequestPacket;
import io.netty.buffer.ByteBuf;

public class StructureTemplateDataRequestSerializer_v361 implements BedrockPacketSerializer<StructureTemplateDataRequestPacket> {
   public static final StructureTemplateDataRequestSerializer_v361 INSTANCE = new StructureTemplateDataRequestSerializer_v361();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StructureTemplateDataRequestPacket packet) {
      helper.writeString(buffer, packet.getName());
      helper.writeBlockPosition(buffer, packet.getPosition());
      helper.writeStructureSettings(buffer, packet.getSettings());
      buffer.writeByte(packet.getOperation().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StructureTemplateDataRequestPacket packet) {
      packet.setName(helper.readString(buffer));
      packet.setPosition(helper.readBlockPosition(buffer));
      packet.setSettings(helper.readStructureSettings(buffer));
      packet.setOperation(StructureTemplateRequestOperation.from(buffer.readByte()));
   }

   protected StructureTemplateDataRequestSerializer_v361() {
   }
}
