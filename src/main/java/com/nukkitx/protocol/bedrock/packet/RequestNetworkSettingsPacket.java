package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class RequestNetworkSettingsPacket implements BedrockPacket {
   private int protocolVersion;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.REQUEST_NETWORK_SETTINGS;
   }

   public int getProtocolVersion() {
      return this.protocolVersion;
   }

   public void setProtocolVersion(int protocolVersion) {
      this.protocolVersion = protocolVersion;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RequestNetworkSettingsPacket)) {
         return false;
      } else {
         RequestNetworkSettingsPacket other = (RequestNetworkSettingsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.protocolVersion == other.protocolVersion;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RequestNetworkSettingsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.protocolVersion;
      return result;
   }

   public String toString() {
      return "RequestNetworkSettingsPacket(protocolVersion=" + this.protocolVersion + ")";
   }
}
