package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import org.cloudburstmc.math.vector.Vector3i;

public class SubChunkRequestPacket implements BedrockPacket {
   private int dimension;
   private Vector3i subChunkPosition;
   private List<Vector3i> positionOffsets = new ObjectArrayList();

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SUB_CHUNK_REQUEST;
   }

   public int getDimension() {
      return this.dimension;
   }

   public Vector3i getSubChunkPosition() {
      return this.subChunkPosition;
   }

   public List<Vector3i> getPositionOffsets() {
      return this.positionOffsets;
   }

   public void setDimension(int dimension) {
      this.dimension = dimension;
   }

   public void setSubChunkPosition(Vector3i subChunkPosition) {
      this.subChunkPosition = subChunkPosition;
   }

   public void setPositionOffsets(List<Vector3i> positionOffsets) {
      this.positionOffsets = positionOffsets;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SubChunkRequestPacket)) {
         return false;
      } else {
         SubChunkRequestPacket other = (SubChunkRequestPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.dimension != other.dimension) {
            return false;
         } else {
            Object this$subChunkPosition = this.subChunkPosition;
            Object other$subChunkPosition = other.subChunkPosition;
            if (this$subChunkPosition == null) {
               if (other$subChunkPosition != null) {
                  return false;
               }
            } else if (!this$subChunkPosition.equals(other$subChunkPosition)) {
               return false;
            }

            Object this$positionOffsets = this.positionOffsets;
            Object other$positionOffsets = other.positionOffsets;
            if (this$positionOffsets == null) {
               if (other$positionOffsets != null) {
                  return false;
               }
            } else if (!this$positionOffsets.equals(other$positionOffsets)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SubChunkRequestPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.dimension;
      Object $subChunkPosition = this.subChunkPosition;
      result = result * 59 + ($subChunkPosition == null ? 43 : $subChunkPosition.hashCode());
      Object $positionOffsets = this.positionOffsets;
      result = result * 59 + ($positionOffsets == null ? 43 : $positionOffsets.hashCode());
      return result;
   }

   public String toString() {
      return "SubChunkRequestPacket(dimension=" + this.dimension + ", subChunkPosition=" + this.subChunkPosition + ", positionOffsets=" + this.positionOffsets + ")";
   }
}
