package com.nukkitx.network.raknet.proxy;

public enum HAProxyProtocolVersion implements ProxyProtocolConstants {
   V1((byte)16),
   V2((byte)32);

   private static final byte VERSION_MASK = -16;
   private final byte byteValue;

   private HAProxyProtocolVersion(byte byteValue) {
      this.byteValue = byteValue;
   }

   public static HAProxyProtocolVersion valueOf(byte verCmdByte) {
      int version = verCmdByte & -16;
      switch ((byte)version) {
         case 16:
            return V1;
         case 32:
            return V2;
         default:
            throw new IllegalArgumentException("unknown version: " + version);
      }
   }

   public byte byteValue() {
      return this.byteValue;
   }

   // $FF: synthetic method
   private static HAProxyProtocolVersion[] $values() {
      return new HAProxyProtocolVersion[]{V1, V2};
   }
}
