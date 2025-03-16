package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.structure.StructureEditorData;
import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3i;

public class StructureBlockUpdatePacket implements BedrockPacket {
   private Vector3i blockPosition;
   private StructureEditorData editorData;
   private boolean powered;
   private boolean waterlogged;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.STRUCTURE_BLOCK_UPDATE;
   }

   public Vector3i getBlockPosition() {
      return this.blockPosition;
   }

   public StructureEditorData getEditorData() {
      return this.editorData;
   }

   public boolean isPowered() {
      return this.powered;
   }

   public boolean isWaterlogged() {
      return this.waterlogged;
   }

   public void setBlockPosition(Vector3i blockPosition) {
      this.blockPosition = blockPosition;
   }

   public void setEditorData(StructureEditorData editorData) {
      this.editorData = editorData;
   }

   public void setPowered(boolean powered) {
      this.powered = powered;
   }

   public void setWaterlogged(boolean waterlogged) {
      this.waterlogged = waterlogged;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StructureBlockUpdatePacket)) {
         return false;
      } else {
         StructureBlockUpdatePacket other = (StructureBlockUpdatePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.powered != other.powered) {
            return false;
         } else if (this.waterlogged != other.waterlogged) {
            return false;
         } else {
            Object this$blockPosition = this.blockPosition;
            Object other$blockPosition = other.blockPosition;
            if (this$blockPosition == null) {
               if (other$blockPosition != null) {
                  return false;
               }
            } else if (!this$blockPosition.equals(other$blockPosition)) {
               return false;
            }

            Object this$editorData = this.editorData;
            Object other$editorData = other.editorData;
            if (this$editorData == null) {
               if (other$editorData != null) {
                  return false;
               }
            } else if (!this$editorData.equals(other$editorData)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof StructureBlockUpdatePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.powered ? 79 : 97);
      result = result * 59 + (this.waterlogged ? 79 : 97);
      Object $blockPosition = this.blockPosition;
      result = result * 59 + ($blockPosition == null ? 43 : $blockPosition.hashCode());
      Object $editorData = this.editorData;
      result = result * 59 + ($editorData == null ? 43 : $editorData.hashCode());
      return result;
   }

   public String toString() {
      return "StructureBlockUpdatePacket(blockPosition=" + this.blockPosition + ", editorData=" + this.editorData + ", powered=" + this.powered + ", waterlogged=" + this.waterlogged + ")";
   }

   public static enum Type {
      NONE,
      SAVE,
      LOAD;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{NONE, SAVE, LOAD};
      }
   }
}
