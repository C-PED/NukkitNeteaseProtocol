package com.nukkitx.protocol.bedrock.data.structure;

public enum StructureRedstoneSaveMode {
   SAVES_TO_MEMORY,
   SAVES_TO_DISK;

   private static final StructureRedstoneSaveMode[] VALUES = values();

   public static StructureRedstoneSaveMode from(int id) {
      return VALUES[id];
   }

   // $FF: synthetic method
   private static StructureRedstoneSaveMode[] $values() {
      return new StructureRedstoneSaveMode[]{SAVES_TO_MEMORY, SAVES_TO_DISK};
   }
}
