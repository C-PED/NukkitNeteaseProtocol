package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.ArrayList;
import java.util.List;

public class ItemStackResponsePacket implements BedrockPacket {
   private final List<ItemStackResponse> entries = new ArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ITEM_STACK_RESPONSE;
   }

   public List<ItemStackResponse> getEntries() {
      return this.entries;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ItemStackResponsePacket)) {
         return false;
      } else {
         ItemStackResponsePacket other = (ItemStackResponsePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$entries = this.getEntries();
            Object other$entries = other.getEntries();
            if (this$entries == null) {
               if (other$entries != null) {
                  return false;
               }
            } else if (!this$entries.equals(other$entries)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ItemStackResponsePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $entries = this.getEntries();
      result = result * 59 + ($entries == null ? 43 : $entries.hashCode());
      return result;
   }

   public String toString() {
      return "ItemStackResponsePacket(entries=" + this.entries + ")";
   }
}
