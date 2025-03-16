package com.nukkitx.protocol.bedrock.data;

public class SyncedPlayerMovementSettings {
   private AuthoritativeMovementMode movementMode;
   private int rewindHistorySize;
   boolean serverAuthoritativeBlockBreaking;

   public AuthoritativeMovementMode getMovementMode() {
      return this.movementMode;
   }

   public int getRewindHistorySize() {
      return this.rewindHistorySize;
   }

   public boolean isServerAuthoritativeBlockBreaking() {
      return this.serverAuthoritativeBlockBreaking;
   }

   public void setMovementMode(AuthoritativeMovementMode movementMode) {
      this.movementMode = movementMode;
   }

   public void setRewindHistorySize(int rewindHistorySize) {
      this.rewindHistorySize = rewindHistorySize;
   }

   public void setServerAuthoritativeBlockBreaking(boolean serverAuthoritativeBlockBreaking) {
      this.serverAuthoritativeBlockBreaking = serverAuthoritativeBlockBreaking;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SyncedPlayerMovementSettings)) {
         return false;
      } else {
         SyncedPlayerMovementSettings other = (SyncedPlayerMovementSettings)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getRewindHistorySize() != other.getRewindHistorySize()) {
            return false;
         } else if (this.isServerAuthoritativeBlockBreaking() != other.isServerAuthoritativeBlockBreaking()) {
            return false;
         } else {
            Object this$movementMode = this.getMovementMode();
            Object other$movementMode = other.getMovementMode();
            if (this$movementMode == null) {
               if (other$movementMode != null) {
                  return false;
               }
            } else if (!this$movementMode.equals(other$movementMode)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SyncedPlayerMovementSettings;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getRewindHistorySize();
      result = result * 59 + (this.isServerAuthoritativeBlockBreaking() ? 79 : 97);
      Object $movementMode = this.getMovementMode();
      result = result * 59 + ($movementMode == null ? 43 : $movementMode.hashCode());
      return result;
   }

   public String toString() {
      return "SyncedPlayerMovementSettings(movementMode=" + this.getMovementMode() + ", rewindHistorySize=" + this.getRewindHistorySize() + ", serverAuthoritativeBlockBreaking=" + this.isServerAuthoritativeBlockBreaking() + ")";
   }
}
