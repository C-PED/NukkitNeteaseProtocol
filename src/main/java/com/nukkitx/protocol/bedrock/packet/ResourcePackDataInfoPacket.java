package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.ResourcePackType;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.Arrays;
import java.util.UUID;

public class ResourcePackDataInfoPacket implements BedrockPacket {
   private UUID packId;
   private String packVersion;
   private long maxChunkSize;
   private long chunkCount;
   private long compressedPackSize;
   private byte[] hash;
   private boolean premium;
   private ResourcePackType type;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.RESOURCE_PACK_DATA_INFO;
   }

   public UUID getPackId() {
      return this.packId;
   }

   public String getPackVersion() {
      return this.packVersion;
   }

   public long getMaxChunkSize() {
      return this.maxChunkSize;
   }

   public long getChunkCount() {
      return this.chunkCount;
   }

   public long getCompressedPackSize() {
      return this.compressedPackSize;
   }

   public byte[] getHash() {
      return this.hash;
   }

   public boolean isPremium() {
      return this.premium;
   }

   public ResourcePackType getType() {
      return this.type;
   }

   public void setPackId(UUID packId) {
      this.packId = packId;
   }

   public void setPackVersion(String packVersion) {
      this.packVersion = packVersion;
   }

   public void setMaxChunkSize(long maxChunkSize) {
      this.maxChunkSize = maxChunkSize;
   }

   public void setChunkCount(long chunkCount) {
      this.chunkCount = chunkCount;
   }

   public void setCompressedPackSize(long compressedPackSize) {
      this.compressedPackSize = compressedPackSize;
   }

   public void setHash(byte[] hash) {
      this.hash = hash;
   }

   public void setPremium(boolean premium) {
      this.premium = premium;
   }

   public void setType(ResourcePackType type) {
      this.type = type;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourcePackDataInfoPacket)) {
         return false;
      } else {
         ResourcePackDataInfoPacket other = (ResourcePackDataInfoPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.maxChunkSize != other.maxChunkSize) {
            return false;
         } else if (this.chunkCount != other.chunkCount) {
            return false;
         } else if (this.compressedPackSize != other.compressedPackSize) {
            return false;
         } else if (this.premium != other.premium) {
            return false;
         } else {
            Object this$packId = this.packId;
            Object other$packId = other.packId;
            if (this$packId == null) {
               if (other$packId != null) {
                  return false;
               }
            } else if (!this$packId.equals(other$packId)) {
               return false;
            }

            Object this$packVersion = this.packVersion;
            Object other$packVersion = other.packVersion;
            if (this$packVersion == null) {
               if (other$packVersion != null) {
                  return false;
               }
            } else if (!this$packVersion.equals(other$packVersion)) {
               return false;
            }

            if (!Arrays.equals(this.hash, other.hash)) {
               return false;
            } else {
               Object this$type = this.type;
               Object other$type = other.type;
               if (this$type == null) {
                  if (other$type != null) {
                     return false;
                  }
               } else if (!this$type.equals(other$type)) {
                  return false;
               }

               return true;
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ResourcePackDataInfoPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $maxChunkSize = this.maxChunkSize;
      result = result * 59 + (int)($maxChunkSize >>> 32 ^ $maxChunkSize);
      long $chunkCount = this.chunkCount;
      result = result * 59 + (int)($chunkCount >>> 32 ^ $chunkCount);
      long $compressedPackSize = this.compressedPackSize;
      result = result * 59 + (int)($compressedPackSize >>> 32 ^ $compressedPackSize);
      result = result * 59 + (this.premium ? 79 : 97);
      Object $packId = this.packId;
      result = result * 59 + ($packId == null ? 43 : $packId.hashCode());
      Object $packVersion = this.packVersion;
      result = result * 59 + ($packVersion == null ? 43 : $packVersion.hashCode());
      result = result * 59 + Arrays.hashCode(this.hash);
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      return result;
   }

   public String toString() {
      return "ResourcePackDataInfoPacket(packId=" + this.packId + ", packVersion=" + this.packVersion + ", maxChunkSize=" + this.maxChunkSize + ", chunkCount=" + this.chunkCount + ", compressedPackSize=" + this.compressedPackSize + ", hash=" + Arrays.toString(this.hash) + ", premium=" + this.premium + ", type=" + this.type + ")";
   }
}
