package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.structure.StructureAnimationMode;
import com.nukkitx.protocol.bedrock.data.structure.StructureBlockType;
import com.nukkitx.protocol.bedrock.data.structure.StructureEditorData;
import com.nukkitx.protocol.bedrock.data.structure.StructureMirror;
import com.nukkitx.protocol.bedrock.data.structure.StructureRedstoneSaveMode;
import com.nukkitx.protocol.bedrock.data.structure.StructureRotation;
import com.nukkitx.protocol.bedrock.data.structure.StructureSettings;
import com.nukkitx.protocol.bedrock.packet.StructureBlockUpdatePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;

public class StructureBlockUpdateSerializer_v291 implements BedrockPacketSerializer<StructureBlockUpdatePacket> {
   public static final StructureBlockUpdateSerializer_v291 INSTANCE = new StructureBlockUpdateSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StructureBlockUpdatePacket packet) {
      StructureEditorData editorData = packet.getEditorData();
      StructureSettings settings = editorData.getSettings();
      helper.writeBlockPosition(buffer, packet.getBlockPosition());
      VarInts.writeUnsignedInt(buffer, editorData.getType().ordinal());
      helper.writeString(buffer, editorData.getName());
      helper.writeString(buffer, editorData.getName());
      helper.writeBlockPosition(buffer, settings.getOffset());
      helper.writeBlockPosition(buffer, settings.getSize());
      buffer.writeBoolean(!settings.isIgnoringEntities());
      buffer.writeBoolean(settings.isIgnoringBlocks());
      buffer.writeBoolean(editorData.isIncludingPlayers());
      buffer.writeBoolean(false);
      buffer.writeFloatLE(settings.getIntegrityValue());
      VarInts.writeUnsignedInt(buffer, settings.getIntegritySeed());
      VarInts.writeUnsignedInt(buffer, settings.getMirror().ordinal());
      VarInts.writeUnsignedInt(buffer, settings.getRotation().ordinal());
      buffer.writeBoolean(settings.isIgnoringEntities());
      buffer.writeBoolean(true);
      Vector3i min = packet.getBlockPosition().add(settings.getOffset());
      helper.writeVector3i(buffer, min);
      Vector3i max = min.add(settings.getSize());
      helper.writeVector3i(buffer, max);
      buffer.writeBoolean(editorData.isBoundingBoxVisible());
      buffer.writeBoolean(packet.isPowered());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StructureBlockUpdatePacket packet) {
      packet.setBlockPosition(helper.readBlockPosition(buffer));
      StructureBlockType structureType = StructureBlockType.values()[VarInts.readUnsignedInt(buffer)];
      String name = helper.readString(buffer);
      String dataField = helper.readString(buffer);
      Vector3i offset = helper.readBlockPosition(buffer);
      Vector3i size = helper.readBlockPosition(buffer);
      buffer.readBoolean();
      boolean ignoreBlocks = !buffer.readBoolean();
      boolean includePlayers = buffer.readBoolean();
      buffer.readBoolean();
      float structureIntegrity = buffer.readFloatLE();
      int integritySeed = VarInts.readUnsignedInt(buffer);
      StructureMirror mirror = StructureMirror.from(VarInts.readUnsignedInt(buffer));
      StructureRotation rotation = StructureRotation.from(VarInts.readUnsignedInt(buffer));
      boolean ignoreEntities = buffer.readBoolean();
      buffer.readBoolean();
      helper.readVector3i(buffer);
      helper.readVector3i(buffer);
      boolean boundingBoxVisible = buffer.readBoolean();
      StructureSettings settings = new StructureSettings("", ignoreEntities, ignoreBlocks, true, size, offset, -1L, rotation, mirror, StructureAnimationMode.NONE, 0.0F, structureIntegrity, integritySeed, Vector3f.ZERO);
      StructureEditorData editorData = new StructureEditorData(name, dataField, includePlayers, boundingBoxVisible, structureType, settings, StructureRedstoneSaveMode.SAVES_TO_DISK);
      packet.setEditorData(editorData);
      packet.setPowered(buffer.readBoolean());
   }

   protected StructureBlockUpdateSerializer_v291() {
   }
}
