package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.TakeItemEntityPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class TakeItemEntitySerializer_v291 implements BedrockPacketSerializer<TakeItemEntityPacket> {
   public static final TakeItemEntitySerializer_v291 INSTANCE = new TakeItemEntitySerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TakeItemEntityPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getItemRuntimeEntityId());
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TakeItemEntityPacket packet) {
      packet.setItemRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
   }

   protected TakeItemEntitySerializer_v291() {
   }
}
