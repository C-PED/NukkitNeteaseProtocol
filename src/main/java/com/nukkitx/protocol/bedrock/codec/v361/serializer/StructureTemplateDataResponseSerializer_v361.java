package com.nukkitx.protocol.bedrock.codec.v361.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.StructureTemplateDataResponsePacket;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class StructureTemplateDataResponseSerializer_v361 implements BedrockPacketSerializer<StructureTemplateDataResponsePacket> {
   public static final StructureTemplateDataResponseSerializer_v361 INSTANCE = new StructureTemplateDataResponseSerializer_v361();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StructureTemplateDataResponsePacket packet) {
      helper.writeString(buffer, packet.getName());
      boolean save = packet.isSave();
      buffer.writeBoolean(save);
      if (save) {
         helper.writeTag(buffer, packet.getTag());
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StructureTemplateDataResponsePacket packet) {
      packet.setName(helper.readString(buffer));
      boolean save = buffer.readBoolean();
      packet.setSave(save);
      if (save) {
         packet.setTag((NbtMap)helper.readTag(buffer, NbtMap.class));
      }

   }

   protected StructureTemplateDataResponseSerializer_v361() {
   }
}
