package com.neteasemc.protocol.netgame;

import com.neteasemc.protocol.custom.packet.geyserTransferPacket.GeyserTransferPacket;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.BedrockPacketHandler;
import com.nukkitx.protocol.bedrock.packet.BedrockPacketType;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.ArrayList;

public class NeteaseNetGameTransferBatchPacket implements BedrockPacket {
   private ArrayList<Long> userIds;
   private ArrayList<BedrockPacket> packets;
   private ArrayList<GeyserTransferPacket> transferPackets;

   public NeteaseNetGameTransferBatchPacket() {
   }

   public NeteaseNetGameTransferBatchPacket(ArrayList<Long> userIds, ArrayList<BedrockPacket> packets) {
      this.userIds = userIds;
      this.packets = packets;
   }

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.NE_NETGAME_TYPE;
   }

   public ArrayList<Long> getUserIds() {
      return this.userIds;
   }

   public ArrayList<BedrockPacket> getPackets() {
      return this.packets;
   }

   public ArrayList<GeyserTransferPacket> getTransferPackets() {
      return this.transferPackets;
   }

   public void setUserIds(ArrayList<Long> userIds) {
      this.userIds = userIds;
   }

   public void setPackets(ArrayList<BedrockPacket> packets) {
      this.packets = packets;
   }

   public void setTransferPackets(ArrayList<GeyserTransferPacket> transferPackets) {
      this.transferPackets = transferPackets;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NeteaseNetGameTransferBatchPacket)) {
         return false;
      } else {
         NeteaseNetGameTransferBatchPacket other = (NeteaseNetGameTransferBatchPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$userIds = this.userIds;
            Object other$userIds = other.userIds;
            if (this$userIds == null) {
               if (other$userIds != null) {
                  return false;
               }
            } else if (!this$userIds.equals(other$userIds)) {
               return false;
            }

            Object this$packets = this.packets;
            Object other$packets = other.packets;
            if (this$packets == null) {
               if (other$packets != null) {
                  return false;
               }
            } else if (!this$packets.equals(other$packets)) {
               return false;
            }

            Object this$transferPackets = this.transferPackets;
            Object other$transferPackets = other.transferPackets;
            if (this$transferPackets == null) {
               if (other$transferPackets != null) {
                  return false;
               }
            } else if (!this$transferPackets.equals(other$transferPackets)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof NeteaseNetGameTransferBatchPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $userIds = this.userIds;
      result = result * 59 + ($userIds == null ? 43 : $userIds.hashCode());
      Object $packets = this.packets;
      result = result * 59 + ($packets == null ? 43 : $packets.hashCode());
      Object $transferPackets = this.transferPackets;
      result = result * 59 + ($transferPackets == null ? 43 : $transferPackets.hashCode());
      return result;
   }

   public String toString() {
      return "NeteaseNetGameTransferBatchPacket(userIds=" + this.userIds + ", packets=" + this.packets + ", transferPackets=" + this.transferPackets + ")";
   }
}
