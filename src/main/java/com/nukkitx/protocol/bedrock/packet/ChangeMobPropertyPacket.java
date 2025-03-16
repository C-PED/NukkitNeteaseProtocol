package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class ChangeMobPropertyPacket implements BedrockPacket {
   private long uniqueEntityId;
   private String property;
   private boolean boolValue;
   private String stringValue;
   private int intValue;
   private float floatValue;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CHANGE_MOB_PROPERTY;
   }

   public long getUniqueEntityId() {
      return this.uniqueEntityId;
   }

   public String getProperty() {
      return this.property;
   }

   public boolean isBoolValue() {
      return this.boolValue;
   }

   public String getStringValue() {
      return this.stringValue;
   }

   public int getIntValue() {
      return this.intValue;
   }

   public float getFloatValue() {
      return this.floatValue;
   }

   public void setUniqueEntityId(long uniqueEntityId) {
      this.uniqueEntityId = uniqueEntityId;
   }

   public void setProperty(String property) {
      this.property = property;
   }

   public void setBoolValue(boolean boolValue) {
      this.boolValue = boolValue;
   }

   public void setStringValue(String stringValue) {
      this.stringValue = stringValue;
   }

   public void setIntValue(int intValue) {
      this.intValue = intValue;
   }

   public void setFloatValue(float floatValue) {
      this.floatValue = floatValue;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ChangeMobPropertyPacket)) {
         return false;
      } else {
         ChangeMobPropertyPacket other = (ChangeMobPropertyPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.uniqueEntityId != other.uniqueEntityId) {
            return false;
         } else if (this.boolValue != other.boolValue) {
            return false;
         } else if (this.intValue != other.intValue) {
            return false;
         } else if (Float.compare(this.floatValue, other.floatValue) != 0) {
            return false;
         } else {
            Object this$property = this.property;
            Object other$property = other.property;
            if (this$property == null) {
               if (other$property != null) {
                  return false;
               }
            } else if (!this$property.equals(other$property)) {
               return false;
            }

            Object this$stringValue = this.stringValue;
            Object other$stringValue = other.stringValue;
            if (this$stringValue == null) {
               if (other$stringValue != null) {
                  return false;
               }
            } else if (!this$stringValue.equals(other$stringValue)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ChangeMobPropertyPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $uniqueEntityId = this.uniqueEntityId;
      result = result * 59 + (int)($uniqueEntityId >>> 32 ^ $uniqueEntityId);
      result = result * 59 + (this.boolValue ? 79 : 97);
      result = result * 59 + this.intValue;
      result = result * 59 + Float.floatToIntBits(this.floatValue);
      Object $property = this.property;
      result = result * 59 + ($property == null ? 43 : $property.hashCode());
      Object $stringValue = this.stringValue;
      result = result * 59 + ($stringValue == null ? 43 : $stringValue.hashCode());
      return result;
   }

   public String toString() {
      return "ChangeMobPropertyPacket(uniqueEntityId=" + this.uniqueEntityId + ", property=" + this.property + ", boolValue=" + this.boolValue + ", stringValue=" + this.stringValue + ", intValue=" + this.intValue + ", floatValue=" + this.floatValue + ")";
   }
}
