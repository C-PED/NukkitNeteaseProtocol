package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.ArrayList;
import java.util.List;

public class ItemStackRequestPacket implements BedrockPacket {
   private final List<ItemStackRequest> requests = new ArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ITEM_STACK_REQUEST;
   }

   public List<ItemStackRequest> getRequests() {
      return this.requests;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ItemStackRequestPacket)) {
         return false;
      } else {
         ItemStackRequestPacket other = (ItemStackRequestPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$requests = this.getRequests();
            Object other$requests = other.getRequests();
            if (this$requests == null) {
               if (other$requests != null) {
                  return false;
               }
            } else if (!this$requests.equals(other$requests)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ItemStackRequestPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $requests = this.getRequests();
      result = result * 59 + ($requests == null ? 43 : $requests.hashCode());
      return result;
   }

   public String toString() {
      return "ItemStackRequestPacket(requests=" + this.requests + ")";
   }
}
