package com.nukkitx.protocol.bedrock.data.event;

public final class PortalBuiltEventData implements EventData {
   private final int dimensionId;

   public EventDataType getType() {
      return EventDataType.PORTAL_BUILT;
   }

   public PortalBuiltEventData(int dimensionId) {
      this.dimensionId = dimensionId;
   }

   public int getDimensionId() {
      return this.dimensionId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PortalBuiltEventData)) {
         return false;
      } else {
         PortalBuiltEventData other = (PortalBuiltEventData)o;
         return this.getDimensionId() == other.getDimensionId();
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getDimensionId();
      return result;
   }

   public String toString() {
      return "PortalBuiltEventData(dimensionId=" + this.getDimensionId() + ")";
   }
}
