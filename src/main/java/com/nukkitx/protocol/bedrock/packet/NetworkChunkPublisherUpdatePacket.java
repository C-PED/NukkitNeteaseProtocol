package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import org.cloudburstmc.math.vector.Vector2i;
import org.cloudburstmc.math.vector.Vector3i;

public class NetworkChunkPublisherUpdatePacket implements BedrockPacket {
   private Vector3i position;
   private int radius;
   private final List<Vector2i> savedChunks = new ObjectArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.NETWORK_CHUNK_PUBLISHER_UPDATE;
   }

   public Vector3i getPosition() {
      return this.position;
   }

   public int getRadius() {
      return this.radius;
   }

   public List<Vector2i> getSavedChunks() {
      return this.savedChunks;
   }

   public void setPosition(Vector3i position) {
      this.position = position;
   }

   public void setRadius(int radius) {
      this.radius = radius;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NetworkChunkPublisherUpdatePacket)) {
         return false;
      } else {
         NetworkChunkPublisherUpdatePacket other = (NetworkChunkPublisherUpdatePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.radius != other.radius) {
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

            Object this$savedChunks = this.savedChunks;
            Object other$savedChunks = other.savedChunks;
            if (this$savedChunks == null) {
               if (other$savedChunks != null) {
                  return false;
               }
            } else if (!this$savedChunks.equals(other$savedChunks)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof NetworkChunkPublisherUpdatePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.radius;
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      Object $savedChunks = this.savedChunks;
      result = result * 59 + ($savedChunks == null ? 43 : $savedChunks.hashCode());
      return result;
   }

   public String toString() {
      return "NetworkChunkPublisherUpdatePacket(position=" + this.position + ", radius=" + this.radius + ", savedChunks=" + this.savedChunks + ")";
   }
}
