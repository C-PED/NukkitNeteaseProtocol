package com.nukkitx.protocol.bedrock.data.event;

public class TargetBlockHitEventData implements EventData {
   private final int redstoneLevel;

   public EventDataType getType() {
      return EventDataType.TARGET_BLOCK_HIT;
   }

   public TargetBlockHitEventData(int redstoneLevel) {
      this.redstoneLevel = redstoneLevel;
   }

   public int getRedstoneLevel() {
      return this.redstoneLevel;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TargetBlockHitEventData)) {
         return false;
      } else {
         TargetBlockHitEventData other = (TargetBlockHitEventData)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.getRedstoneLevel() == other.getRedstoneLevel();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TargetBlockHitEventData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getRedstoneLevel();
      return result;
   }

   public String toString() {
      return "TargetBlockHitEventData(redstoneLevel=" + this.getRedstoneLevel() + ")";
   }
}
