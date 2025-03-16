package com.nukkitx.protocol.bedrock.codec.v419.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v388.serializer.MoveEntityDeltaSerializer_v388;
import com.nukkitx.protocol.bedrock.packet.MoveEntityDeltaPacket;
import com.nukkitx.protocol.common.util.TriConsumer;
import io.netty.buffer.ByteBuf;

public class MoveEntityDeltaSerializer_v419 extends MoveEntityDeltaSerializer_v388 {
   protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveEntityDeltaPacket> READER_X = (buffer, helper, packet) -> packet.setX(buffer.readFloatLE());
   protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveEntityDeltaPacket> READER_Y = (buffer, helper, packet) -> packet.setY(buffer.readFloatLE());
   protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveEntityDeltaPacket> READER_Z = (buffer, helper, packet) -> packet.setZ(buffer.readFloatLE());
   protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveEntityDeltaPacket> WRITER_X = (buffer, helper, packet) -> buffer.writeFloatLE(packet.getX());
   protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveEntityDeltaPacket> WRITER_Y = (buffer, helper, packet) -> buffer.writeFloatLE(packet.getY());
   protected static final TriConsumer<ByteBuf, BedrockCodecHelper, MoveEntityDeltaPacket> WRITER_Z = (buffer, helper, packet) -> buffer.writeFloatLE(packet.getZ());
   public static final MoveEntityDeltaSerializer_v419 INSTANCE = new MoveEntityDeltaSerializer_v419();

   protected MoveEntityDeltaSerializer_v419() {
      this.readers.put(MoveEntityDeltaPacket.Flag.HAS_X, READER_X);
      this.readers.put(MoveEntityDeltaPacket.Flag.HAS_Y, READER_Y);
      this.readers.put(MoveEntityDeltaPacket.Flag.HAS_Z, READER_Z);
      this.writers.put(MoveEntityDeltaPacket.Flag.HAS_X, WRITER_X);
      this.writers.put(MoveEntityDeltaPacket.Flag.HAS_Y, WRITER_Y);
      this.writers.put(MoveEntityDeltaPacket.Flag.HAS_Z, WRITER_Z);
   }
}
