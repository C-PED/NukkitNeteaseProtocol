package com.nukkitx.protocol.bedrock.codec.compat.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PlayStatusPacket;
import io.netty.buffer.ByteBuf;

public class PlayStatusSerializerCompat implements BedrockPacketSerializer<PlayStatusPacket> {
   public static final PlayStatusSerializerCompat INSTANCE = new PlayStatusSerializerCompat();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayStatusPacket packet) {
      buffer.writeInt(packet.getStatus().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayStatusPacket packet) {
      packet.setStatus(PlayStatusPacket.Status.values()[buffer.readInt()]);
   }
}
