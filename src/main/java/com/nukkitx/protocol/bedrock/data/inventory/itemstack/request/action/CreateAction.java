package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

public final class CreateAction implements ItemStackRequestAction {
   private final int slot;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.CREATE;
   }

   public CreateAction(int slot) {
      this.slot = slot;
   }

   public int getSlot() {
      return this.slot;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CreateAction)) {
         return false;
      } else {
         CreateAction other = (CreateAction)o;
         return this.getSlot() == other.getSlot();
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getSlot();
      return result;
   }

   public String toString() {
      return "CreateAction(slot=" + this.getSlot() + ")";
   }
}
