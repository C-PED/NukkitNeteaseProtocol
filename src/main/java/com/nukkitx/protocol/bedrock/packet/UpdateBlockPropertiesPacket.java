package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.nbt.NbtMap;

public class UpdateBlockPropertiesPacket implements BedrockPacket {
   private NbtMap properties;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.UPDATE_BLOCK_PROPERTIES;
   }

   public NbtMap getProperties() {
      return this.properties;
   }

   public void setProperties(NbtMap properties) {
      this.properties = properties;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UpdateBlockPropertiesPacket)) {
         return false;
      } else {
         UpdateBlockPropertiesPacket other = (UpdateBlockPropertiesPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$properties = this.properties;
            Object other$properties = other.properties;
            if (this$properties == null) {
               if (other$properties != null) {
                  return false;
               }
            } else if (!this$properties.equals(other$properties)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UpdateBlockPropertiesPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $properties = this.properties;
      result = result * 59 + ($properties == null ? 43 : $properties.hashCode());
      return result;
   }

   public String toString() {
      return "UpdateBlockPropertiesPacket(properties=" + this.properties + ")";
   }
}
