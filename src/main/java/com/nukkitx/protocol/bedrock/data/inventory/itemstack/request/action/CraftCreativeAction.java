package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

public final class CraftCreativeAction implements ItemStackRequestAction {
   private final int creativeItemNetworkId;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.CRAFT_CREATIVE;
   }

   public CraftCreativeAction(int creativeItemNetworkId) {
      this.creativeItemNetworkId = creativeItemNetworkId;
   }

   public int getCreativeItemNetworkId() {
      return this.creativeItemNetworkId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CraftCreativeAction)) {
         return false;
      } else {
         CraftCreativeAction other = (CraftCreativeAction)o;
         return this.getCreativeItemNetworkId() == other.getCreativeItemNetworkId();
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getCreativeItemNetworkId();
      return result;
   }

   public String toString() {
      return "CraftCreativeAction(creativeItemNetworkId=" + this.getCreativeItemNetworkId() + ")";
   }
}
