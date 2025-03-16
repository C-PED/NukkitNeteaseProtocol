package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;

public class ClientCacheBlobStatusPacket implements BedrockPacket {
   private final LongList acks = new LongArrayList();
   private final LongList naks = new LongArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CLIENT_CACHE_BLOB_STATUS;
   }

   public LongList getAcks() {
      return this.acks;
   }

   public LongList getNaks() {
      return this.naks;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ClientCacheBlobStatusPacket)) {
         return false;
      } else {
         ClientCacheBlobStatusPacket other = (ClientCacheBlobStatusPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$acks = this.acks;
            Object other$acks = other.acks;
            if (this$acks == null) {
               if (other$acks != null) {
                  return false;
               }
            } else if (!this$acks.equals(other$acks)) {
               return false;
            }

            Object this$naks = this.naks;
            Object other$naks = other.naks;
            if (this$naks == null) {
               if (other$naks != null) {
                  return false;
               }
            } else if (!this$naks.equals(other$naks)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ClientCacheBlobStatusPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $acks = this.acks;
      result = result * 59 + ($acks == null ? 43 : $acks.hashCode());
      Object $naks = this.naks;
      result = result * 59 + ($naks == null ? 43 : $naks.hashCode());
      return result;
   }

   public String toString() {
      return "ClientCacheBlobStatusPacket(acks=" + this.acks + ", naks=" + this.naks + ")";
   }
}
