package com.nukkitx.protocol.bedrock.data.inventory.descriptor;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;

public class ItemDescriptorWithCount {
   public static final ItemDescriptorWithCount EMPTY;
   private final ItemDescriptor descriptor;
   private final int count;

   public ItemData toItem() {
      return this.descriptor == InvalidDescriptor.INSTANCE ? ItemData.AIR : this.descriptor.toItem().count(this.count).build();
   }

   public static ItemDescriptorWithCount fromItem(ItemData item) {
      return item == ItemData.AIR ? EMPTY : new ItemDescriptorWithCount(new DefaultDescriptor(item.getDefinition(), item.getDamage()), item.getCount());
   }

   public ItemDescriptor getDescriptor() {
      return this.descriptor;
   }

   public int getCount() {
      return this.count;
   }

   public String toString() {
      return "ItemDescriptorWithCount(descriptor=" + this.getDescriptor() + ", count=" + this.getCount() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ItemDescriptorWithCount)) {
         return false;
      } else {
         ItemDescriptorWithCount other = (ItemDescriptorWithCount)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getCount() != other.getCount()) {
            return false;
         } else {
            Object this$descriptor = this.getDescriptor();
            Object other$descriptor = other.getDescriptor();
            if (this$descriptor == null) {
               if (other$descriptor != null) {
                  return false;
               }
            } else if (!this$descriptor.equals(other$descriptor)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ItemDescriptorWithCount;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getCount();
      Object $descriptor = this.getDescriptor();
      result = result * 59 + ($descriptor == null ? 43 : $descriptor.hashCode());
      return result;
   }

   public ItemDescriptorWithCount(ItemDescriptor descriptor, int count) {
      this.descriptor = descriptor;
      this.count = count;
   }

   static {
      EMPTY = new ItemDescriptorWithCount(InvalidDescriptor.INSTANCE, 0);
   }
}
