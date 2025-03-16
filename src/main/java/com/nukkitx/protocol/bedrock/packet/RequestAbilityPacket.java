package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.Ability;
import com.nukkitx.protocol.common.PacketSignal;

public class RequestAbilityPacket implements BedrockPacket {
   private Ability ability;
   private Ability.Type type;
   private boolean boolValue;
   private float floatValue;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.REQUEST_ABILITY;
   }

   public Ability getAbility() {
      return this.ability;
   }

   public Ability.Type getType() {
      return this.type;
   }

   public boolean isBoolValue() {
      return this.boolValue;
   }

   public float getFloatValue() {
      return this.floatValue;
   }

   public void setAbility(Ability ability) {
      this.ability = ability;
   }

   public void setType(Ability.Type type) {
      this.type = type;
   }

   public void setBoolValue(boolean boolValue) {
      this.boolValue = boolValue;
   }

   public void setFloatValue(float floatValue) {
      this.floatValue = floatValue;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RequestAbilityPacket)) {
         return false;
      } else {
         RequestAbilityPacket other = (RequestAbilityPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.boolValue != other.boolValue) {
            return false;
         } else if (Float.compare(this.floatValue, other.floatValue) != 0) {
            return false;
         } else {
            Object this$ability = this.ability;
            Object other$ability = other.ability;
            if (this$ability == null) {
               if (other$ability != null) {
                  return false;
               }
            } else if (!this$ability.equals(other$ability)) {
               return false;
            }

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
      return other instanceof RequestAbilityPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.boolValue ? 79 : 97);
      result = result * 59 + Float.floatToIntBits(this.floatValue);
      Object $ability = this.ability;
      result = result * 59 + ($ability == null ? 43 : $ability.hashCode());
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      return result;
   }

   public String toString() {
      return "RequestAbilityPacket(ability=" + this.ability + ", type=" + this.type + ", boolValue=" + this.boolValue + ", floatValue=" + this.floatValue + ")";
   }
}
