package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import io.netty.buffer.ByteBuf;
import io.netty.util.AbstractReferenceCounted;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;

public class LevelChunkPacket extends AbstractReferenceCounted implements BedrockPacket {
   private int chunkX;
   private int chunkZ;
   private int subChunksLength;
   private boolean cachingEnabled;
   private boolean requestSubChunks;
   private int subChunkLimit;
   private final LongList blobIds = new LongArrayList();
   private ByteBuf data;
   private int dimension;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.LEVEL_CHUNK;
   }

   public LevelChunkPacket touch(Object hint) {
      this.data.touch(hint);
      return this;
   }

   protected void deallocate() {
      this.data.release();
   }

   public int getChunkX() {
      return this.chunkX;
   }

   public int getChunkZ() {
      return this.chunkZ;
   }

   public int getSubChunksLength() {
      return this.subChunksLength;
   }

   public boolean isCachingEnabled() {
      return this.cachingEnabled;
   }

   public boolean isRequestSubChunks() {
      return this.requestSubChunks;
   }

   public int getSubChunkLimit() {
      return this.subChunkLimit;
   }

   public LongList getBlobIds() {
      return this.blobIds;
   }

   public ByteBuf getData() {
      return this.data;
   }

   public int getDimension() {
      return this.dimension;
   }

   public void setChunkX(int chunkX) {
      this.chunkX = chunkX;
   }

   public void setChunkZ(int chunkZ) {
      this.chunkZ = chunkZ;
   }

   public void setSubChunksLength(int subChunksLength) {
      this.subChunksLength = subChunksLength;
   }

   public void setCachingEnabled(boolean cachingEnabled) {
      this.cachingEnabled = cachingEnabled;
   }

   public void setRequestSubChunks(boolean requestSubChunks) {
      this.requestSubChunks = requestSubChunks;
   }

   public void setSubChunkLimit(int subChunkLimit) {
      this.subChunkLimit = subChunkLimit;
   }

   public void setData(ByteBuf data) {
      this.data = data;
   }

   public void setDimension(int dimension) {
      this.dimension = dimension;
   }

   public String toString() {
      return "LevelChunkPacket(chunkX=" + this.chunkX + ", chunkZ=" + this.chunkZ + ", subChunksLength=" + this.subChunksLength + ", cachingEnabled=" + this.cachingEnabled + ", requestSubChunks=" + this.requestSubChunks + ", subChunkLimit=" + this.subChunkLimit + ", blobIds=" + this.blobIds + ", dimension=" + this.dimension + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LevelChunkPacket)) {
         return false;
      } else {
         LevelChunkPacket other = (LevelChunkPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.chunkX != other.chunkX) {
            return false;
         } else if (this.chunkZ != other.chunkZ) {
            return false;
         } else if (this.subChunksLength != other.subChunksLength) {
            return false;
         } else if (this.cachingEnabled != other.cachingEnabled) {
            return false;
         } else if (this.requestSubChunks != other.requestSubChunks) {
            return false;
         } else if (this.subChunkLimit != other.subChunkLimit) {
            return false;
         } else if (this.dimension != other.dimension) {
            return false;
         } else {
            Object this$blobIds = this.blobIds;
            Object other$blobIds = other.blobIds;
            if (this$blobIds == null) {
               if (other$blobIds != null) {
                  return false;
               }
            } else if (!this$blobIds.equals(other$blobIds)) {
               return false;
            }

            Object this$data = this.data;
            Object other$data = other.data;
            if (this$data == null) {
               if (other$data != null) {
                  return false;
               }
            } else if (!this$data.equals(other$data)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof LevelChunkPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.chunkX;
      result = result * 59 + this.chunkZ;
      result = result * 59 + this.subChunksLength;
      result = result * 59 + (this.cachingEnabled ? 79 : 97);
      result = result * 59 + (this.requestSubChunks ? 79 : 97);
      result = result * 59 + this.subChunkLimit;
      result = result * 59 + this.dimension;
      Object $blobIds = this.blobIds;
      result = result * 59 + ($blobIds == null ? 43 : $blobIds.hashCode());
      Object $data = this.data;
      result = result * 59 + ($data == null ? 43 : $data.hashCode());
      return result;
   }
}
