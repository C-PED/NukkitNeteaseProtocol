package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class ClientToServerHandshakePacket implements BedrockPacket {
   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CLIENT_TO_SERVER_HANDSHAKE;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ClientToServerHandshakePacket)) {
         return false;
      } else {
         ClientToServerHandshakePacket other = (ClientToServerHandshakePacket)o;
         return other.canEqual(this);
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ClientToServerHandshakePacket;
   }

   public int hashCode() {
      int result = 1;
      return 1;
   }

   public String toString() {
      return "ClientToServerHandshakePacket()";
   }
}
