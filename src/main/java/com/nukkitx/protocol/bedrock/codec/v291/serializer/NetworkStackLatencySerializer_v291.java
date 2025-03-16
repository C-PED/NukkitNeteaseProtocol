package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.NetworkStackLatencyPacket;
import io.netty.buffer.ByteBuf;

public class NetworkStackLatencySerializer_v291 implements BedrockPacketSerializer<NetworkStackLatencyPacket> {
   public static final NetworkStackLatencySerializer_v291 INSTANCE = new NetworkStackLatencySerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkStackLatencyPacket packet) {
      buffer.writeLongLE(packet.getTimestamp());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkStackLatencyPacket packet) {
      packet.setTimestamp(buffer.readLongLE());
   }

   protected NetworkStackLatencySerializer_v291() {
   }
}
