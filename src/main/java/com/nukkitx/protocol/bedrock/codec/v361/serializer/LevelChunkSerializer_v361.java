package com.nukkitx.protocol.bedrock.codec.v361.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.LevelChunkPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.longs.LongList;
import it.unimi.dsi.fastutil.longs.LongListIterator;

public class LevelChunkSerializer_v361 implements BedrockPacketSerializer<LevelChunkPacket> {
   public static final LevelChunkSerializer_v361 INSTANCE = new LevelChunkSerializer_v361();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LevelChunkPacket packet) {
      VarInts.writeInt(buffer, packet.getChunkX());
      VarInts.writeInt(buffer, packet.getChunkZ());
      VarInts.writeUnsignedInt(buffer, packet.getSubChunksLength());
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
      packet.setChunkX(VarInts.readInt(buffer));
      packet.setChunkZ(VarInts.readInt(buffer));
      packet.setSubChunksLength(VarInts.readUnsignedInt(buffer));
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

   protected LevelChunkSerializer_v361() {
   }
}
