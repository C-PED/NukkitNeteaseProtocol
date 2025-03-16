package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.DebugInfoPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class DebugInfoSerializer_v407 implements BedrockPacketSerializer<DebugInfoPacket> {
   public static final DebugInfoSerializer_v407 INSTANCE = new DebugInfoSerializer_v407();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DebugInfoPacket packet) {
      VarInts.writeLong(buffer, packet.getUniqueEntityId());
      helper.writeString(buffer, packet.getData());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DebugInfoPacket packet) {
      packet.setUniqueEntityId(VarInts.readLong(buffer));
      packet.setData(helper.readString(buffer));
   }

   protected DebugInfoSerializer_v407() {
   }
}
