package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class PlayerFogPacket implements BedrockPacket {
   private final List<String> fogStack = new ObjectArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PLAYER_FOG;
   }

   public List<String> getFogStack() {
      return this.fogStack;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PlayerFogPacket)) {
         return false;
      } else {
         PlayerFogPacket other = (PlayerFogPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$fogStack = this.fogStack;
            Object other$fogStack = other.fogStack;
            if (this$fogStack == null) {
               if (other$fogStack != null) {
                  return false;
               }
            } else if (!this$fogStack.equals(other$fogStack)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PlayerFogPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $fogStack = this.fogStack;
      result = result * 59 + ($fogStack == null ? 43 : $fogStack.hashCode());
      return result;
   }

   public String toString() {
      return "PlayerFogPacket(fogStack=" + this.fogStack + ")";
   }
}
