package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request;

import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;

public final class ItemStackRequestSlotData {
   private final ContainerSlotType container;
   private final int slot;
   private final int stackNetworkId;

   public ItemStackRequestSlotData(ContainerSlotType container, int slot, int stackNetworkId) {
      this.container = container;
      this.slot = slot;
      this.stackNetworkId = stackNetworkId;
   }

   public ContainerSlotType getContainer() {
      return this.container;
   }

   public int getSlot() {
      return this.slot;
   }

   public int getStackNetworkId() {
      return this.stackNetworkId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ItemStackRequestSlotData)) {
         return false;
      } else {
         ItemStackRequestSlotData other = (ItemStackRequestSlotData)o;
         if (this.getSlot() != other.getSlot()) {
            return false;
         } else if (this.getStackNetworkId() != other.getStackNetworkId()) {
            return false;
         } else {
            Object this$container = this.getContainer();
            Object other$container = other.getContainer();
            if (this$container == null) {
               if (other$container != null) {
                  return false;
               }
            } else if (!this$container.equals(other$container)) {
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
      result = result * 59 + this.getStackNetworkId();
      Object $container = this.getContainer();
      result = result * 59 + ($container == null ? 43 : $container.hashCode());
      return result;
   }

   public String toString() {
      return "ItemStackRequestSlotData(container=" + this.getContainer() + ", slot=" + this.getSlot() + ", stackNetworkId=" + this.getStackNetworkId() + ")";
   }
}
