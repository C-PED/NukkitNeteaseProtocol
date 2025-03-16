package com.nukkitx.protocol.bedrock.data;

public enum PacketViolationType {
   UNKNOWN,
   MALFORMED_PACKET;

   // $FF: synthetic method
   private static PacketViolationType[] $values() {
      return new PacketViolationType[]{UNKNOWN, MALFORMED_PACKET};
   }
}
