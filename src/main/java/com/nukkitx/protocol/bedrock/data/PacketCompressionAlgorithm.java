package com.nukkitx.protocol.bedrock.data;

public enum PacketCompressionAlgorithm implements CompressionAlgorithm {
   ZLIB,
   SNAPPY,
   NONE;

   // $FF: synthetic method
   private static PacketCompressionAlgorithm[] $values() {
      return new PacketCompressionAlgorithm[]{ZLIB, SNAPPY, NONE};
   }
}
