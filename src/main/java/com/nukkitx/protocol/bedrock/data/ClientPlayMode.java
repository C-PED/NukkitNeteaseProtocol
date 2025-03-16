package com.nukkitx.protocol.bedrock.data;

public enum ClientPlayMode {
   NORMAL,
   TEASER,
   SCREEN,
   VIEWER,
   REALITY,
   PLACEMENT,
   LIVING_ROOM,
   EXIT_LEVEL,
   EXIT_LEVEL_LIVING_ROOM;

   // $FF: synthetic method
   private static ClientPlayMode[] $values() {
      return new ClientPlayMode[]{NORMAL, TEASER, SCREEN, VIEWER, REALITY, PLACEMENT, LIVING_ROOM, EXIT_LEVEL, EXIT_LEVEL_LIVING_ROOM};
   }
}
