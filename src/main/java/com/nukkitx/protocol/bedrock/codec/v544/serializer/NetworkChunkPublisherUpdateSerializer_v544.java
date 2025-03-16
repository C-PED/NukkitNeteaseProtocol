package com.nukkitx.protocol.bedrock.codec.v544.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.NetworkChunkPublisherUpdateSerializer_v313;
import com.nukkitx.protocol.bedrock.packet.NetworkChunkPublisherUpdatePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector2i;

public class NetworkChunkPublisherUpdateSerializer_v544 extends NetworkChunkPublisherUpdateSerializer_v313 {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkChunkPublisherUpdatePacket packet) {
      super.serialize(buffer, helper, packet);
      helper.writeArray(buffer, packet.getSavedChunks(), ByteBuf::writeIntLE, this::writeSavedChunk);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkChunkPublisherUpdatePacket packet) {
      super.deserialize(buffer, helper, packet);
      helper.readArray(buffer, packet.getSavedChunks(), ByteBuf::readIntLE, this::readSavedChunk);
   }

   protected void writeSavedChunk(ByteBuf buffer, BedrockCodecHelper helper, Vector2i savedChunk) {
      VarInts.writeInt(buffer, savedChunk.getX());
      VarInts.writeInt(buffer, savedChunk.getY());
   }

   protected Vector2i readSavedChunk(ByteBuf buffer, BedrockCodecHelper helper) {
      return Vector2i.from(VarInts.readInt(buffer), VarInts.readInt(buffer));
   }
}
