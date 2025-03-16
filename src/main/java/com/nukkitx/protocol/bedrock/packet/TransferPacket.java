package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class TransferPacket implements BedrockPacket {
   private String address;
   private int port;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.TRANSFER;
   }

   public String getAddress() {
      return this.address;
   }

   public int getPort() {
      return this.port;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public void setPort(int port) {
      this.port = port;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TransferPacket)) {
         return false;
      } else {
         TransferPacket other = (TransferPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.port != other.port) {
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
      return other instanceof TransferPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.port;
      Object $address = this.address;
      result = result * 59 + ($address == null ? 43 : $address.hashCode());
      return result;
   }

   public String toString() {
      return "TransferPacket(address=" + this.address + ", port=" + this.port + ")";
   }
}
