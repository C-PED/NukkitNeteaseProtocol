package com.neteasemc.protocol.custom.packet.customItemPacket;

import com.neteasemc.protocol.custom.GeyserBasePacket;
import com.neteasemc.protocol.custom.GeyserBasePacketHandler;
import com.neteasemc.protocol.custom.GeyserPacketType;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.Map;
import org.bukkit.entity.Player;

public class CustomItemPacket extends GeyserBasePacket {
   private Map<String, String> items;

   public GeyserPacketType getPacketType() {
      return GeyserPacketType.CUSTOM_ITEM;
   }

   public PacketSignal handle(GeyserBasePacketHandler handler) {
      return handler.handle(this);
   }

   public PacketSignal handle(GeyserBasePacketHandler handler, Player player) {
      return handler.handle(this, player);
   }

   public Map<String, String> getItems() {
      return this.items;
   }

   public void setItems(Map<String, String> items) {
      this.items = items;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CustomItemPacket)) {
         return false;
      } else {
         CustomItemPacket other = (CustomItemPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$items = this.items;
            Object other$items = other.items;
            if (this$items == null) {
               if (other$items != null) {
                  return false;
               }
            } else if (!this$items.equals(other$items)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CustomItemPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $items = this.items;
      result = result * 59 + ($items == null ? 43 : $items.hashCode());
      return result;
   }

   public String toString() {
      return "CustomItemPacket(items=" + this.getItems() + ")";
   }
}
