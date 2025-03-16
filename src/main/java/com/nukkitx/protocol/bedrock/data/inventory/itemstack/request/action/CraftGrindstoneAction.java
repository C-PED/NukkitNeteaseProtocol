package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

public final class CraftGrindstoneAction implements ItemStackRequestAction {
   private final int recipeNetworkId;
   private final int repairCost;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT;
   }

   public CraftGrindstoneAction(int recipeNetworkId, int repairCost) {
      this.recipeNetworkId = recipeNetworkId;
      this.repairCost = repairCost;
   }

   public int getRecipeNetworkId() {
      return this.recipeNetworkId;
   }

   public int getRepairCost() {
      return this.repairCost;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CraftGrindstoneAction)) {
         return false;
      } else {
         CraftGrindstoneAction other = (CraftGrindstoneAction)o;
         if (this.getRecipeNetworkId() != other.getRecipeNetworkId()) {
            return false;
         } else {
            return this.getRepairCost() == other.getRepairCost();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getRecipeNetworkId();
      result = result * 59 + this.getRepairCost();
      return result;
   }

   public String toString() {
      return "CraftGrindstoneAction(recipeNetworkId=" + this.getRecipeNetworkId() + ", repairCost=" + this.getRepairCost() + ")";
   }
}
