package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class PlayerStartItemCooldownPacket implements BedrockPacket {
   private String itemCategory;
   private int cooldownDuration;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PLAYER_START_ITEM_COOLDOWN;
   }

   public String getItemCategory() {
      return this.itemCategory;
   }

   public int getCooldownDuration() {
      return this.cooldownDuration;
   }

   public void setItemCategory(String itemCategory) {
      this.itemCategory = itemCategory;
   }

   public void setCooldownDuration(int cooldownDuration) {
      this.cooldownDuration = cooldownDuration;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PlayerStartItemCooldownPacket)) {
         return false;
      } else {
         PlayerStartItemCooldownPacket other = (PlayerStartItemCooldownPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.cooldownDuration != other.cooldownDuration) {
            return false;
         } else {
            Object this$itemCategory = this.itemCategory;
            Object other$itemCategory = other.itemCategory;
            if (this$itemCategory == null) {
               if (other$itemCategory != null) {
                  return false;
               }
            } else if (!this$itemCategory.equals(other$itemCategory)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PlayerStartItemCooldownPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.cooldownDuration;
      Object $itemCategory = this.itemCategory;
      result = result * 59 + ($itemCategory == null ? 43 : $itemCategory.hashCode());
      return result;
   }

   public String toString() {
      return "PlayerStartItemCooldownPacket(itemCategory=" + this.itemCategory + ", cooldownDuration=" + this.cooldownDuration + ")";
   }
}
