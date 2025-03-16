package com.nukkitx.protocol.bedrock.data.inventory.itemstack.response;

import lombok.NonNull;

public final class ItemStackResponseSlot {
   private final int slot;
   private final int hotbarSlot;
   private final int count;
   private final int stackNetworkId;
   private final @NonNull String customName;
   private final int durabilityCorrection;

   public ItemStackResponseSlot(int slot, int hotbarSlot, int count, int stackNetworkId, @NonNull String customName, int durabilityCorrection) {
      if (customName == null) {
         throw new NullPointerException("customName is marked non-null but is null");
      } else {
         this.slot = slot;
         this.hotbarSlot = hotbarSlot;
         this.count = count;
         this.stackNetworkId = stackNetworkId;
         this.customName = customName;
         this.durabilityCorrection = durabilityCorrection;
      }
   }

   public int getSlot() {
      return this.slot;
   }

   public int getHotbarSlot() {
      return this.hotbarSlot;
   }

   public int getCount() {
      return this.count;
   }

   public int getStackNetworkId() {
      return this.stackNetworkId;
   }

   public @NonNull String getCustomName() {
      return this.customName;
   }

   public int getDurabilityCorrection() {
      return this.durabilityCorrection;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ItemStackResponseSlot)) {
         return false;
      } else {
         ItemStackResponseSlot other = (ItemStackResponseSlot)o;
         if (this.getSlot() != other.getSlot()) {
            return false;
         } else if (this.getHotbarSlot() != other.getHotbarSlot()) {
            return false;
         } else if (this.getCount() != other.getCount()) {
            return false;
         } else if (this.getStackNetworkId() != other.getStackNetworkId()) {
            return false;
         } else if (this.getDurabilityCorrection() != other.getDurabilityCorrection()) {
            return false;
         } else {
            Object this$customName = this.getCustomName();
            Object other$customName = other.getCustomName();
            if (this$customName == null) {
               if (other$customName != null) {
                  return false;
               }
            } else if (!this$customName.equals(other$customName)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getSlot();
      result = result * 59 + this.getHotbarSlot();
      result = result * 59 + this.getCount();
      result = result * 59 + this.getStackNetworkId();
      result = result * 59 + this.getDurabilityCorrection();
      Object $customName = this.getCustomName();
      result = result * 59 + ($customName == null ? 43 : $customName.hashCode());
      return result;
   }

   public String toString() {
      return "ItemStackResponseSlot(slot=" + this.getSlot() + ", hotbarSlot=" + this.getHotbarSlot() + ", count=" + this.getCount() + ", stackNetworkId=" + this.getStackNetworkId() + ", customName=" + this.getCustomName() + ", durabilityCorrection=" + this.getDurabilityCorrection() + ")";
   }
}
