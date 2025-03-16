package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class HurtArmorPacket implements BedrockPacket {
   private int cause;
   private int damage;
   private long armorSlots;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.HURT_ARMOR;
   }

   public int getCause() {
      return this.cause;
   }

   public int getDamage() {
      return this.damage;
   }

   public long getArmorSlots() {
      return this.armorSlots;
   }

   public void setCause(int cause) {
      this.cause = cause;
   }

   public void setDamage(int damage) {
      this.damage = damage;
   }

   public void setArmorSlots(long armorSlots) {
      this.armorSlots = armorSlots;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HurtArmorPacket)) {
         return false;
      } else {
         HurtArmorPacket other = (HurtArmorPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.cause != other.cause) {
            return false;
         } else if (this.damage != other.damage) {
            return false;
         } else {
            return this.armorSlots == other.armorSlots;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof HurtArmorPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.cause;
      result = result * 59 + this.damage;
      long $armorSlots = this.armorSlots;
      result = result * 59 + (int)($armorSlots >>> 32 ^ $armorSlots);
      return result;
   }

   public String toString() {
      return "HurtArmorPacket(cause=" + this.cause + ", damage=" + this.damage + ", armorSlots=" + this.armorSlots + ")";
   }
}
