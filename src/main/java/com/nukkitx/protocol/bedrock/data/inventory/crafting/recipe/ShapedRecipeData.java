package com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import com.nukkitx.protocol.common.util.Preconditions;
import java.util.List;
import java.util.UUID;

public class ShapedRecipeData implements CraftingRecipeData {
   private final CraftingDataType type;
   private final String id;
   private final int width;
   private final int height;
   private final List<ItemDescriptorWithCount> ingredients;
   private final List<ItemData> results;
   private final UUID uuid;
   private final String tag;
   private final int priority;
   private final int netId;
   private final boolean assumeSymetry;

   public static ShapedRecipeData of(CraftingDataType type, String id, int width, int height, List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid, String tag, int priority, int netId) {
      Preconditions.checkArgument(type == CraftingDataType.SHAPED || type == CraftingDataType.SHAPED_CHEMISTRY, "type must be SHAPED or SHAPED_CHEMISTRY");
      return new ShapedRecipeData(type, id, width, height, ingredients, results, uuid, tag, priority, netId, false);
   }

   public static ShapedRecipeData of(CraftingDataType type, String id, int width, int height, List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid, String tag, int priority, int netId, boolean assumeSymetry) {
      Preconditions.checkArgument(type == CraftingDataType.SHAPED || type == CraftingDataType.SHAPED_CHEMISTRY, "type must be SHAPED or SHAPED_CHEMISTRY");
      return new ShapedRecipeData(type, id, width, height, ingredients, results, uuid, tag, priority, netId, assumeSymetry);
   }

   public static ShapedRecipeData shaped(String id, int width, int height, List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid, String tag, int priority, int netId, boolean assumeSymetry) {
      return of(CraftingDataType.SHAPED, id, width, height, ingredients, results, uuid, tag, priority, netId, assumeSymetry);
   }

   public static ShapedRecipeData shapedChemistry(String id, int width, int height, List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid, String tag, int priority, int netId, boolean assumeSymetry) {
      return of(CraftingDataType.SHAPED_CHEMISTRY, id, width, height, ingredients, results, uuid, tag, priority, netId, assumeSymetry);
   }

   public CraftingDataType getType() {
      return this.type;
   }

   public String getId() {
      return this.id;
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
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

   public boolean isAssumeSymetry() {
      return this.assumeSymetry;
   }

   public String toString() {
      return "ShapedRecipeData(type=" + this.getType() + ", id=" + this.getId() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ", ingredients=" + this.getIngredients() + ", results=" + this.getResults() + ", uuid=" + this.getUuid() + ", tag=" + this.getTag() + ", priority=" + this.getPriority() + ", netId=" + this.getNetId() + ", assumeSymetry=" + this.isAssumeSymetry() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ShapedRecipeData)) {
         return false;
      } else {
         ShapedRecipeData other = (ShapedRecipeData)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getWidth() != other.getWidth()) {
            return false;
         } else if (this.getHeight() != other.getHeight()) {
            return false;
         } else if (this.getPriority() != other.getPriority()) {
            return false;
         } else if (this.getNetId() != other.getNetId()) {
            return false;
         } else if (this.isAssumeSymetry() != other.isAssumeSymetry()) {
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
      return other instanceof ShapedRecipeData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getWidth();
      result = result * 59 + this.getHeight();
      result = result * 59 + this.getPriority();
      result = result * 59 + this.getNetId();
      result = result * 59 + (this.isAssumeSymetry() ? 79 : 97);
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

   private ShapedRecipeData(CraftingDataType type, String id, int width, int height, List<ItemDescriptorWithCount> ingredients, List<ItemData> results, UUID uuid, String tag, int priority, int netId, boolean assumeSymetry) {
      this.type = type;
      this.id = id;
      this.width = width;
      this.height = height;
      this.ingredients = ingredients;
      this.results = results;
      this.uuid = uuid;
      this.tag = tag;
      this.priority = priority;
      this.netId = netId;
      this.assumeSymetry = assumeSymetry;
   }
}
