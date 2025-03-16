package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.DisconnectPacket;
import io.netty.buffer.ByteBuf;

public class DisconnectSerializer_v291 implements BedrockPacketSerializer<DisconnectPacket> {
   public static final DisconnectSerializer_v291 INSTANCE = new DisconnectSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
      buffer.writeBoolean(packet.isMessageSkipped());
      if (!packet.isMessageSkipped()) {
         helper.writeString(buffer, packet.getKickMessage());
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
      packet.setMessageSkipped(buffer.readBoolean());
      if (!packet.isMessageSkipped()) {
         packet.setKickMessage(helper.readString(buffer));
      }

   }

   protected DisconnectSerializer_v291() {
   }
}
