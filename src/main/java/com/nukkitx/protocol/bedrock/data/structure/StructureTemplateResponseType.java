package com.nukkitx.protocol.bedrock.data.structure;

public enum StructureTemplateResponseType {
   NONE,
   EXPORT,
   QUERY,
   IMPORT;

   private static final StructureTemplateResponseType[] VALUES = values();

   public static StructureTemplateResponseType from(int id) {
      return VALUES[id];
   }

   // $FF: synthetic method
   private static StructureTemplateResponseType[] $values() {
      return new StructureTemplateResponseType[]{NONE, EXPORT, QUERY, IMPORT};
   }
}
