package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.RemoveEntityPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class RemoveEntitySerializer_v291 implements BedrockPacketSerializer<RemoveEntityPacket> {
   public static final RemoveEntitySerializer_v291 INSTANCE = new RemoveEntitySerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RemoveEntityPacket packet) {
      VarInts.writeLong(buffer, packet.getUniqueEntityId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RemoveEntityPacket packet) {
      packet.setUniqueEntityId(VarInts.readLong(buffer));
   }

   protected RemoveEntitySerializer_v291() {
   }
}
