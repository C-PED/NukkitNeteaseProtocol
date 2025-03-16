package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetTimePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetTimeSerializer_v291 implements BedrockPacketSerializer<SetTimePacket> {
   public static final SetTimeSerializer_v291 INSTANCE = new SetTimeSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetTimePacket packet) {
      VarInts.writeInt(buffer, packet.getTime());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetTimePacket packet) {
      packet.setTime(VarInts.readInt(buffer));
   }

   protected SetTimeSerializer_v291() {
   }
}
