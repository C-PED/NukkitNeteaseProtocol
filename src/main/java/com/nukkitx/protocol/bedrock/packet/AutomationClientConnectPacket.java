package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class AutomationClientConnectPacket implements BedrockPacket {
   private String address;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.AUTOMATION_CLIENT_CONNECT;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AutomationClientConnectPacket)) {
         return false;
      } else {
         AutomationClientConnectPacket other = (AutomationClientConnectPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$address = this.address;
            Object other$address = other.address;
            if (this$address == null) {
               if (other$address != null) {
                  return false;
               }
            } else if (!this$address.equals(other$address)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AutomationClientConnectPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $address = this.address;
      result = result * 59 + ($address == null ? 43 : $address.hashCode());
      return result;
   }

   public String toString() {
      return "AutomationClientConnectPacket(address=" + this.address + ")";
   }
}
