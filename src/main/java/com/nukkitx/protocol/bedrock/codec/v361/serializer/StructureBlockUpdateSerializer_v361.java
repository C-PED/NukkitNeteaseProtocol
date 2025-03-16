package com.nukkitx.protocol.bedrock.codec.v361.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.structure.StructureBlockType;
import com.nukkitx.protocol.bedrock.data.structure.StructureEditorData;
import com.nukkitx.protocol.bedrock.data.structure.StructureRedstoneSaveMode;
import com.nukkitx.protocol.bedrock.data.structure.StructureSettings;
import com.nukkitx.protocol.bedrock.packet.StructureBlockUpdatePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class StructureBlockUpdateSerializer_v361 implements BedrockPacketSerializer<StructureBlockUpdatePacket> {
   public static final StructureBlockUpdateSerializer_v361 INSTANCE = new StructureBlockUpdateSerializer_v361();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StructureBlockUpdatePacket packet) {
      helper.writeBlockPosition(buffer, packet.getBlockPosition());
      this.writeEditorData(buffer, helper, packet.getEditorData());
      buffer.writeBoolean(packet.isPowered());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StructureBlockUpdatePacket packet) {
      packet.setBlockPosition(helper.readBlockPosition(buffer));
      packet.setEditorData(this.readEditorData(buffer, helper));
      packet.setPowered(buffer.readBoolean());
   }

   protected StructureEditorData readEditorData(ByteBuf buffer, BedrockCodecHelper helper) {
      String name = helper.readString(buffer);
      String dataField = helper.readString(buffer);
      boolean includingPlayers = buffer.readBoolean();
      boolean boundingBoxVisible = buffer.readBoolean();
      StructureBlockType type = StructureBlockType.from(VarInts.readInt(buffer));
      StructureSettings settings = helper.readStructureSettings(buffer);
      return new StructureEditorData(name, dataField, includingPlayers, boundingBoxVisible, type, settings, StructureRedstoneSaveMode.SAVES_TO_DISK);
   }

   protected void writeEditorData(ByteBuf buffer, BedrockCodecHelper helper, StructureEditorData data) {
      helper.writeString(buffer, data.getName());
      helper.writeString(buffer, data.getDataField());
      buffer.writeBoolean(data.isIncludingPlayers());
      buffer.writeBoolean(data.isBoundingBoxVisible());
      VarInts.writeInt(buffer, data.getType().ordinal());
      helper.writeStructureSettings(buffer, data.getSettings());
   }

   protected StructureBlockUpdateSerializer_v361() {
   }
}
