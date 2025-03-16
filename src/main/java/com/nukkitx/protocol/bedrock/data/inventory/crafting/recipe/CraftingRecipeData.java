package com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import java.util.List;

public interface CraftingRecipeData extends TaggedCraftingData, UniqueCraftingData, IdentifiableRecipeData {
   List<ItemDescriptorWithCount> getIngredients();

   List<ItemData> getResults();

   int getPriority();
}
