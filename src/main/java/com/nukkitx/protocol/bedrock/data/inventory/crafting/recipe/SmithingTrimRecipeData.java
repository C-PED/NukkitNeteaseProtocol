package com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe;

import com.nukkitx.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;

public class SmithingTrimRecipeData implements TaggedCraftingData, IdentifiableRecipeData, NetworkRecipeData {
   private final String id;
   private final ItemDescriptorWithCount base;
   private final ItemDescriptorWithCount addition;
   private final ItemDescriptorWithCount template;
   private final String tag;
   private final int netId;

   public CraftingDataType getType() {
      return CraftingDataType.SMITHING_TRIM;
   }

   public static SmithingTrimRecipeData of(String id, ItemDescriptorWithCount base, ItemDescriptorWithCount addition, ItemDescriptorWithCount template, String tag, int netId) {
      return new SmithingTrimRecipeData(id, base, addition, template, tag, netId);
   }

   public String getId() {
      return this.id;
   }

   public ItemDescriptorWithCount getBase() {
      return this.base;
   }

   public ItemDescriptorWithCount getAddition() {
      return this.addition;
   }

   public ItemDescriptorWithCount getTemplate() {
      return this.template;
   }

   public String getTag() {
      return this.tag;
   }

   public int getNetId() {
      return this.netId;
   }

   public String toString() {
      return "SmithingTrimRecipeData(id=" + this.getId() + ", base=" + this.getBase() + ", addition=" + this.getAddition() + ", template=" + this.getTemplate() + ", tag=" + this.getTag() + ", netId=" + this.getNetId() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SmithingTrimRecipeData)) {
         return false;
      } else {
         SmithingTrimRecipeData other = (SmithingTrimRecipeData)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getNetId() != other.getNetId()) {
            return false;
         } else {
            Object this$id = this.getId();
            Object other$id = other.getId();
            if (this$id == null) {
               if (other$id != null) {
                  return false;
               }
            } else if (!this$id.equals(other$id)) {
               return false;
            }

            Object this$base = this.getBase();
            Object other$base = other.getBase();
            if (this$base == null) {
               if (other$base != null) {
                  return false;
               }
            } else if (!this$base.equals(other$base)) {
               return false;
            }

            Object this$addition = this.getAddition();
            Object other$addition = other.getAddition();
            if (this$addition == null) {
               if (other$addition != null) {
                  return false;
               }
            } else if (!this$addition.equals(other$addition)) {
               return false;
            }

            Object this$template = this.getTemplate();
            Object other$template = other.getTemplate();
            if (this$template == null) {
               if (other$template != null) {
                  return false;
               }
            } else if (!this$template.equals(other$template)) {
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
      return other instanceof SmithingTrimRecipeData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getNetId();
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $base = this.getBase();
      result = result * 59 + ($base == null ? 43 : $base.hashCode());
      Object $addition = this.getAddition();
      result = result * 59 + ($addition == null ? 43 : $addition.hashCode());
      Object $template = this.getTemplate();
      result = result * 59 + ($template == null ? 43 : $template.hashCode());
      Object $tag = this.getTag();
      result = result * 59 + ($tag == null ? 43 : $tag.hashCode());
      return result;
   }

   private SmithingTrimRecipeData(String id, ItemDescriptorWithCount base, ItemDescriptorWithCount addition, ItemDescriptorWithCount template, String tag, int netId) {
      this.id = id;
      this.base = base;
      this.addition = addition;
      this.template = template;
      this.tag = tag;
      this.netId = netId;
   }
}
