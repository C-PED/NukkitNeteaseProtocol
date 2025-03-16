package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class OnScreenTextureAnimationPacket implements BedrockPacket {
   private long effectId;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ON_SCREEN_TEXTURE_ANIMATION;
   }

   public long getEffectId() {
      return this.effectId;
   }

   public void setEffectId(long effectId) {
      this.effectId = effectId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof OnScreenTextureAnimationPacket)) {
         return false;
      } else {
         OnScreenTextureAnimationPacket other = (OnScreenTextureAnimationPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.effectId == other.effectId;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof OnScreenTextureAnimationPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $effectId = this.effectId;
      result = result * 59 + (int)($effectId >>> 32 ^ $effectId);
      return result;
   }

   public String toString() {
      return "OnScreenTextureAnimationPacket(effectId=" + this.effectId + ")";
   }
}
