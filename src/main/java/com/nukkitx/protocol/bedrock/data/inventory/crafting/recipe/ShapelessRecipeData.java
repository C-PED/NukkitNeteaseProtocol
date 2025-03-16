package com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import com.nukkitx.protocol.common.util.Preconditions;
import java.util.List;
import java.util.UUID;

public class ShapelessRecipeData implements CraftingRecipeData {
   private final CraftingDataType type;
   private final String id;
   private final List<ItemDescriptorWithCount> ingredients;
   private final List<ItemData> results;
   private final UUID uuid;
   private final String tag;
   private final int priority;
   private final int netId;

   public static ShapelessRecipeData of(CraftingDataType type, String id, List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid, String tag, int priority, int netId) {
      Preconditions.checkArgument(type == CraftingDataType.SHAPELESS || type == CraftingDataType.SHAPELESS_CHEMISTRY || type == CraftingDataType.SHULKER_BOX, "type must be SHAPELESS, SHAPELESS_CHEMISTRY or SHULKER_BOX");
      return new ShapelessRecipeData(type, id, ingredients, results, uuid, tag, priority, netId);
   }

   public static ShapelessRecipeData shapeless(String id, List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid, String tag, int priority, int netId) {
      return of(CraftingDataType.SHAPELESS, id, ingredients, results, uuid, tag, priority, netId);
   }

   public static ShapelessRecipeData shapelessChemistry(String id, List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid, String tag, int priority, int netId) {
      return of(CraftingDataType.SHAPELESS_CHEMISTRY, id, ingredients, results, uuid, tag, priority, netId);
   }

   public static ShapelessRecipeData shulkerBox(String id, List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid, String tag, int priority, int netId) {
      return of(CraftingDataType.SHULKER_BOX, id, ingredients, results, uuid, tag, priority, netId);
   }

   public CraftingDataType getType() {
      return this.type;
   }

   public String getId() {
      return this.id;
   }

   public List<ItemDescriptorWithCount> getIngredients() {
      return this.ingredients;
   }

   public List<ItemData> getResults() {
      return this.results;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public String getTag() {
      return this.tag;
   }

   public int getPriority() {
      return this.priority;
   }

   public int getNetId() {
      return this.netId;
   }

   public String toString() {
      return "ShapelessRecipeData(type=" + this.getType() + ", id=" + this.getId() + ", ingredients=" + this.getIngredients() + ", results=" + this.getResults() + ", uuid=" + this.getUuid() + ", tag=" + this.getTag() + ", priority=" + this.getPriority() + ", netId=" + this.getNetId() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ShapelessRecipeData)) {
         return false;
      } else {
         ShapelessRecipeData other = (ShapelessRecipeData)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getPriority() != other.getPriority()) {
            return false;
         } else if (this.getNetId() != other.getNetId()) {
            return false;
         } else {
            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$id = this.getId();
            Object other$id = other.getId();
            if (this$id == null) {
               if (other$id != null) {
                  return false;
               }
            } else if (!this$id.equals(other$id)) {
               return false;
            }

            Object this$ingredients = this.getIngredients();
            Object other$ingredients = other.getIngredients();
            if (this$ingredients == null) {
               if (other$ingredients != null) {
                  return false;
               }
            } else if (!this$ingredients.equals(other$ingredients)) {
               return false;
            }

            Object this$results = this.getResults();
            Object other$results = other.getResults();
            if (this$results == null) {
               if (other$results != null) {
                  return false;
               }
            } else if (!this$results.equals(other$results)) {
               return false;
            }

            Object this$uuid = this.getUuid();
            Object other$uuid = other.getUuid();
            if (this$uuid == null) {
               if (other$uuid != null) {
                  return false;
               }
            } else if (!this$uuid.equals(other$uuid)) {
               return false;
            }

            Object this$tag = this.getTag();
            Object other$tag = other.getTag();
            if (this$tag == null) {
               if (other$tag != null) {
                  return false;
               }
            } else if (!this$tag.equals(other$tag)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ShapelessRecipeData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getPriority();
      result = result * 59 + this.getNetId();
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $ingredients = this.getIngredients();
      result = result * 59 + ($ingredients == null ? 43 : $ingredients.hashCode());
      Object $results = this.getResults();
      result = result * 59 + ($results == null ? 43 : $results.hashCode());
      Object $uuid = this.getUuid();
      result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
      Object $tag = this.getTag();
      result = result * 59 + ($tag == null ? 43 : $tag.hashCode());
      return result;
   }

   private ShapelessRecipeData(CraftingDataType type, String id, List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid, String tag, int priority, int netId) {
      this.type = type;
      this.id = id;
      this.ingredients = ingredients;
      this.results = results;
      this.uuid = uuid;
      this.tag = tag;
      this.priority = priority;
      this.netId = netId;
   }
}
