package com.nukkitx.protocol.bedrock.codec.v475.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v471.serializer.SubChunkSerializer_v471;
import com.nukkitx.protocol.bedrock.data.HeightMapDataType;
import com.nukkitx.protocol.bedrock.data.SubChunkData;
import com.nukkitx.protocol.bedrock.data.SubChunkRequestResult;
import com.nukkitx.protocol.bedrock.packet.SubChunkPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SubChunkSerializer_v475 extends SubChunkSerializer_v471 {
   public static final SubChunkSerializer_v475 INSTANCE = new SubChunkSerializer_v475();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeBoolean(packet.isCacheEnabled());
      if (packet.isCacheEnabled()) {
         buffer.writeLongLE(((SubChunkData)packet.getSubChunks().get(0)).getBlobId());
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setCacheEnabled(buffer.readBoolean());
      if (packet.isCacheEnabled()) {
         ((SubChunkData)packet.getSubChunks().get(0)).setBlobId(buffer.readLongLE());
      }

   }

   protected void serializeSubChunk(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet, SubChunkData subChunk) {
      helper.writeVector3i(buffer, subChunk.getPosition());
      helper.writeByteBuf(buffer, subChunk.getData());
      VarInts.writeInt(buffer, subChunk.getResult().ordinal());
      buffer.writeByte(subChunk.getHeightMapType().ordinal());
      if (subChunk.getHeightMapType() == HeightMapDataType.HAS_DATA) {
         ByteBuf heightMapBuf = subChunk.getHeightMapData();
         buffer.writeBytes(heightMapBuf, heightMapBuf.readerIndex(), 256);
      }

   }

   protected SubChunkData deserializeSubChunk(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
      SubChunkData subChunk = new SubChunkData();
      subChunk.setPosition(helper.readVector3i(buffer));
      subChunk.setData(helper.readByteBuf(buffer));
      subChunk.setResult(SubChunkRequestResult.values()[VarInts.readInt(buffer)]);
      subChunk.setHeightMapType(HeightMapDataType.values()[buffer.readByte()]);
      if (subChunk.getHeightMapType() == HeightMapDataType.HAS_DATA) {
         subChunk.setHeightMapData(buffer.readRetainedSlice(256));
      }

      return subChunk;
   }

   protected SubChunkSerializer_v475() {
   }
}
