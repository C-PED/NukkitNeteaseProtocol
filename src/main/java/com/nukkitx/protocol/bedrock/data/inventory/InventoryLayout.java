package com.nukkitx.protocol.bedrock.data.inventory;

public enum InventoryLayout {
   NONE,
   SURVIVAL,
   RECIPE_BOOK,
   CREATIVE;

   public static final InventoryLayout[] VALUES = values();

   // $FF: synthetic method
   private static InventoryLayout[] $values() {
      return new InventoryLayout[]{NONE, SURVIVAL, RECIPE_BOOK, CREATIVE};
   }
}
