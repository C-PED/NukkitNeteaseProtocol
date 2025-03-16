package com.nukkitx.protocol.bedrock.data;

public enum PacketViolationSeverity {
   UNKNOWN,
   WARNING,
   FINAL_WARNING,
   TERMINATING_CONNECTION;

   // $FF: synthetic method
   private static PacketViolationSeverity[] $values() {
      return new PacketViolationSeverity[]{UNKNOWN, WARNING, FINAL_WARNING, TERMINATING_CONNECTION};
   }
}
