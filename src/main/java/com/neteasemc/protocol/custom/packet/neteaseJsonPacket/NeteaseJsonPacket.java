package com.neteasemc.protocol.custom.packet.neteaseJsonPacket;

import com.neteasemc.protocol.custom.GeyserBasePacket;
import com.neteasemc.protocol.custom.GeyserBasePacketHandler;
import com.neteasemc.protocol.custom.GeyserPacketType;
import com.nukkitx.protocol.common.PacketSignal;
import org.bukkit.entity.Player;

public class NeteaseJsonPacket extends GeyserBasePacket {
   String jsonString;

   public NeteaseJsonPacket() {
   }

   public NeteaseJsonPacket(String jsoString) {
      this.jsonString = jsoString;
   }

   public GeyserPacketType getPacketType() {
      return GeyserPacketType.NETEASE_JSON;
   }

   public PacketSignal handle(GeyserBasePacketHandler handler) {
      return handler.handle(this);
   }

   public PacketSignal handle(GeyserBasePacketHandler handler, Player player) {
      return handler.handle(this, player);
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NeteaseJsonPacket)) {
         return false;
      } else {
         NeteaseJsonPacket other = (NeteaseJsonPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$jsonString = this.jsonString;
            Object other$jsonString = other.jsonString;
            if (this$jsonString == null) {
               if (other$jsonString != null) {
                  return false;
               }
            } else if (!this$jsonString.equals(other$jsonString)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof NeteaseJsonPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $jsonString = this.jsonString;
      result = result * 59 + ($jsonString == null ? 43 : $jsonString.hashCode());
      return result;
   }

   public String toString() {
      return "NeteaseJsonPacket(jsonString=" + this.getJsonString() + ")";
   }

   public String getJsonString() {
      return this.jsonString;
   }

   public void setJsonString(String jsonString) {
      this.jsonString = jsonString;
   }
}
