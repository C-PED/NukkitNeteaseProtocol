package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import java.util.List;

public final class AutoCraftRecipeAction implements RecipeItemStackRequestAction {
   private final int recipeNetworkId;
   private final int timesCrafted;
   private final List<ItemDescriptorWithCount> ingredients;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.CRAFT_RECIPE_AUTO;
   }

   public AutoCraftRecipeAction(int recipeNetworkId, int timesCrafted, List<ItemDescriptorWithCount> ingredients) {
      this.recipeNetworkId = recipeNetworkId;
      this.timesCrafted = timesCrafted;
      this.ingredients = ingredients;
   }

   public int getRecipeNetworkId() {
      return this.recipeNetworkId;
   }

   public int getTimesCrafted() {
      return this.timesCrafted;
   }

   public List<ItemDescriptorWithCount> getIngredients() {
      return this.ingredients;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AutoCraftRecipeAction)) {
         return false;
      } else {
         AutoCraftRecipeAction other = (AutoCraftRecipeAction)o;
         if (this.getRecipeNetworkId() != other.getRecipeNetworkId()) {
            return false;
         } else if (this.getTimesCrafted() != other.getTimesCrafted()) {
            return false;
         } else {
            Object this$ingredients = this.getIngredients();
            Object other$ingredients = other.getIngredients();
            if (this$ingredients == null) {
               if (other$ingredients != null) {
                  return false;
               }
            } else if (!this$ingredients.equals(other$ingredients)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getRecipeNetworkId();
      result = result * 59 + this.getTimesCrafted();
      Object $ingredients = this.getIngredients();
      result = result * 59 + ($ingredients == null ? 43 : $ingredients.hashCode());
      return result;
   }

   public String toString() {
      return "AutoCraftRecipeAction(recipeNetworkId=" + this.getRecipeNetworkId() + ", timesCrafted=" + this.getTimesCrafted() + ", ingredients=" + this.getIngredients() + ")";
   }
}
