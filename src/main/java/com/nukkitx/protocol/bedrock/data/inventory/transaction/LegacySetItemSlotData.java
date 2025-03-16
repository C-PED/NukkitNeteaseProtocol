package com.nukkitx.protocol.bedrock.data.inventory.transaction;

import java.util.Arrays;

public final class LegacySetItemSlotData {
   private final int containerId;
   private final byte[] slots;

   public LegacySetItemSlotData(int containerId, byte[] slots) {
      this.containerId = containerId;
      this.slots = slots;
   }

   public int getContainerId() {
      return this.containerId;
   }

   public byte[] getSlots() {
      return this.slots;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LegacySetItemSlotData)) {
         return false;
      } else {
         LegacySetItemSlotData other = (LegacySetItemSlotData)o;
         if (this.getContainerId() != other.getContainerId()) {
            return false;
         } else {
            return Arrays.equals(this.getSlots(), other.getSlots());
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getContainerId();
      result = result * 59 + Arrays.hashCode(this.getSlots());
      return result;
   }

   public String toString() {
      return "LegacySetItemSlotData(containerId=" + this.getContainerId() + ", slots=" + Arrays.toString(this.getSlots()) + ")";
   }
}
