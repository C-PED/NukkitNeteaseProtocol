package com.nukkitx.protocol.bedrock.data;

public enum ResourcePackType {
   INVALID,
   RESOURCES,
   DATA_ADD_ON,
   WORLD_TEMPLATE,
   ADDON,
   SKINS,
   CACHED,
   COPY_PROTECTED,
   PERSONA_PIECE;

   // $FF: synthetic method
   private static ResourcePackType[] $values() {
      return new ResourcePackType[]{INVALID, RESOURCES, DATA_ADD_ON, WORLD_TEMPLATE, ADDON, SKINS, CACHED, COPY_PROTECTED, PERSONA_PIECE};
   }
}
