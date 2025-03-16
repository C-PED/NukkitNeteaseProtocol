package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.nbt.NbtMap;

public class AvailableEntityIdentifiersPacket implements BedrockPacket {
   private NbtMap identifiers;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.AVAILABLE_ENTITY_IDENTIFIERS;
   }

   public NbtMap getIdentifiers() {
      return this.identifiers;
   }

   public void setIdentifiers(NbtMap identifiers) {
      this.identifiers = identifiers;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AvailableEntityIdentifiersPacket)) {
         return false;
      } else {
         AvailableEntityIdentifiersPacket other = (AvailableEntityIdentifiersPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$identifiers = this.identifiers;
            Object other$identifiers = other.identifiers;
            if (this$identifiers == null) {
               if (other$identifiers != null) {
                  return false;
               }
            } else if (!this$identifiers.equals(other$identifiers)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AvailableEntityIdentifiersPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $identifiers = this.identifiers;
      result = result * 59 + ($identifiers == null ? 43 : $identifiers.hashCode());
      return result;
   }

   public String toString() {
      return "AvailableEntityIdentifiersPacket(identifiers=" + this.identifiers + ")";
   }
}
