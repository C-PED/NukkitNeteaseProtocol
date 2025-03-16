package com.neteasemc.protocol.custom;

import com.nukkitx.protocol.common.PacketSignal;
import org.bukkit.entity.Player;

public abstract class GeyserBasePacket {
   private int packetId;

   public abstract PacketSignal handle(GeyserBasePacketHandler var1);

   public abstract PacketSignal handle(GeyserBasePacketHandler var1, Player var2);

   public abstract GeyserPacketType getPacketType();

   public void setPacketId(int packetId) {
      this.packetId = packetId;
   }

   public int getPacketId() {
      return this.packetId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GeyserBasePacket)) {
         return false;
      } else {
         GeyserBasePacket other = (GeyserBasePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.getPacketId() == other.getPacketId();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof GeyserBasePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getPacketId();
      return result;
   }

   public String toString() {
      return "GeyserBasePacket(packetId=" + this.getPacketId() + ")";
   }
}
