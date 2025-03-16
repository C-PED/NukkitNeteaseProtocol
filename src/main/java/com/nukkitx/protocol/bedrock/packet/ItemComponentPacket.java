package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.ComponentItemData;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class ItemComponentPacket implements BedrockPacket {
   private final List<ComponentItemData> items = new ObjectArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ITEM_COMPONENT;
   }

   public List<ComponentItemData> getItems() {
      return this.items;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ItemComponentPacket)) {
         return false;
      } else {
         ItemComponentPacket other = (ItemComponentPacket)o;
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
      return other instanceof ItemComponentPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $items = this.items;
      result = result * 59 + ($items == null ? 43 : $items.hashCode());
      return result;
   }

   public String toString() {
      return "ItemComponentPacket(items=" + this.items + ")";
   }
}
