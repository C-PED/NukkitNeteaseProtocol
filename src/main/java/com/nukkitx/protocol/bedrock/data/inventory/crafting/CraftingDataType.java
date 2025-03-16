package com.nukkitx.protocol.bedrock.data.inventory.crafting;

public enum CraftingDataType {
   SHAPELESS,
   SHAPED,
   FURNACE,
   FURNACE_DATA,
   MULTI,
   SHULKER_BOX,
   SHAPELESS_CHEMISTRY,
   SHAPED_CHEMISTRY,
   SMITHING_TRANSFORM,
   SMITHING_TRIM;

   private static final CraftingDataType[] VALUES = values();

   public static CraftingDataType byId(int id) {
      if (id >= 0 && id < VALUES.length) {
         return VALUES[id];
      } else {
         throw new UnsupportedOperationException("Unknown CraftingDataType ID: " + id);
      }
   }

   // $FF: synthetic method
   private static CraftingDataType[] $values() {
      return new CraftingDataType[]{SHAPELESS, SHAPED, FURNACE, FURNACE_DATA, MULTI, SHULKER_BOX, SHAPELESS_CHEMISTRY, SHAPED_CHEMISTRY, SMITHING_TRANSFORM, SMITHING_TRIM};
   }
}
