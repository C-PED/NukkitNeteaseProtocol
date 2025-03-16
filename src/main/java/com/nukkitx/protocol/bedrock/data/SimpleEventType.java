package com.nukkitx.protocol.bedrock.data;

public enum SimpleEventType {
   NONE,
   ENABLE_COMMANDS,
   DISABLE_COMMANDS,
   UNLOCK_WORLD_TEMPLATE_SETTINGS;

   // $FF: synthetic method
   private static SimpleEventType[] $values() {
      return new SimpleEventType[]{NONE, ENABLE_COMMANDS, DISABLE_COMMANDS, UNLOCK_WORLD_TEMPLATE_SETTINGS};
   }
}
