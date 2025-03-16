package com.nukkitx.network.raknet;

public enum RakNetState {
   UNCONNECTED,
   INITIALIZING,
   INITIALIZED,
   CONNECTING,
   CONNECTED;

   // $FF: synthetic method
   private static RakNetState[] $values() {
      return new RakNetState[]{UNCONNECTED, INITIALIZING, INITIALIZED, CONNECTING, CONNECTED};
   }
}
