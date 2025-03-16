package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.StructureBlockUpdateSerializer_v361;
import com.nukkitx.protocol.bedrock.data.structure.StructureBlockType;
import com.nukkitx.protocol.bedrock.data.structure.StructureEditorData;
import com.nukkitx.protocol.bedrock.data.structure.StructureRedstoneSaveMode;
import com.nukkitx.protocol.bedrock.data.structure.StructureSettings;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class StructureBlockUpdateSerializer_v388 extends StructureBlockUpdateSerializer_v361 {
   public static final StructureBlockUpdateSerializer_v388 INSTANCE = new StructureBlockUpdateSerializer_v388();

   protected StructureEditorData readEditorData(ByteBuf buffer, BedrockCodecHelper helper) {
      String name = helper.readString(buffer);
      String dataField = helper.readString(buffer);
      boolean includingPlayers = buffer.readBoolean();
      boolean boundingBoxVisible = buffer.readBoolean();
      StructureBlockType type = StructureBlockType.from(VarInts.readInt(buffer));
      StructureSettings settings = helper.readStructureSettings(buffer);
      StructureRedstoneSaveMode redstoneSaveMode = StructureRedstoneSaveMode.from(VarInts.readInt(buffer));
      return new StructureEditorData(name, dataField, includingPlayers, boundingBoxVisible, type, settings, redstoneSaveMode);
   }

   protected void writeEditorData(ByteBuf buffer, BedrockCodecHelper helper, StructureEditorData data) {
      super.writeEditorData(buffer, helper, data);
      VarInts.writeInt(buffer, data.getRedstoneSaveMode().ordinal());
   }

   protected StructureBlockUpdateSerializer_v388() {
   }
}
