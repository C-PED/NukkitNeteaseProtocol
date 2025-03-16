package com.nukkitx.protocol.bedrock.data.event;

public final class MobBornEventData implements EventData {
   private final int entityType;
   private final int variant;
   private final int color;

   public EventDataType getType() {
      return EventDataType.MOB_BORN;
   }

   public MobBornEventData(int entityType, int variant, int color) {
      this.entityType = entityType;
      this.variant = variant;
      this.color = color;
   }

   public int getEntityType() {
      return this.entityType;
   }

   public int getVariant() {
      return this.variant;
   }

   public int getColor() {
      return this.color;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MobBornEventData)) {
         return false;
      } else {
         MobBornEventData other = (MobBornEventData)o;
         if (this.getEntityType() != other.getEntityType()) {
            return false;
         } else if (this.getVariant() != other.getVariant()) {
            return false;
         } else {
            return this.getColor() == other.getColor();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getEntityType();
      result = result * 59 + this.getVariant();
      result = result * 59 + this.getColor();
      return result;
   }

   public String toString() {
      return "MobBornEventData(entityType=" + this.getEntityType() + ", variant=" + this.getVariant() + ", color=" + this.getColor() + ")";
   }
}
