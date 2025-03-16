package com.nukkitx.protocol.bedrock.data.inventory.descriptor;

public enum ItemDescriptorType {
   INVALID,
   DEFAULT,
   MOLANG,
   ITEM_TAG,
   DEFERRED,
   COMPLEX_ALIAS;

   // $FF: synthetic method
   private static ItemDescriptorType[] $values() {
      return new ItemDescriptorType[]{INVALID, DEFAULT, MOLANG, ITEM_TAG, DEFERRED, COMPLEX_ALIAS};
   }
}
