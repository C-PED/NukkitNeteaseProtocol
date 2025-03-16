package com.nukkitx.protocol.bedrock.data;

public enum CodeBuilderOperationType {
   NONE,
   GET,
   SET,
   RESET;

   // $FF: synthetic method
   private static CodeBuilderOperationType[] $values() {
      return new CodeBuilderOperationType[]{NONE, GET, SET, RESET};
   }
}
