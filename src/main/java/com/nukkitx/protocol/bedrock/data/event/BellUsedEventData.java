package com.nukkitx.protocol.bedrock.data.event;

public final class BellUsedEventData implements EventData {
   private final int itemId;

   public EventDataType getType() {
      return EventDataType.BELL_USED;
   }

   public BellUsedEventData(int itemId) {
      this.itemId = itemId;
   }

   public int getItemId() {
      return this.itemId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BellUsedEventData)) {
         return false;
      } else {
         BellUsedEventData other = (BellUsedEventData)o;
         return this.getItemId() == other.getItemId();
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getItemId();
      return result;
   }

   public String toString() {
      return "BellUsedEventData(itemId=" + this.getItemId() + ")";
   }
}
