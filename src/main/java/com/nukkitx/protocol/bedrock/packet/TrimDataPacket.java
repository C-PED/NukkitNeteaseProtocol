package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.TrimMaterial;
import com.nukkitx.protocol.bedrock.data.TrimPattern;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class TrimDataPacket implements BedrockPacket {
   private final List<TrimPattern> patterns = new ObjectArrayList();
   private final List<TrimMaterial> materials = new ObjectArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.TRIM_DATA;
   }

   public List<TrimPattern> getPatterns() {
      return this.patterns;
   }

   public List<TrimMaterial> getMaterials() {
      return this.materials;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TrimDataPacket)) {
         return false;
      } else {
         TrimDataPacket other = (TrimDataPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$patterns = this.patterns;
            Object other$patterns = other.patterns;
            if (this$patterns == null) {
               if (other$patterns != null) {
                  return false;
               }
            } else if (!this$patterns.equals(other$patterns)) {
               return false;
            }

            Object this$materials = this.materials;
            Object other$materials = other.materials;
            if (this$materials == null) {
               if (other$materials != null) {
                  return false;
               }
            } else if (!this$materials.equals(other$materials)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TrimDataPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $patterns = this.patterns;
      result = result * 59 + ($patterns == null ? 43 : $patterns.hashCode());
      Object $materials = this.materials;
      result = result * 59 + ($materials == null ? 43 : $materials.hashCode());
      return result;
   }

   public String toString() {
      return "TrimDataPacket(patterns=" + this.patterns + ", materials=" + this.materials + ")";
   }
}
