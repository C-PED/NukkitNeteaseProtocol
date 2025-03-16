package com.neteasemc.protocol.netgame;

import com.neteasemc.protocol.netgame.subpacket.SubPacket;
import com.nukkitx.protocol.bedrock.annotation.NoEncryption;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.BedrockPacketHandler;
import com.nukkitx.protocol.bedrock.packet.BedrockPacketType;
import com.nukkitx.protocol.common.PacketSignal;

@NoEncryption
public class NetworkGamePacket implements BedrockPacket {
   SubPacket subPacket;

   public NetworkGamePacket() {
   }

   public NetworkGamePacket(SubPacket packet) {
      this.subPacket = packet;
   }

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.NETWORK_GAME_PACKET;
   }

   public SubPacket getSubPacket() {
      return this.subPacket;
   }

   public void setSubPacket(SubPacket subPacket) {
      this.subPacket = subPacket;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NetworkGamePacket)) {
         return false;
      } else {
         NetworkGamePacket other = (NetworkGamePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$subPacket = this.subPacket;
            Object other$subPacket = other.subPacket;
            if (this$subPacket == null) {
               if (other$subPacket != null) {
                  return false;
               }
            } else if (!this$subPacket.equals(other$subPacket)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof NetworkGamePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $subPacket = this.subPacket;
      result = result * 59 + ($subPacket == null ? 43 : $subPacket.hashCode());
      return result;
   }

   public String toString() {
      return "NetworkGamePacket(subPacket=" + this.subPacket + ")";
   }
}
