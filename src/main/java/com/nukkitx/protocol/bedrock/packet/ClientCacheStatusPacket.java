package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class ClientCacheStatusPacket implements BedrockPacket {
   private boolean supported;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CLIENT_CACHE_STATUS;
   }

   public boolean isSupported() {
      return this.supported;
   }

   public void setSupported(boolean supported) {
      this.supported = supported;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ClientCacheStatusPacket)) {
         return false;
      } else {
         ClientCacheStatusPacket other = (ClientCacheStatusPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.supported == other.supported;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ClientCacheStatusPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.supported ? 79 : 97);
      return result;
   }

   public String toString() {
      return "ClientCacheStatusPacket(supported=" + this.supported + ")";
   }
}
