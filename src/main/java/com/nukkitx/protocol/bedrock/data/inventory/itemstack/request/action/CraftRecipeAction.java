package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

public final class CraftRecipeAction implements RecipeItemStackRequestAction {
   private final int recipeNetworkId;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.CRAFT_RECIPE;
   }

   public CraftRecipeAction(int recipeNetworkId) {
      this.recipeNetworkId = recipeNetworkId;
   }

   public int getRecipeNetworkId() {
      return this.recipeNetworkId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CraftRecipeAction)) {
         return false;
      } else {
         CraftRecipeAction other = (CraftRecipeAction)o;
         return this.getRecipeNetworkId() == other.getRecipeNetworkId();
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getRecipeNetworkId();
      return result;
   }

   public String toString() {
      return "CraftRecipeAction(recipeNetworkId=" + this.getRecipeNetworkId() + ")";
   }
}
