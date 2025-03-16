package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.EnchantOptionData;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.ArrayList;
import java.util.List;

public class PlayerEnchantOptionsPacket implements BedrockPacket {
   private final List<EnchantOptionData> options = new ArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PLAYER_ENCHANT_OPTIONS;
   }

   public List<EnchantOptionData> getOptions() {
      return this.options;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PlayerEnchantOptionsPacket)) {
         return false;
      } else {
         PlayerEnchantOptionsPacket other = (PlayerEnchantOptionsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$options = this.getOptions();
            Object other$options = other.getOptions();
            if (this$options == null) {
               if (other$options != null) {
                  return false;
               }
            } else if (!this$options.equals(other$options)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PlayerEnchantOptionsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $options = this.getOptions();
      result = result * 59 + ($options == null ? 43 : $options.hashCode());
      return result;
   }

   public String toString() {
      return "PlayerEnchantOptionsPacket(options=" + this.options + ")";
   }
}
