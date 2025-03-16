package com.nukkitx.protocol.bedrock.data.structure;

public enum StructureRotation {
   NONE,
   ROTATE_90,
   ROTATE_180,
   ROTATE_270;

   private static final StructureRotation[] VALUES = values();

   public static StructureRotation from(int id) {
      return VALUES[id];
   }

   // $FF: synthetic method
   private static StructureRotation[] $values() {
      return new StructureRotation[]{NONE, ROTATE_90, ROTATE_180, ROTATE_270};
   }
}
