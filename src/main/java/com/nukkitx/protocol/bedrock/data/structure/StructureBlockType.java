package com.nukkitx.protocol.bedrock.data.structure;

public enum StructureBlockType {
   DATA,
   SAVE,
   LOAD,
   CORNER,
   INVALID,
   EXPORT;

   private static final StructureBlockType[] VALUES = values();

   public static StructureBlockType from(int id) {
      return VALUES[id];
   }

   // $FF: synthetic method
   private static StructureBlockType[] $values() {
      return new StructureBlockType[]{DATA, SAVE, LOAD, CORNER, INVALID, EXPORT};
   }
}
