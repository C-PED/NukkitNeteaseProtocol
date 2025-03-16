package com.nukkitx.protocol.bedrock.data.event;

import com.nukkitx.protocol.bedrock.data.BlockInteractionType;

public final class CauldronInteractEventData implements EventData {
   private final BlockInteractionType blockInteractionType;
   private final int itemId;

   public EventDataType getType() {
      return EventDataType.CAULDRON_INTERACT;
   }

   public CauldronInteractEventData(BlockInteractionType blockInteractionType, int itemId) {
      this.blockInteractionType = blockInteractionType;
      this.itemId = itemId;
   }

   public BlockInteractionType getBlockInteractionType() {
      return this.blockInteractionType;
   }

   public int getItemId() {
      return this.itemId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CauldronInteractEventData)) {
         return false;
      } else {
         CauldronInteractEventData other = (CauldronInteractEventData)o;
         if (this.getItemId() != other.getItemId()) {
            return false;
         } else {
            Object this$blockInteractionType = this.getBlockInteractionType();
            Object other$blockInteractionType = other.getBlockInteractionType();
            if (this$blockInteractionType == null) {
               if (other$blockInteractionType != null) {
                  return false;
               }
            } else if (!this$blockInteractionType.equals(other$blockInteractionType)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getItemId();
      Object $blockInteractionType = this.getBlockInteractionType();
      result = result * 59 + ($blockInteractionType == null ? 43 : $blockInteractionType.hashCode());
      return result;
   }

   public String toString() {
      return "CauldronInteractEventData(blockInteractionType=" + this.getBlockInteractionType() + ", itemId=" + this.getItemId() + ")";
   }
}
