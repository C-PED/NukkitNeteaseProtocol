package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class RefreshEntitlementsPacket implements BedrockPacket {
   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.REFRESH_ENTITLEMENTS;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RefreshEntitlementsPacket)) {
         return false;
      } else {
         RefreshEntitlementsPacket other = (RefreshEntitlementsPacket)o;
         return other.canEqual(this);
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RefreshEntitlementsPacket;
   }

   public int hashCode() {
      int result = 1;
      return 1;
   }

   public String toString() {
      return "RefreshEntitlementsPacket()";
   }
}
