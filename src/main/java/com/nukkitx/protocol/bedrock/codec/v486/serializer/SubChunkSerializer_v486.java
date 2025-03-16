package com.nukkitx.protocol.bedrock.codec.v486.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v475.serializer.SubChunkSerializer_v475;
import com.nukkitx.protocol.bedrock.data.HeightMapDataType;
import com.nukkitx.protocol.bedrock.data.SubChunkData;
import com.nukkitx.protocol.bedrock.data.SubChunkRequestResult;
import com.nukkitx.protocol.bedrock.packet.SubChunkPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3i;

public class SubChunkSerializer_v486 extends SubChunkSerializer_v475 {
   public static final SubChunkSerializer_v486 INSTANCE = new SubChunkSerializer_v486();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
      buffer.writeBoolean(packet.isCacheEnabled());
      VarInts.writeInt(buffer, packet.getDimension());
      helper.writeVector3i(buffer, packet.getCenterPosition());
      buffer.writeIntLE(packet.getSubChunks().size());
      packet.getSubChunks().forEach((subChunk) -> this.serializeSubChunk(buffer, helper, packet, subChunk));
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
      packet.setCacheEnabled(buffer.readBoolean());
      packet.setDimension(VarInts.readInt(buffer));
      packet.setCenterPosition(helper.readVector3i(buffer));
      int size = buffer.readIntLE();

      for(int i = 0; i < size; ++i) {
         packet.getSubChunks().add(this.deserializeSubChunk(buffer, helper, packet));
      }

   }

   protected void serializeSubChunk(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet, SubChunkData subChunk) {
      this.writeSubChunkOffset(buffer, subChunk.getPosition());
      buffer.writeByte(subChunk.getResult().ordinal());
      if (subChunk.getResult() != SubChunkRequestResult.SUCCESS_ALL_AIR || !packet.isCacheEnabled()) {
         helper.writeByteBuf(buffer, subChunk.getData());
      }

      buffer.writeByte(subChunk.getHeightMapType().ordinal());
      if (subChunk.getHeightMapType() == HeightMapDataType.HAS_DATA) {
         ByteBuf heightMapBuf = subChunk.getHeightMapData();
         buffer.writeBytes(heightMapBuf, heightMapBuf.readerIndex(), 256);
      }

      if (packet.isCacheEnabled()) {
         buffer.writeLongLE(subChunk.getBlobId());
      }

   }

   protected SubChunkData deserializeSubChunk(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
      SubChunkData subChunk = new SubChunkData();
      subChunk.setPosition(this.readSubChunkOffset(buffer));
      subChunk.setResult(SubChunkRequestResult.values()[buffer.readByte()]);
      if (subChunk.getResult() != SubChunkRequestResult.SUCCESS_ALL_AIR || !packet.isCacheEnabled()) {
         subChunk.setData(helper.readByteBuf(buffer));
      }

      subChunk.setHeightMapType(HeightMapDataType.values()[buffer.readByte()]);
      if (subChunk.getHeightMapType() == HeightMapDataType.HAS_DATA) {
         subChunk.setHeightMapData(buffer.readRetainedSlice(256));
      }

      if (packet.isCacheEnabled()) {
         subChunk.setBlobId(buffer.readLongLE());
      }

      return subChunk;
   }

   protected void writeSubChunkOffset(ByteBuf buffer, Vector3i offsetPosition) {
      buffer.writeByte(offsetPosition.getX());
      buffer.writeByte(offsetPosition.getY());
      buffer.writeByte(offsetPosition.getZ());
   }

   protected Vector3i readSubChunkOffset(ByteBuf buffer) {
      return Vector3i.from(buffer.readByte(), buffer.readByte(), buffer.readByte());
   }

   protected SubChunkSerializer_v486() {
   }
}
