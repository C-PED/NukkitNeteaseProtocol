package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import java.util.UUID;

public class ResourcePackChunkRequestPacket implements BedrockPacket {
   private UUID packId;
   private String packVersion;
   private int chunkIndex;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.RESOURCE_PACK_CHUNK_REQUEST;
   }

   public UUID getPackId() {
      return this.packId;
   }

   public String getPackVersion() {
      return this.packVersion;
   }

   public int getChunkIndex() {
      return this.chunkIndex;
   }

   public void setPackId(UUID packId) {
      this.packId = packId;
   }

   public void setPackVersion(String packVersion) {
      this.packVersion = packVersion;
   }

   public void setChunkIndex(int chunkIndex) {
      this.chunkIndex = chunkIndex;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourcePackChunkRequestPacket)) {
         return false;
      } else {
         ResourcePackChunkRequestPacket other = (ResourcePackChunkRequestPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.chunkIndex != other.chunkIndex) {
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

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ResourcePackChunkRequestPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.chunkIndex;
      Object $packId = this.packId;
      result = result * 59 + ($packId == null ? 43 : $packId.hashCode());
      Object $packVersion = this.packVersion;
      result = result * 59 + ($packVersion == null ? 43 : $packVersion.hashCode());
      return result;
   }

   public String toString() {
      return "ResourcePackChunkRequestPacket(packId=" + this.packId + ", packVersion=" + this.packVersion + ", chunkIndex=" + this.chunkIndex + ")";
   }
}
