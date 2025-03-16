package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.annotation.NoEncryption;
import com.nukkitx.protocol.common.PacketSignal;

@NoEncryption
public class ServerToClientHandshakePacket implements BedrockPacket {
   private String jwt;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SERVER_TO_CLIENT_HANDSHAKE;
   }

   public String getJwt() {
      return this.jwt;
   }

   public void setJwt(String jwt) {
      this.jwt = jwt;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ServerToClientHandshakePacket)) {
         return false;
      } else {
         ServerToClientHandshakePacket other = (ServerToClientHandshakePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$jwt = this.jwt;
            Object other$jwt = other.jwt;
            if (this$jwt == null) {
               if (other$jwt != null) {
                  return false;
               }
            } else if (!this$jwt.equals(other$jwt)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ServerToClientHandshakePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $jwt = this.jwt;
      result = result * 59 + ($jwt == null ? 43 : $jwt.hashCode());
      return result;
   }

   public String toString() {
      return "ServerToClientHandshakePacket(jwt=" + this.jwt + ")";
   }
}
