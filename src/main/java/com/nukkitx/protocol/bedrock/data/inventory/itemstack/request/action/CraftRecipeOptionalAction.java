package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

public final class CraftRecipeOptionalAction implements ItemStackRequestAction {
   private final int recipeNetworkId;
   private final int filteredStringIndex;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.CRAFT_RECIPE_OPTIONAL;
   }

   public CraftRecipeOptionalAction(int recipeNetworkId, int filteredStringIndex) {
      this.recipeNetworkId = recipeNetworkId;
      this.filteredStringIndex = filteredStringIndex;
   }

   public int getRecipeNetworkId() {
      return this.recipeNetworkId;
   }

   public int getFilteredStringIndex() {
      return this.filteredStringIndex;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CraftRecipeOptionalAction)) {
         return false;
      } else {
         CraftRecipeOptionalAction other = (CraftRecipeOptionalAction)o;
         if (this.getRecipeNetworkId() != other.getRecipeNetworkId()) {
            return false;
         } else {
            return this.getFilteredStringIndex() == other.getFilteredStringIndex();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getRecipeNetworkId();
      result = result * 59 + this.getFilteredStringIndex();
      return result;
   }

   public String toString() {
      return "CraftRecipeOptionalAction(recipeNetworkId=" + this.getRecipeNetworkId() + ", filteredStringIndex=" + this.getFilteredStringIndex() + ")";
   }
}
