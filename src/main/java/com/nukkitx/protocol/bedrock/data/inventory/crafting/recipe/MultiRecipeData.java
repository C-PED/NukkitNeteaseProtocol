package com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe;

import com.nukkitx.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import java.util.UUID;

public class MultiRecipeData implements UniqueCraftingData {
   private final UUID uuid;
   private final int netId;

   public CraftingDataType getType() {
      return CraftingDataType.MULTI;
   }

   public static MultiRecipeData of(UUID uuid, int netId) {
      return new MultiRecipeData(uuid, netId);
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public int getNetId() {
      return this.netId;
   }

   public String toString() {
      return "MultiRecipeData(uuid=" + this.getUuid() + ", netId=" + this.getNetId() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MultiRecipeData)) {
         return false;
      } else {
         MultiRecipeData other = (MultiRecipeData)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getNetId() != other.getNetId()) {
            return false;
         } else {
            Object this$uuid = this.getUuid();
            Object other$uuid = other.getUuid();
            if (this$uuid == null) {
               if (other$uuid != null) {
                  return false;
               }
            } else if (!this$uuid.equals(other$uuid)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MultiRecipeData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getNetId();
      Object $uuid = this.getUuid();
      result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
      return result;
   }

   private MultiRecipeData(UUID uuid, int netId) {
      this.uuid = uuid;
      this.netId = netId;
   }
}
