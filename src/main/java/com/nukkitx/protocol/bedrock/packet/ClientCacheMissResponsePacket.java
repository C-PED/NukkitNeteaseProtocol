package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import io.netty.buffer.ByteBuf;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.ReferenceCounted;
import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;

public class ClientCacheMissResponsePacket extends AbstractReferenceCounted implements BedrockPacket {
   private final Long2ObjectMap<ByteBuf> blobs = new Long2ObjectLinkedOpenHashMap();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CLIENT_CACHE_MISS_RESPONSE;
   }

   protected void deallocate() {
      this.blobs.values().forEach(ReferenceCounted::release);
   }

   public ClientCacheMissResponsePacket touch(Object hint) {
      this.blobs.values().forEach((byteBuf) -> byteBuf.touch(hint));
      return this;
   }

   public Long2ObjectMap<ByteBuf> getBlobs() {
      return this.blobs;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ClientCacheMissResponsePacket)) {
         return false;
      } else {
         ClientCacheMissResponsePacket other = (ClientCacheMissResponsePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$blobs = this.blobs;
            Object other$blobs = other.blobs;
            if (this$blobs == null) {
               if (other$blobs != null) {
                  return false;
               }
            } else if (!this$blobs.equals(other$blobs)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ClientCacheMissResponsePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $blobs = this.blobs;
      result = result * 59 + ($blobs == null ? 43 : $blobs.hashCode());
      return result;
   }

   public String toString() {
      return "ClientCacheMissResponsePacket(blobs=" + this.blobs + ")";
   }
}
