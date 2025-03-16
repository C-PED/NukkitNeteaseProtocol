package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3i;

public class SetSpawnPositionPacket implements BedrockPacket {
   private Type spawnType;
   private Vector3i blockPosition;
   private int dimensionId;
   private Vector3i spawnPosition = Vector3i.from(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
   /** @deprecated */
   @Deprecated
   private boolean spawnForced;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_SPAWN_POSITION;
   }

   public Type getSpawnType() {
      return this.spawnType;
   }

   public Vector3i getBlockPosition() {
      return this.blockPosition;
   }

   public int getDimensionId() {
      return this.dimensionId;
   }

   public Vector3i getSpawnPosition() {
      return this.spawnPosition;
   }

   /** @deprecated */
   @Deprecated
   public boolean isSpawnForced() {
      return this.spawnForced;
   }

   public void setSpawnType(Type spawnType) {
      this.spawnType = spawnType;
   }

   public void setBlockPosition(Vector3i blockPosition) {
      this.blockPosition = blockPosition;
   }

   public void setDimensionId(int dimensionId) {
      this.dimensionId = dimensionId;
   }

   public void setSpawnPosition(Vector3i spawnPosition) {
      this.spawnPosition = spawnPosition;
   }

   /** @deprecated */
   @Deprecated
   public void setSpawnForced(boolean spawnForced) {
      this.spawnForced = spawnForced;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetSpawnPositionPacket)) {
         return false;
      } else {
         SetSpawnPositionPacket other = (SetSpawnPositionPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.dimensionId != other.dimensionId) {
            return false;
         } else if (this.spawnForced != other.spawnForced) {
            return false;
         } else {
            Object this$spawnType = this.spawnType;
            Object other$spawnType = other.spawnType;
            if (this$spawnType == null) {
               if (other$spawnType != null) {
                  return false;
               }
            } else if (!this$spawnType.equals(other$spawnType)) {
               return false;
            }

            Object this$blockPosition = this.blockPosition;
            Object other$blockPosition = other.blockPosition;
            if (this$blockPosition == null) {
               if (other$blockPosition != null) {
                  return false;
               }
            } else if (!this$blockPosition.equals(other$blockPosition)) {
               return false;
            }

            Object this$spawnPosition = this.spawnPosition;
            Object other$spawnPosition = other.spawnPosition;
            if (this$spawnPosition == null) {
               if (other$spawnPosition != null) {
                  return false;
               }
            } else if (!this$spawnPosition.equals(other$spawnPosition)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetSpawnPositionPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.dimensionId;
      result = result * 59 + (this.spawnForced ? 79 : 97);
      Object $spawnType = this.spawnType;
      result = result * 59 + ($spawnType == null ? 43 : $spawnType.hashCode());
      Object $blockPosition = this.blockPosition;
      result = result * 59 + ($blockPosition == null ? 43 : $blockPosition.hashCode());
      Object $spawnPosition = this.spawnPosition;
      result = result * 59 + ($spawnPosition == null ? 43 : $spawnPosition.hashCode());
      return result;
   }

   public String toString() {
      return "SetSpawnPositionPacket(spawnType=" + this.spawnType + ", blockPosition=" + this.blockPosition + ", dimensionId=" + this.dimensionId + ", spawnPosition=" + this.spawnPosition + ", spawnForced=" + this.spawnForced + ")";
   }

   public static enum Type {
      PLAYER_SPAWN,
      WORLD_SPAWN;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{PLAYER_SPAWN, WORLD_SPAWN};
      }
   }
}
