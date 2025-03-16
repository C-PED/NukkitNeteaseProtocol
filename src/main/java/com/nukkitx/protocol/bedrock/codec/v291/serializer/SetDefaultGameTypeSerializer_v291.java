package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetDefaultGameTypePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetDefaultGameTypeSerializer_v291 implements BedrockPacketSerializer<SetDefaultGameTypePacket> {
   public static final SetDefaultGameTypeSerializer_v291 INSTANCE = new SetDefaultGameTypeSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetDefaultGameTypePacket packet) {
      VarInts.writeInt(buffer, packet.getGamemode());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetDefaultGameTypePacket packet) {
      packet.setGamemode(VarInts.readInt(buffer));
   }

   protected SetDefaultGameTypeSerializer_v291() {
   }
}
