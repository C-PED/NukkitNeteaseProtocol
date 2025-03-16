package com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import com.nukkitx.protocol.common.util.Preconditions;

public class FurnaceRecipeData implements TaggedCraftingData {
   private final CraftingDataType type;
   private final int inputId;
   private final int inputData;
   private final ItemData result;
   private final String tag;

   public boolean hasData() {
      return this.type == CraftingDataType.FURNACE_DATA;
   }

   public static FurnaceRecipeData of(CraftingDataType type, int inputId, int inputData, ItemData result, String tag) {
      Preconditions.checkArgument(type == CraftingDataType.FURNACE || type == CraftingDataType.FURNACE_DATA, "type must be FURNACE or FURNACE_DATA");
      return new FurnaceRecipeData(type, inputId, inputData, result, tag);
   }

   public static FurnaceRecipeData of(int inputId, ItemData result, String tag) {
      return new FurnaceRecipeData(CraftingDataType.FURNACE, inputId, -1, result, tag);
   }

   public static FurnaceRecipeData of(int inputId, int inputData, ItemData result, String tag) {
      return new FurnaceRecipeData(CraftingDataType.FURNACE_DATA, inputId, inputData, result, tag);
   }

   public CraftingDataType getType() {
      return this.type;
   }

   public int getInputId() {
      return this.inputId;
   }

   public int getInputData() {
      return this.inputData;
   }

   public ItemData getResult() {
      return this.result;
   }

   public String getTag() {
      return this.tag;
   }

   public String toString() {
      return "FurnaceRecipeData(type=" + this.getType() + ", inputId=" + this.getInputId() + ", inputData=" + this.getInputData() + ", result=" + this.getResult() + ", tag=" + this.getTag() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FurnaceRecipeData)) {
         return false;
      } else {
         FurnaceRecipeData other = (FurnaceRecipeData)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getInputId() != other.getInputId()) {
            return false;
         } else if (this.getInputData() != other.getInputData()) {
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

            Object this$result = this.getResult();
            Object other$result = other.getResult();
            if (this$result == null) {
               if (other$result != null) {
                  return false;
               }
            } else if (!this$result.equals(other$result)) {
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
      return other instanceof FurnaceRecipeData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getInputId();
      result = result * 59 + this.getInputData();
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $result = this.getResult();
      result = result * 59 + ($result == null ? 43 : $result.hashCode());
      Object $tag = this.getTag();
      result = result * 59 + ($tag == null ? 43 : $tag.hashCode());
      return result;
   }

   private FurnaceRecipeData(CraftingDataType type, int inputId, int inputData, ItemData result, String tag) {
      this.type = type;
      this.inputId = inputId;
      this.inputData = inputData;
      this.result = result;
      this.tag = tag;
   }
}
