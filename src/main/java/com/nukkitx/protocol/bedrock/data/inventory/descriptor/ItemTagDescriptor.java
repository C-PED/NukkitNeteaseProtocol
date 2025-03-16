package com.nukkitx.protocol.bedrock.data.inventory.descriptor;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;

public final class ItemTagDescriptor implements ItemDescriptor {
   private final String itemTag;

   public ItemDescriptorType getType() {
      return ItemDescriptorType.ITEM_TAG;
   }

   public ItemData.Builder toItem() {
      throw new UnsupportedOperationException();
   }

   public ItemTagDescriptor(String itemTag) {
      this.itemTag = itemTag;
   }

   public String getItemTag() {
      return this.itemTag;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ItemTagDescriptor)) {
         return false;
      } else {
         ItemTagDescriptor other = (ItemTagDescriptor)o;
         Object this$itemTag = this.getItemTag();
         Object other$itemTag = other.getItemTag();
         if (this$itemTag == null) {
            if (other$itemTag != null) {
               return false;
            }
         } else if (!this$itemTag.equals(other$itemTag)) {
            return false;
         }

         return true;
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $itemTag = this.getItemTag();
      result = result * 59 + ($itemTag == null ? 43 : $itemTag.hashCode());
      return result;
   }

   public String toString() {
      return "ItemTagDescriptor(itemTag=" + this.getItemTag() + ")";
   }
}
