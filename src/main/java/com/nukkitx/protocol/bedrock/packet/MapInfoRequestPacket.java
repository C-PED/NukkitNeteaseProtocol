package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.map.MapPixel;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class MapInfoRequestPacket implements BedrockPacket {
   private long uniqueMapId;
   private final List<MapPixel> pixels = new ObjectArrayList();

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.MAP_INFO_REQUEST;
   }

   public long getUniqueMapId() {
      return this.uniqueMapId;
   }

   public List<MapPixel> getPixels() {
      return this.pixels;
   }

   public void setUniqueMapId(long uniqueMapId) {
      this.uniqueMapId = uniqueMapId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MapInfoRequestPacket)) {
         return false;
      } else {
         MapInfoRequestPacket other = (MapInfoRequestPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.uniqueMapId != other.uniqueMapId) {
            return false;
         } else {
            Object this$pixels = this.pixels;
            Object other$pixels = other.pixels;
            if (this$pixels == null) {
               if (other$pixels != null) {
                  return false;
               }
            } else if (!this$pixels.equals(other$pixels)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MapInfoRequestPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $uniqueMapId = this.uniqueMapId;
      result = result * 59 + (int)($uniqueMapId >>> 32 ^ $uniqueMapId);
      Object $pixels = this.pixels;
      result = result * 59 + ($pixels == null ? 43 : $pixels.hashCode());
      return result;
   }

   public String toString() {
      return "MapInfoRequestPacket(uniqueMapId=" + this.uniqueMapId + ", pixels=" + this.pixels + ")";
   }
}
