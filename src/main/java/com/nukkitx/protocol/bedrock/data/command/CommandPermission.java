package com.nukkitx.protocol.bedrock.data.command;

public enum CommandPermission {
   ANY,
   GAME_DIRECTORS,
   ADMIN,
   HOST,
   OWNER,
   INTERNAL;

   // $FF: synthetic method
   private static CommandPermission[] $values() {
      return new CommandPermission[]{ANY, GAME_DIRECTORS, ADMIN, HOST, OWNER, INTERNAL};
   }
}
