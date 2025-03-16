package com.nukkitx.protocol.bedrock.data;

public enum AuthoritativeMovementMode {
   CLIENT,
   SERVER,
   SERVER_WITH_REWIND;

   // $FF: synthetic method
   private static AuthoritativeMovementMode[] $values() {
      return new AuthoritativeMovementMode[]{CLIENT, SERVER, SERVER_WITH_REWIND};
   }
}
