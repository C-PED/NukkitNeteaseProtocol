package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

public final class LabTableCombineAction implements ItemStackRequestAction {
   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.LAB_TABLE_COMBINE;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else {
         return o instanceof LabTableCombineAction;
      }
   }

   public int hashCode() {
      int result = 1;
      return 1;
   }

   public String toString() {
      return "LabTableCombineAction()";
   }
}
