package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class SetHealthPacket implements BedrockPacket {
   private int health;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_HEALTH;
   }

   public int getHealth() {
      return this.health;
   }

   public void setHealth(int health) {
      this.health = health;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetHealthPacket)) {
         return false;
      } else {
         SetHealthPacket other = (SetHealthPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.health == other.health;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetHealthPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.health;
      return result;
   }

   public String toString() {
      return "SetHealthPacket(health=" + this.health + ")";
   }
}
