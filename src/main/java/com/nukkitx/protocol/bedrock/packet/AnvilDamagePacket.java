package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3i;

public class AnvilDamagePacket implements BedrockPacket {
   private int damage;
   private Vector3i position;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ANVIL_DAMAGE;
   }

   public int getDamage() {
      return this.damage;
   }

   public Vector3i getPosition() {
      return this.position;
   }

   public void setDamage(int damage) {
      this.damage = damage;
   }

   public void setPosition(Vector3i position) {
      this.position = position;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AnvilDamagePacket)) {
         return false;
      } else {
         AnvilDamagePacket other = (AnvilDamagePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.damage != other.damage) {
            return false;
         } else {
            Object this$position = this.position;
            Object other$position = other.position;
            if (this$position == null) {
               if (other$position != null) {
                  return false;
               }
            } else if (!this$position.equals(other$position)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AnvilDamagePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.damage;
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      return result;
   }

   public String toString() {
      return "AnvilDamagePacket(damage=" + this.damage + ", position=" + this.position + ")";
   }
}
