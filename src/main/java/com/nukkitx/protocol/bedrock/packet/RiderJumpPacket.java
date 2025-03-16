package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class RiderJumpPacket implements BedrockPacket {
   private int jumpStrength;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.RIDER_JUMP;
   }

   public int getJumpStrength() {
      return this.jumpStrength;
   }

   public void setJumpStrength(int jumpStrength) {
      this.jumpStrength = jumpStrength;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RiderJumpPacket)) {
         return false;
      } else {
         RiderJumpPacket other = (RiderJumpPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.jumpStrength == other.jumpStrength;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RiderJumpPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.jumpStrength;
      return result;
   }

   public String toString() {
      return "RiderJumpPacket(jumpStrength=" + this.jumpStrength + ")";
   }
}
