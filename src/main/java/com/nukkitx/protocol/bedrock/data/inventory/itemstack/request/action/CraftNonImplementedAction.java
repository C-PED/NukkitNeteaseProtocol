package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

public final class CraftNonImplementedAction implements ItemStackRequestAction {
   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.CRAFT_NON_IMPLEMENTED_DEPRECATED;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else {
         return o instanceof CraftNonImplementedAction;
      }
   }

   public int hashCode() {
      int result = 1;
      return 1;
   }

   public String toString() {
      return "CraftNonImplementedAction()";
   }
}
