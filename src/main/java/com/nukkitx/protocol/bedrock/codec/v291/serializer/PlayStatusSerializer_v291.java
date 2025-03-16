package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PlayStatusPacket;
import io.netty.buffer.ByteBuf;

public class PlayStatusSerializer_v291 implements BedrockPacketSerializer<PlayStatusPacket> {
   public static final PlayStatusSerializer_v291 INSTANCE = new PlayStatusSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayStatusPacket packet) {
      buffer.writeInt(packet.getStatus().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayStatusPacket packet) {
      packet.setStatus(PlayStatusPacket.Status.values()[buffer.readInt()]);
   }

   protected PlayStatusSerializer_v291() {
   }
}
