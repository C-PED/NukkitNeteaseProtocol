package com.nukkitx.protocol.bedrock.codec.v554.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v388.serializer.NetworkSettingsSerializer_v388;
import com.nukkitx.protocol.bedrock.data.PacketCompressionAlgorithm;
import com.nukkitx.protocol.bedrock.packet.NetworkSettingsPacket;
import io.netty.buffer.ByteBuf;

public class NetworkSettingsSerializer_v554 extends NetworkSettingsSerializer_v388 {
   protected static final PacketCompressionAlgorithm[] ALGORITHMS = PacketCompressionAlgorithm.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkSettingsPacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeShortLE(packet.getCompressionAlgorithm().ordinal());
      buffer.writeBoolean(packet.isClientThrottleEnabled());
      buffer.writeByte(packet.getClientThrottleThreshold());
      buffer.writeFloatLE(packet.getClientThrottleScalar());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkSettingsPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setCompressionAlgorithm(ALGORITHMS[buffer.readUnsignedShortLE()]);
      packet.setClientThrottleEnabled(buffer.readBoolean());
      packet.setClientThrottleThreshold(buffer.readUnsignedByte());
      packet.setClientThrottleScalar(buffer.readFloatLE());
   }
}
