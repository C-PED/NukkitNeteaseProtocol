package com.neteasemc.protocol.netgame;

import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.BedrockPacketHandler;
import com.nukkitx.protocol.bedrock.packet.BedrockPacketType;
import com.nukkitx.protocol.common.PacketSignal;

public class NeteaseNetGameTransferPacket implements BedrockPacket {
   private long userId;
   private BedrockPacket subPacket;

   public NeteaseNetGameTransferPacket() {
   }

   public NeteaseNetGameTransferPacket(long userId, BedrockPacket packet) {
      this.userId = userId;
      this.subPacket = packet;
   }

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.NE_NETGAME_TYPE;
   }

   public long getUserId() {
      return this.userId;
   }

   public BedrockPacket getSubPacket() {
      return this.subPacket;
   }

   public void setUserId(long userId) {
      this.userId = userId;
   }

   public void setSubPacket(BedrockPacket subPacket) {
      this.subPacket = subPacket;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NeteaseNetGameTransferPacket)) {
         return false;
      } else {
         NeteaseNetGameTransferPacket other = (NeteaseNetGameTransferPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.userId != other.userId) {
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
      return other instanceof NeteaseNetGameTransferPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $userId = this.userId;
      result = result * 59 + (int)($userId >>> 32 ^ $userId);
      Object $subPacket = this.subPacket;
      result = result * 59 + ($subPacket == null ? 43 : $subPacket.hashCode());
      return result;
   }

   public String toString() {
      return "NeteaseNetGameTransferPacket(userId=" + this.userId + ", subPacket=" + this.subPacket + ")";
   }
}
