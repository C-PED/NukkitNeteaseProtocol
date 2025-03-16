package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotData;

public final class ConsumeAction implements ItemStackRequestAction {
   private final int count;
   private final ItemStackRequestSlotData source;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.CONSUME;
   }

   public ConsumeAction(int count, ItemStackRequestSlotData source) {
      this.count = count;
      this.source = source;
   }

   public int getCount() {
      return this.count;
   }

   public ItemStackRequestSlotData getSource() {
      return this.source;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ConsumeAction)) {
         return false;
      } else {
         ConsumeAction other = (ConsumeAction)o;
         if (this.getCount() != other.getCount()) {
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
      Object $source = this.getSource();
      result = result * 59 + ($source == null ? 43 : $source.hashCode());
      return result;
   }

   public String toString() {
      return "ConsumeAction(count=" + this.getCount() + ", source=" + this.getSource() + ")";
   }
}
