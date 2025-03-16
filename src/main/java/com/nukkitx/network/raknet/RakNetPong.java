package com.nukkitx.network.raknet;

public class RakNetPong {
   private final long pingTime;
   private final long pongTime;
   private final long guid;
   private final byte[] userData;

   public long getPingTime() {
      return this.pingTime;
   }

   public long getPongTime() {
      return this.pongTime;
   }

   public long getGuid() {
      return this.guid;
   }

   public byte[] getUserData() {
      return this.userData;
   }

   public RakNetPong(long pingTime, long pongTime, long guid, byte[] userData) {
      this.pingTime = pingTime;
      this.pongTime = pongTime;
      this.guid = guid;
      this.userData = userData;
   }
}
