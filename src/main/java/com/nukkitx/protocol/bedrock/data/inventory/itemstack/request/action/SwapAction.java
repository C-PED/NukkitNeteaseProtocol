package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotData;

public final class SwapAction implements ItemStackRequestAction {
   private final ItemStackRequestSlotData source;
   private final ItemStackRequestSlotData destination;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.SWAP;
   }

   public SwapAction(ItemStackRequestSlotData source, ItemStackRequestSlotData destination) {
      this.source = source;
      this.destination = destination;
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
      } else if (!(o instanceof SwapAction)) {
         return false;
      } else {
         SwapAction other = (SwapAction)o;
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

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $source = this.getSource();
      result = result * 59 + ($source == null ? 43 : $source.hashCode());
      Object $destination = this.getDestination();
      result = result * 59 + ($destination == null ? 43 : $destination.hashCode());
      return result;
   }

   public String toString() {
      return "SwapAction(source=" + this.getSource() + ", destination=" + this.getDestination() + ")";
   }
}
