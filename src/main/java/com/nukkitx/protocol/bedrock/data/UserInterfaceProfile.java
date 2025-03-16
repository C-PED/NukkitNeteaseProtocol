package com.nukkitx.protocol.bedrock.data;

public enum UserInterfaceProfile {
   CLASSIC,
   POCKET,
   NONE;

   private static final UserInterfaceProfile[] VALUES = values();

   public static UserInterfaceProfile from(int id) {
      return VALUES[id];
   }

   // $FF: synthetic method
   private static UserInterfaceProfile[] $values() {
      return new UserInterfaceProfile[]{CLASSIC, POCKET, NONE};
   }
}
