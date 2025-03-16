package com.nukkitx.network.raknet.util;

import com.nukkitx.network.raknet.EncapsulatedPacket;
import com.nukkitx.network.raknet.RakNetSession;
import com.nukkitx.network.util.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import javax.annotation.Nullable;

public class SplitPacketHelper extends AbstractReferenceCounted {
   private final EncapsulatedPacket[] packets;
   private final long created = System.currentTimeMillis();

   public SplitPacketHelper(long expectedLength) {
      Preconditions.checkArgument(expectedLength >= 1L && expectedLength <= 5376L, "expectedLength is less than 1 or greater than 5376 (%s)", expectedLength);
      this.packets = new EncapsulatedPacket[(int)expectedLength];
   }

   @Nullable
   public EncapsulatedPacket add(EncapsulatedPacket packet, RakNetSession session) {
      Preconditions.checkNotNull(packet, "packet");
      Preconditions.checkArgument(packet.isSplit(), "packet is not split");
      Preconditions.checkState(this.refCnt() > 0, "packet has been released");
      Preconditions.checkElementIndex(packet.getPartIndex(), this.packets.length);
      int partIndex = packet.getPartIndex();
      if (this.packets[partIndex] != null) {
         return null;
      } else {
         this.packets[partIndex] = packet;
         packet.retain();
         int sz = 0;

         for(EncapsulatedPacket netPacket : this.packets) {
            if (netPacket == null) {
               return null;
            }

            sz += netPacket.getBuffer().readableBytes();
         }

         ByteBuf reassembled = session.allocateBuffer(sz);

         for(EncapsulatedPacket netPacket : this.packets) {
            ByteBuf buf = netPacket.getBuffer();
            reassembled.writeBytes(buf, buf.readerIndex(), buf.readableBytes());
         }

         return packet.fromSplit(reassembled);
      }
   }

   public boolean expired() {
      Preconditions.checkState(this.refCnt() > 0, "packet has been released");
      return System.currentTimeMillis() - this.created >= 30000L;
   }

   protected void deallocate() {
      for(EncapsulatedPacket packet : this.packets) {
         ReferenceCountUtil.release(packet);
      }

   }

   public ReferenceCounted touch(Object hint) {
      throw new UnsupportedOperationException();
   }
}
