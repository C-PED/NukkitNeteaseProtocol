package com.nukkitx.protocol.bedrock.data.structure;

public enum StructureTemplateRequestOperation {
   NONE,
   EXPORT_FROM_SAVED_MODE,
   EXPORT_FROM_LOAD_MODE,
   QUERY_SAVED_STRUCTURE,
   IMPORT;

   private static final StructureTemplateRequestOperation[] VALUES = values();

   public static StructureTemplateRequestOperation from(int id) {
      return VALUES[id];
   }

   // $FF: synthetic method
   private static StructureTemplateRequestOperation[] $values() {
      return new StructureTemplateRequestOperation[]{NONE, EXPORT_FROM_SAVED_MODE, EXPORT_FROM_LOAD_MODE, QUERY_SAVED_STRUCTURE, IMPORT};
   }
}
