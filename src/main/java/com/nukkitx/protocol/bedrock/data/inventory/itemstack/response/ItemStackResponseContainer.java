package com.nukkitx.protocol.bedrock.data.inventory.itemstack.response;

import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import java.util.List;

public final class ItemStackResponseContainer {
   private final ContainerSlotType container;
   private final List<ItemStackResponseSlot> items;

   public ItemStackResponseContainer(ContainerSlotType container, List<ItemStackResponseSlot> items) {
      this.container = container;
      this.items = items;
   }

   public ContainerSlotType getContainer() {
      return this.container;
   }

   public List<ItemStackResponseSlot> getItems() {
      return this.items;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ItemStackResponseContainer)) {
         return false;
      } else {
         ItemStackResponseContainer other = (ItemStackResponseContainer)o;
         Object this$container = this.getContainer();
         Object other$container = other.getContainer();
         if (this$container == null) {
            if (other$container != null) {
               return false;
            }
         } else if (!this$container.equals(other$container)) {
            return false;
         }

         Object this$items = this.getItems();
         Object other$items = other.getItems();
         if (this$items == null) {
            if (other$items != null) {
               return false;
            }
         } else if (!this$items.equals(other$items)) {
            return false;
         }

         return true;
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $container = this.getContainer();
      result = result * 59 + ($container == null ? 43 : $container.hashCode());
      Object $items = this.getItems();
      result = result * 59 + ($items == null ? 43 : $items.hashCode());
      return result;
   }

   public String toString() {
      return "ItemStackResponseContainer(container=" + this.getContainer() + ", items=" + this.getItems() + ")";
   }
}
