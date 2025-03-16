package com.nukkitx.protocol.bedrock.data.attribute;

public enum AttributeOperation {
   ADDITION,
   MULTIPLY_BASE,
   MULTIPLY_TOTAL,
   CAP,
   INVALID;

   // $FF: synthetic method
   private static AttributeOperation[] $values() {
      return new AttributeOperation[]{ADDITION, MULTIPLY_BASE, MULTIPLY_TOTAL, CAP, INVALID};
   }
}
