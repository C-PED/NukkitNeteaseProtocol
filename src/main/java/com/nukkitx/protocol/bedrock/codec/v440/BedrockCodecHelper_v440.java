package com.nukkitx.protocol.bedrock.codec.v440;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v431.BedrockCodecHelper_v431;
import com.nukkitx.protocol.bedrock.data.GameRuleData;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.bedrock.data.structure.StructureAnimationMode;
import com.nukkitx.protocol.bedrock.data.structure.StructureMirror;
import com.nukkitx.protocol.bedrock.data.structure.StructureRotation;
import com.nukkitx.protocol.bedrock.data.structure.StructureSettings;
import com.nukkitx.protocol.common.util.Preconditions;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;

public class BedrockCodecHelper_v440 extends BedrockCodecHelper_v431 {
   public BedrockCodecHelper_v440(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerSlotType> containerSlotTypes) {
      super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes);
   }

   public void writeGameRule(ByteBuf buffer, GameRuleData<?> gameRule) {
      Preconditions.checkNotNull(buffer, "buffer");
      Preconditions.checkNotNull(gameRule, "gameRule");
      Object value = gameRule.getValue();
      int id = this.gameRuleType.getId(value.getClass());
      this.writeString(buffer, gameRule.getName());
      buffer.writeBoolean(gameRule.isEditable());
      VarInts.writeUnsignedInt(buffer, id);
      switch (id) {
         case 1:
            buffer.writeBoolean((Boolean)value);
            break;
         case 2:
            VarInts.writeUnsignedInt(buffer, (Integer)value);
            break;
         case 3:
            buffer.writeFloatLE((Float)value);
      }

   }

   public GameRuleData<?> readGameRule(ByteBuf buffer) {
      Preconditions.checkNotNull(buffer, "buffer");
      String name = this.readString(buffer);
      boolean editable = buffer.readBoolean();
      int type = VarInts.readUnsignedInt(buffer);
      switch (type) {
         case 1:
            return new GameRuleData(name, editable, buffer.readBoolean());
         case 2:
            return new GameRuleData(name, editable, VarInts.readUnsignedInt(buffer));
         case 3:
            return new GameRuleData(name, editable, buffer.readFloatLE());
         default:
            throw new IllegalStateException("Invalid gamerule type received");
      }
   }

   public StructureSettings readStructureSettings(ByteBuf buffer) {
      String paletteName = this.readString(buffer);
      boolean ignoringEntities = buffer.readBoolean();
      boolean ignoringBlocks = buffer.readBoolean();
      Vector3i size = this.readBlockPosition(buffer);
      Vector3i offset = this.readBlockPosition(buffer);
      long lastEditedByEntityId = VarInts.readLong(buffer);
      StructureRotation rotation = StructureRotation.from(buffer.readByte());
      StructureMirror mirror = StructureMirror.from(buffer.readByte());
      StructureAnimationMode animationMode = StructureAnimationMode.from(buffer.readUnsignedByte());
      float animationSeconds = buffer.readFloatLE();
      float integrityValue = buffer.readFloatLE();
      int integritySeed = buffer.readIntLE();
      Vector3f pivot = this.readVector3f(buffer);
      return new StructureSettings(paletteName, ignoringEntities, ignoringBlocks, true, size, offset, lastEditedByEntityId, rotation, mirror, animationMode, animationSeconds, integrityValue, integritySeed, pivot);
   }

   public void writeStructureSettings(ByteBuf buffer, StructureSettings settings) {
      this.writeString(buffer, settings.getPaletteName());
      buffer.writeBoolean(settings.isIgnoringEntities());
      buffer.writeBoolean(settings.isIgnoringBlocks());
      this.writeBlockPosition(buffer, settings.getSize());
      this.writeBlockPosition(buffer, settings.getOffset());
      VarInts.writeLong(buffer, settings.getLastEditedByEntityId());
      buffer.writeByte(settings.getRotation().ordinal());
      buffer.writeByte(settings.getMirror().ordinal());
      buffer.writeByte(settings.getAnimationMode().ordinal());
      buffer.writeFloatLE(settings.getAnimationSeconds());
      buffer.writeFloatLE(settings.getIntegrityValue());
      buffer.writeIntLE(settings.getIntegritySeed());
      this.writeVector3f(buffer, settings.getPivot());
   }
}
