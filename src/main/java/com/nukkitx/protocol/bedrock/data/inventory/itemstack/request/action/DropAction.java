package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotData;

public final class DropAction implements ItemStackRequestAction {
   private final int count;
   private final ItemStackRequestSlotData source;
   private final boolean randomly;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.DROP;
   }

   public DropAction(int count, ItemStackRequestSlotData source, boolean randomly) {
      this.count = count;
      this.source = source;
      this.randomly = randomly;
   }

   public int getCount() {
      return this.count;
   }

   public ItemStackRequestSlotData getSource() {
      return this.source;
   }

   public boolean isRandomly() {
      return this.randomly;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DropAction)) {
         return false;
      } else {
         DropAction other = (DropAction)o;
         if (this.getCount() != other.getCount()) {
            return false;
         } else if (this.isRandomly() != other.isRandomly()) {
            return false;
         } else {
            Object this$source = this.getSource();
            Object other$source = other.getSource();
            if (this$source == null) {
               if (other$source != null) {
                  return false;
               }
            } else if (!this$source.equals(other$source)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getCount();
      result = result * 59 + (this.isRandomly() ? 79 : 97);
      Object $source = this.getSource();
      result = result * 59 + ($source == null ? 43 : $source.hashCode());
      return result;
   }

   public String toString() {
      return "DropAction(count=" + this.getCount() + ", source=" + this.getSource() + ", randomly=" + this.isRandomly() + ")";
   }
}
