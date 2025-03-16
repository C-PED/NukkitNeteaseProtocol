package com.nukkitx.protocol.bedrock.data.inventory.descriptor;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;

public class InvalidDescriptor implements ItemDescriptor {
   public static final InvalidDescriptor INSTANCE = new InvalidDescriptor();

   public ItemDescriptorType getType() {
      return ItemDescriptorType.INVALID;
   }

   public ItemData.Builder toItem() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      return "InvalidDescriptor()";
   }

   private InvalidDescriptor() {
   }
}
