package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.nbt.NbtMap;

public class BiomeDefinitionListPacket implements BedrockPacket {
   private NbtMap definitions;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.BIOME_DEFINITIONS_LIST;
   }

   public NbtMap getDefinitions() {
      return this.definitions;
   }

   public void setDefinitions(NbtMap definitions) {
      this.definitions = definitions;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BiomeDefinitionListPacket)) {
         return false;
      } else {
         BiomeDefinitionListPacket other = (BiomeDefinitionListPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$definitions = this.definitions;
            Object other$definitions = other.definitions;
            if (this$definitions == null) {
               if (other$definitions != null) {
                  return false;
               }
            } else if (!this$definitions.equals(other$definitions)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof BiomeDefinitionListPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $definitions = this.definitions;
      result = result * 59 + ($definitions == null ? 43 : $definitions.hashCode());
      return result;
   }

   public String toString() {
      return "BiomeDefinitionListPacket(definitions=" + this.definitions + ")";
   }
}
