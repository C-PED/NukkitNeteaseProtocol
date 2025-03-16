package com.nukkitx.protocol.bedrock.codec.v313.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.NetworkChunkPublisherUpdatePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class NetworkChunkPublisherUpdateSerializer_v313 implements BedrockPacketSerializer<NetworkChunkPublisherUpdatePacket> {
   public static final NetworkChunkPublisherUpdateSerializer_v313 INSTANCE = new NetworkChunkPublisherUpdateSerializer_v313();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkChunkPublisherUpdatePacket packet) {
      helper.writeVector3i(buffer, packet.getPosition());
      VarInts.writeUnsignedInt(buffer, packet.getRadius());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkChunkPublisherUpdatePacket packet) {
      packet.setPosition(helper.readVector3i(buffer));
      packet.setRadius(VarInts.readUnsignedInt(buffer));
   }

   protected NetworkChunkPublisherUpdateSerializer_v313() {
   }
}
