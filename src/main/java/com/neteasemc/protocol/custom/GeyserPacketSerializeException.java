package com.neteasemc.protocol.custom;

public class GeyserPacketSerializeException extends RuntimeException {
   public GeyserPacketSerializeException(Throwable e) {
      super(e);
   }

   public GeyserPacketSerializeException(String message, Throwable e) {
      super(message, e);
   }
}
