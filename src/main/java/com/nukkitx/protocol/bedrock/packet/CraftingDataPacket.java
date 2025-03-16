package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.crafting.ContainerMixData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.MaterialReducer;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.PotionMixData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class CraftingDataPacket implements BedrockPacket {
   private final List<RecipeData> craftingData = new ObjectArrayList();
   private final List<PotionMixData> potionMixData = new ObjectArrayList();
   private final List<ContainerMixData> containerMixData = new ObjectArrayList();
   private final List<MaterialReducer> materialReducers = new ObjectArrayList();
   private boolean cleanRecipes;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CRAFTING_DATA;
   }

   public List<RecipeData> getCraftingData() {
      return this.craftingData;
   }

   public List<PotionMixData> getPotionMixData() {
      return this.potionMixData;
   }

   public List<ContainerMixData> getContainerMixData() {
      return this.containerMixData;
   }

   public List<MaterialReducer> getMaterialReducers() {
      return this.materialReducers;
   }

   public boolean isCleanRecipes() {
      return this.cleanRecipes;
   }

   public void setCleanRecipes(boolean cleanRecipes) {
      this.cleanRecipes = cleanRecipes;
   }

   public String toString() {
      return "CraftingDataPacket(craftingData=" + this.craftingData + ", potionMixData=" + this.potionMixData + ", containerMixData=" + this.containerMixData + ", materialReducers=" + this.materialReducers + ", cleanRecipes=" + this.cleanRecipes + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CraftingDataPacket)) {
         return false;
      } else {
         CraftingDataPacket other = (CraftingDataPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.cleanRecipes != other.cleanRecipes) {
            return false;
         } else {
            Object this$craftingData = this.craftingData;
            Object other$craftingData = other.craftingData;
            if (this$craftingData == null) {
               if (other$craftingData != null) {
                  return false;
               }
            } else if (!this$craftingData.equals(other$craftingData)) {
               return false;
            }

            Object this$potionMixData = this.potionMixData;
            Object other$potionMixData = other.potionMixData;
            if (this$potionMixData == null) {
               if (other$potionMixData != null) {
                  return false;
               }
            } else if (!this$potionMixData.equals(other$potionMixData)) {
               return false;
            }

            Object this$containerMixData = this.containerMixData;
            Object other$containerMixData = other.containerMixData;
            if (this$containerMixData == null) {
               if (other$containerMixData != null) {
                  return false;
               }
            } else if (!this$containerMixData.equals(other$containerMixData)) {
               return false;
            }

            Object this$materialReducers = this.materialReducers;
            Object other$materialReducers = other.materialReducers;
            if (this$materialReducers == null) {
               if (other$materialReducers != null) {
                  return false;
               }
            } else if (!this$materialReducers.equals(other$materialReducers)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CraftingDataPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.cleanRecipes ? 79 : 97);
      Object $craftingData = this.craftingData;
      result = result * 59 + ($craftingData == null ? 43 : $craftingData.hashCode());
      Object $potionMixData = this.potionMixData;
      result = result * 59 + ($potionMixData == null ? 43 : $potionMixData.hashCode());
      Object $containerMixData = this.containerMixData;
      result = result * 59 + ($containerMixData == null ? 43 : $containerMixData.hashCode());
      Object $materialReducers = this.materialReducers;
      result = result * 59 + ($materialReducers == null ? 43 : $materialReducers.hashCode());
      return result;
   }
}
