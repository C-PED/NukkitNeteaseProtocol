package com.nukkitx.protocol.bedrock.data.event;

public final class CauldronUsedEventData implements EventData {
   private final int potionId;
   private final int color;
   private final int fillLevel;

   public EventDataType getType() {
      return EventDataType.CAULDRON_USED;
   }

   public CauldronUsedEventData(int potionId, int color, int fillLevel) {
      this.potionId = potionId;
      this.color = color;
      this.fillLevel = fillLevel;
   }

   public int getPotionId() {
      return this.potionId;
   }

   public int getColor() {
      return this.color;
   }

   public int getFillLevel() {
      return this.fillLevel;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CauldronUsedEventData)) {
         return false;
      } else {
         CauldronUsedEventData other = (CauldronUsedEventData)o;
         if (this.getPotionId() != other.getPotionId()) {
            return false;
         } else if (this.getColor() != other.getColor()) {
            return false;
         } else {
            return this.getFillLevel() == other.getFillLevel();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getPotionId();
      result = result * 59 + this.getColor();
      result = result * 59 + this.getFillLevel();
      return result;
   }

   public String toString() {
      return "CauldronUsedEventData(potionId=" + this.getPotionId() + ", color=" + this.getColor() + ", fillLevel=" + this.getFillLevel() + ")";
   }
}
