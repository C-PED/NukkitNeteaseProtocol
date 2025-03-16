package com.nukkitx.protocol.bedrock.data.structure;

public enum StructureMirror {
   NONE,
   X,
   Z,
   XZ;

   private static final StructureMirror[] VALUES = values();

   public static StructureMirror from(int id) {
      return VALUES[id];
   }

   // $FF: synthetic method
   private static StructureMirror[] $values() {
      return new StructureMirror[]{NONE, X, Z, XZ};
   }
}
