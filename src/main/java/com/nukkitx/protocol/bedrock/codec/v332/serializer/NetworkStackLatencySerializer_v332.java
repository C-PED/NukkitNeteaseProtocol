package com.nukkitx.protocol.bedrock.codec.v332.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.NetworkStackLatencyPacket;
import io.netty.buffer.ByteBuf;

public class NetworkStackLatencySerializer_v332 implements BedrockPacketSerializer<NetworkStackLatencyPacket> {
   public static final NetworkStackLatencySerializer_v332 INSTANCE = new NetworkStackLatencySerializer_v332();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkStackLatencyPacket packet) {
      buffer.writeLongLE(packet.getTimestamp());
      buffer.writeBoolean(packet.isFromServer());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkStackLatencyPacket packet) {
      packet.setTimestamp(buffer.readLongLE());
      packet.setFromServer(buffer.readBoolean());
   }

   protected NetworkStackLatencySerializer_v332() {
   }
}
