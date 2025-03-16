package com.nukkitx.protocol.bedrock.codec.v448.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.SimulationType;
import com.nukkitx.protocol.bedrock.packet.SimulationTypePacket;
import io.netty.buffer.ByteBuf;

public class SimulationTypeSerializer_v448 implements BedrockPacketSerializer<SimulationTypePacket> {
   public static final SimulationTypeSerializer_v448 INSTANCE = new SimulationTypeSerializer_v448();
   private static final SimulationType[] VALUES = SimulationType.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SimulationTypePacket packet) {
      buffer.writeByte(packet.getType().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SimulationTypePacket packet) {
      packet.setType(VALUES[buffer.readUnsignedByte()]);
   }

   protected SimulationTypeSerializer_v448() {
   }
}
