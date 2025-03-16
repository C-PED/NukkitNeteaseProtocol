package com.neteasemc.protocol.custom.packet.spigotInfoPacket;

import com.neteasemc.protocol.custom.GeyserBasePacket;
import com.neteasemc.protocol.custom.GeyserBasePacketHandler;
import com.neteasemc.protocol.custom.GeyserPacketType;
import com.nukkitx.protocol.common.PacketSignal;
import org.bukkit.entity.Player;

public class SpigotInfoPacket extends GeyserBasePacket {
   private String spigotVersion = "";

   public GeyserPacketType getPacketType() {
      return GeyserPacketType.SPIGOT_INFO;
   }

   public PacketSignal handle(GeyserBasePacketHandler handler) {
      return handler.handle(this);
   }

   public PacketSignal handle(GeyserBasePacketHandler handler, Player player) {
      return handler.handle(this, player);
   }

   public String getSpigotVersion() {
      return this.spigotVersion;
   }

   public void setSpigotVersion(String spigotVersion) {
      this.spigotVersion = spigotVersion;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SpigotInfoPacket)) {
         return false;
      } else {
         SpigotInfoPacket other = (SpigotInfoPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$spigotVersion = this.spigotVersion;
            Object other$spigotVersion = other.spigotVersion;
            if (this$spigotVersion == null) {
               if (other$spigotVersion != null) {
                  return false;
               }
            } else if (!this$spigotVersion.equals(other$spigotVersion)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SpigotInfoPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $spigotVersion = this.spigotVersion;
      result = result * 59 + ($spigotVersion == null ? 43 : $spigotVersion.hashCode());
      return result;
   }

   public String toString() {
      return "SpigotInfoPacket(spigotVersion=" + this.getSpigotVersion() + ")";
   }
}
