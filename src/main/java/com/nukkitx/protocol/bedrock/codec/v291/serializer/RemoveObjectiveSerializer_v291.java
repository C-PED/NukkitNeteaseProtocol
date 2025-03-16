package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.RemoveObjectivePacket;
import io.netty.buffer.ByteBuf;

public class RemoveObjectiveSerializer_v291 implements BedrockPacketSerializer<RemoveObjectivePacket> {
   public static final RemoveObjectiveSerializer_v291 INSTANCE = new RemoveObjectiveSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RemoveObjectivePacket packet) {
      helper.writeString(buffer, packet.getObjectiveId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RemoveObjectivePacket packet) {
      packet.setObjectiveId(helper.readString(buffer));
   }

   protected RemoveObjectiveSerializer_v291() {
   }
}
