package com.nukkitx.protocol.bedrock.codec.v471.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.HeightMapDataType;
import com.nukkitx.protocol.bedrock.data.SubChunkData;
import com.nukkitx.protocol.bedrock.data.SubChunkRequestResult;
import com.nukkitx.protocol.bedrock.packet.SubChunkPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SubChunkSerializer_v471 implements BedrockPacketSerializer<SubChunkPacket> {
   public static final SubChunkSerializer_v471 INSTANCE = new SubChunkSerializer_v471();
   protected static final int HEIGHT_MAP_LENGTH = 256;

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
      VarInts.writeInt(buffer, packet.getDimension());
      SubChunkData subChunk = (SubChunkData)packet.getSubChunks().get(0);
      this.serializeSubChunk(buffer, helper, packet, subChunk);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
      packet.setDimension(VarInts.readInt(buffer));
      SubChunkData subChunk = this.deserializeSubChunk(buffer, helper, packet);
      packet.getSubChunks().add(subChunk);
   }

   protected void serializeSubChunk(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet, SubChunkData subChunk) {
      helper.writeVector3i(buffer, subChunk.getPosition());
      helper.writeByteBuf(buffer, subChunk.getData());
      VarInts.writeInt(buffer, subChunk.getResult().ordinal());
      buffer.writeByte(subChunk.getHeightMapType().ordinal());
      ByteBuf heightMapBuf = subChunk.getHeightMapData();
      buffer.writeBytes(heightMapBuf, heightMapBuf.readerIndex(), 256);
   }

   protected SubChunkData deserializeSubChunk(ByteBuf buffer, BedrockCodecHelper helper, SubChunkPacket packet) {
      SubChunkData subChunk = new SubChunkData();
      subChunk.setPosition(helper.readVector3i(buffer));
      subChunk.setData(helper.readByteBuf(buffer));
      subChunk.setResult(SubChunkRequestResult.values()[VarInts.readInt(buffer)]);
      subChunk.setHeightMapType(HeightMapDataType.values()[buffer.readByte()]);
      subChunk.setHeightMapData(buffer.readRetainedSlice(256));
      return subChunk;
   }

   protected SubChunkSerializer_v471() {
   }
}
