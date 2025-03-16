package com.nukkitx.protocol.bedrock.data;

public enum PacketRecipient {
   CLIENT,
   SERVER,
   BOTH;

   // $FF: synthetic method
   private static PacketRecipient[] $values() {
      return new PacketRecipient[]{CLIENT, SERVER, BOTH};
   }
}
