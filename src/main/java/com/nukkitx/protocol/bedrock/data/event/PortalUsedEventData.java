package com.nukkitx.protocol.bedrock.data.event;

public final class PortalUsedEventData implements EventData {
   private final int fromDimensionId;
   private final int toDimensionId;

   public EventDataType getType() {
      return EventDataType.PORTAL_USED;
   }

   public PortalUsedEventData(int fromDimensionId, int toDimensionId) {
      this.fromDimensionId = fromDimensionId;
      this.toDimensionId = toDimensionId;
   }

   public int getFromDimensionId() {
      return this.fromDimensionId;
   }

   public int getToDimensionId() {
      return this.toDimensionId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PortalUsedEventData)) {
         return false;
      } else {
         PortalUsedEventData other = (PortalUsedEventData)o;
         if (this.getFromDimensionId() != other.getFromDimensionId()) {
            return false;
         } else {
            return this.getToDimensionId() == other.getToDimensionId();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getFromDimensionId();
      result = result * 59 + this.getToDimensionId();
      return result;
   }

   public String toString() {
      return "PortalUsedEventData(fromDimensionId=" + this.getFromDimensionId() + ", toDimensionId=" + this.getToDimensionId() + ")";
   }
}
