package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector2f;

public class PlayerInputPacket implements BedrockPacket {
   private Vector2f inputMotion;
   private boolean jumping;
   private boolean sneaking;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PLAYER_INPUT;
   }

   public Vector2f getInputMotion() {
      return this.inputMotion;
   }

   public boolean isJumping() {
      return this.jumping;
   }

   public boolean isSneaking() {
      return this.sneaking;
   }

   public void setInputMotion(Vector2f inputMotion) {
      this.inputMotion = inputMotion;
   }

   public void setJumping(boolean jumping) {
      this.jumping = jumping;
   }

   public void setSneaking(boolean sneaking) {
      this.sneaking = sneaking;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PlayerInputPacket)) {
         return false;
      } else {
         PlayerInputPacket other = (PlayerInputPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.jumping != other.jumping) {
            return false;
         } else if (this.sneaking != other.sneaking) {
            return false;
         } else {
            Object this$inputMotion = this.inputMotion;
            Object other$inputMotion = other.inputMotion;
            if (this$inputMotion == null) {
               if (other$inputMotion != null) {
                  return false;
               }
            } else if (!this$inputMotion.equals(other$inputMotion)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PlayerInputPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.jumping ? 79 : 97);
      result = result * 59 + (this.sneaking ? 79 : 97);
      Object $inputMotion = this.inputMotion;
      result = result * 59 + ($inputMotion == null ? 43 : $inputMotion.hashCode());
      return result;
   }

   public String toString() {
      return "PlayerInputPacket(inputMotion=" + this.inputMotion + ", jumping=" + this.jumping + ", sneaking=" + this.sneaking + ")";
   }
}
