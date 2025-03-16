package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.definitions.DimensionDefinition;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class DimensionDataPacket implements BedrockPacket {
   private final List<DimensionDefinition> definitions = new ObjectArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.DIMENSION_DATA;
   }

   public List<DimensionDefinition> getDefinitions() {
      return this.definitions;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DimensionDataPacket)) {
         return false;
      } else {
         DimensionDataPacket other = (DimensionDataPacket)o;
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
      return other instanceof DimensionDataPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $definitions = this.definitions;
      result = result * 59 + ($definitions == null ? 43 : $definitions.hashCode());
      return result;
   }

   public String toString() {
      return "DimensionDataPacket(definitions=" + this.definitions + ")";
   }
}
