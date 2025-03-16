package com.nukkitx.protocol.bedrock.codec.v486.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.LevelChunkSerializer_v361;
import com.nukkitx.protocol.bedrock.packet.LevelChunkPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.longs.LongList;
import it.unimi.dsi.fastutil.longs.LongListIterator;

public class LevelChunkSerializer_v486 extends LevelChunkSerializer_v361 {
   public static final LevelChunkSerializer_v486 INSTANCE = new LevelChunkSerializer_v486();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LevelChunkPacket packet) {
      this.writeChunkLocation(buffer, packet);
      if (!packet.isRequestSubChunks()) {
         VarInts.writeUnsignedInt(buffer, packet.getSubChunksLength());
      } else if (packet.getSubChunkLimit() < 0) {
         VarInts.writeUnsignedInt(buffer, -1);
      } else {
         VarInts.writeUnsignedInt(buffer, -2);
         buffer.writeShortLE(packet.getSubChunkLimit());
      }

      buffer.writeBoolean(packet.isCachingEnabled());
      if (packet.isCachingEnabled()) {
         LongList blobIds = packet.getBlobIds();
         VarInts.writeUnsignedInt(buffer, blobIds.size());
         LongListIterator var5 = blobIds.iterator();

         while(var5.hasNext()) {
            long blobId = (Long)var5.next();
            buffer.writeLongLE(blobId);
         }
      }

      helper.writeByteBuf(buffer, packet.getData());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LevelChunkPacket packet) {
      this.readChunkLocation(buffer, packet);
      int subChunksCount = VarInts.readUnsignedInt(buffer);
      if (subChunksCount >= 0) {
         packet.setSubChunksLength(subChunksCount);
      } else {
         packet.setRequestSubChunks(true);
         if (subChunksCount == -1) {
            packet.setSubChunkLimit(subChunksCount);
         } else if (subChunksCount == -2) {
            packet.setSubChunkLimit(buffer.readUnsignedShortLE());
         }
      }

      packet.setCachingEnabled(buffer.readBoolean());
      if (packet.isCachingEnabled()) {
         LongList blobIds = packet.getBlobIds();
         int length = VarInts.readUnsignedInt(buffer);

         for(int i = 0; i < length; ++i) {
            blobIds.add(buffer.readLongLE());
         }
      }

      packet.setData(helper.readByteBuf(buffer));
   }

   protected void writeChunkLocation(ByteBuf buffer, LevelChunkPacket packet) {
      VarInts.writeInt(buffer, packet.getChunkX());
      VarInts.writeInt(buffer, packet.getChunkZ());
   }

   protected void readChunkLocation(ByteBuf buffer, LevelChunkPacket packet) {
      packet.setChunkX(VarInts.readInt(buffer));
      packet.setChunkZ(VarInts.readInt(buffer));
   }

   protected LevelChunkSerializer_v486() {
   }
}
