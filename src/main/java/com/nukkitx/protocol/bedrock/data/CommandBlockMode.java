package com.nukkitx.protocol.bedrock.data;

public enum CommandBlockMode {
   NORMAL,
   REPEATING,
   CHAIN;

   // $FF: synthetic method
   private static CommandBlockMode[] $values() {
      return new CommandBlockMode[]{NORMAL, REPEATING, CHAIN};
   }
}
