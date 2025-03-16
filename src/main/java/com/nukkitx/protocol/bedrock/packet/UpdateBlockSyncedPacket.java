package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.BlockSyncType;
import com.nukkitx.protocol.common.PacketSignal;

public class UpdateBlockSyncedPacket extends UpdateBlockPacket {
   private long runtimeEntityId;
   private BlockSyncType entityBlockSyncType;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.UPDATE_BLOCK_SYNCED;
   }

   public String toString() {
      return "UpdateBlockSyncedPacket(runtimeEntityId=" + this.runtimeEntityId + ", entityBlockSyncType=" + this.entityBlockSyncType + ", flags=" + this.flags + ", blockPosition=" + this.blockPosition + ", definition=" + this.definition + ", dataLayer=" + this.dataLayer + ")";
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public BlockSyncType getEntityBlockSyncType() {
      return this.entityBlockSyncType;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setEntityBlockSyncType(BlockSyncType entityBlockSyncType) {
      this.entityBlockSyncType = entityBlockSyncType;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UpdateBlockSyncedPacket)) {
         return false;
      } else {
         UpdateBlockSyncedPacket other = (UpdateBlockSyncedPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (!super.equals(o)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else {
            Object this$entityBlockSyncType = this.entityBlockSyncType;
            Object other$entityBlockSyncType = other.entityBlockSyncType;
            if (this$entityBlockSyncType == null) {
               if (other$entityBlockSyncType != null) {
                  return false;
               }
            } else if (!this$entityBlockSyncType.equals(other$entityBlockSyncType)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UpdateBlockSyncedPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = super.hashCode();
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      Object $entityBlockSyncType = this.entityBlockSyncType;
      result = result * 59 + ($entityBlockSyncType == null ? 43 : $entityBlockSyncType.hashCode());
      return result;
   }
}
