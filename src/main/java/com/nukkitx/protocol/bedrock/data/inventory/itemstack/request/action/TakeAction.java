package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotData;

public final class TakeAction implements TransferItemStackRequestAction {
   private final int count;
   private final ItemStackRequestSlotData source;
   private final ItemStackRequestSlotData destination;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.TAKE;
   }

   public TakeAction(int count, ItemStackRequestSlotData source, ItemStackRequestSlotData destination) {
      this.count = count;
      this.source = source;
      this.destination = destination;
   }

   public int getCount() {
      return this.count;
   }

   public ItemStackRequestSlotData getSource() {
      return this.source;
   }

   public ItemStackRequestSlotData getDestination() {
      return this.destination;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TakeAction)) {
         return false;
      } else {
         TakeAction other = (TakeAction)o;
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

            Object this$destination = this.getDestination();
            Object other$destination = other.getDestination();
            if (this$destination == null) {
               if (other$destination != null) {
                  return false;
               }
            } else if (!this$destination.equals(other$destination)) {
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
      Object $destination = this.getDestination();
      result = result * 59 + ($destination == null ? 43 : $destination.hashCode());
      return result;
   }

   public String toString() {
      return "TakeAction(count=" + this.getCount() + ", source=" + this.getSource() + ", destination=" + this.getDestination() + ")";
   }
}
