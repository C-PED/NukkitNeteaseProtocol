package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.ItemUseType;
import com.nukkitx.protocol.common.PacketSignal;

public class CompletedUsingItemPacket implements BedrockPacket {
   private int itemId;
   private ItemUseType type;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.COMPLETED_USING_ITEM;
   }

   public int getItemId() {
      return this.itemId;
   }

   public ItemUseType getType() {
      return this.type;
   }

   public void setItemId(int itemId) {
      this.itemId = itemId;
   }

   public void setType(ItemUseType type) {
      this.type = type;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CompletedUsingItemPacket)) {
         return false;
      } else {
         CompletedUsingItemPacket other = (CompletedUsingItemPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.itemId != other.itemId) {
            return false;
         } else {
            Object this$type = this.type;
            Object other$type = other.type;
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CompletedUsingItemPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.itemId;
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      return result;
   }

   public String toString() {
      return "CompletedUsingItemPacket(itemId=" + this.itemId + ", type=" + this.type + ")";
   }
}
