package com.nukkitx.protocol.bedrock.codec.v486.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v471.serializer.SubChunkRequestSerializer_v471;
import com.nukkitx.protocol.bedrock.packet.SubChunkRequestPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3i;

public class SubChunkRequestSerializer_v486 extends SubChunkRequestSerializer_v471 {
   public static final SubChunkRequestSerializer_v486 INSTANCE = new SubChunkRequestSerializer_v486();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkRequestPacket packet) {
      VarInts.writeInt(buffer, packet.getDimension());
      helper.writeVector3i(buffer, packet.getSubChunkPosition());
      helper.writeArray(buffer, packet.getPositionOffsets(), ByteBuf::writeIntLE, this::writeSubChunkOffset);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkRequestPacket packet) {
      packet.setDimension(VarInts.readInt(buffer));
      packet.setSubChunkPosition(helper.readVector3i(buffer));
      helper.readArray(buffer, packet.getPositionOffsets(), ByteBuf::readIntLE, this::readSubChunkOffset);
   }

   protected void writeSubChunkOffset(ByteBuf buffer, Vector3i offsetPosition) {
      buffer.writeByte(offsetPosition.getX());
      buffer.writeByte(offsetPosition.getY());
      buffer.writeByte(offsetPosition.getZ());
   }

   protected Vector3i readSubChunkOffset(ByteBuf buffer) {
      return Vector3i.from(buffer.readByte(), buffer.readByte(), buffer.readByte());
   }

   protected SubChunkRequestSerializer_v486() {
   }
}
