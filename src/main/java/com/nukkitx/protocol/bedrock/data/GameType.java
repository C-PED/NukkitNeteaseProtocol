package com.nukkitx.protocol.bedrock.data;

public enum GameType {
   SURVIVAL,
   CREATIVE,
   ADVENTURE,
   /** @deprecated */
   @Deprecated
   SURVIVAL_VIEWER,
   /** @deprecated */
   @Deprecated
   CREATIVE_VIEWER,
   DEFAULT,
   SPECTATOR;

   private static final GameType[] VALUES = values();

   public static GameType from(int id) {
      return VALUES[id];
   }

   // $FF: synthetic method
   private static GameType[] $values() {
      return new GameType[]{SURVIVAL, CREATIVE, ADVENTURE, SURVIVAL_VIEWER, CREATIVE_VIEWER, DEFAULT, SPECTATOR};
   }
}
