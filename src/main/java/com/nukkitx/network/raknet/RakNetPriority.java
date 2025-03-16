package com.nukkitx.network.raknet;

public enum RakNetPriority {
   IMMEDIATE,
   HIGH,
   MEDIUM,
   LOW;

   // $FF: synthetic method
   private static RakNetPriority[] $values() {
      return new RakNetPriority[]{IMMEDIATE, HIGH, MEDIUM, LOW};
   }
}
