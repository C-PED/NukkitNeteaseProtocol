package com.nukkitx.protocol.bedrock.data;

public enum SimulationType {
   GAME,
   EDITOR,
   TEST;

   // $FF: synthetic method
   private static SimulationType[] $values() {
      return new SimulationType[]{GAME, EDITOR, TEST};
   }
}
