package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class ServerSettingsRequestPacket implements BedrockPacket {
   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SERVER_SETTINGS_REQUEST;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ServerSettingsRequestPacket)) {
         return false;
      } else {
         ServerSettingsRequestPacket other = (ServerSettingsRequestPacket)o;
         return other.canEqual(this);
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ServerSettingsRequestPacket;
   }

   public int hashCode() {
      int result = 1;
      return 1;
   }

   public String toString() {
      return "ServerSettingsRequestPacket()";
   }
}
