package com.nukkitx.protocol.bedrock.data.event;

public final class EntityInteractEventData implements EventData {
   private final int interactionType;
   private final int legacyEntityTypeId;
   private final int variant;
   private final int paletteColor;

   public EventDataType getType() {
      return EventDataType.ENTITY_INTERACT;
   }

   public EntityInteractEventData(int interactionType, int legacyEntityTypeId, int variant, int paletteColor) {
      this.interactionType = interactionType;
      this.legacyEntityTypeId = legacyEntityTypeId;
      this.variant = variant;
      this.paletteColor = paletteColor;
   }

   public int getInteractionType() {
      return this.interactionType;
   }

   public int getLegacyEntityTypeId() {
      return this.legacyEntityTypeId;
   }

   public int getVariant() {
      return this.variant;
   }

   public int getPaletteColor() {
      return this.paletteColor;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EntityInteractEventData)) {
         return false;
      } else {
         EntityInteractEventData other = (EntityInteractEventData)o;
         if (this.getInteractionType() != other.getInteractionType()) {
            return false;
         } else if (this.getLegacyEntityTypeId() != other.getLegacyEntityTypeId()) {
            return false;
         } else if (this.getVariant() != other.getVariant()) {
            return false;
         } else {
            return this.getPaletteColor() == other.getPaletteColor();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getInteractionType();
      result = result * 59 + this.getLegacyEntityTypeId();
      result = result * 59 + this.getVariant();
      result = result * 59 + this.getPaletteColor();
      return result;
   }

   public String toString() {
      return "EntityInteractEventData(interactionType=" + this.getInteractionType() + ", legacyEntityTypeId=" + this.getLegacyEntityTypeId() + ", variant=" + this.getVariant() + ", paletteColor=" + this.getPaletteColor() + ")";
   }
}
