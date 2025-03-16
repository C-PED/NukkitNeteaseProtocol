package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetPlayerGameTypePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetPlayerGameTypeSerializer_v291 implements BedrockPacketSerializer<SetPlayerGameTypePacket> {
   public static final SetPlayerGameTypeSerializer_v291 INSTANCE = new SetPlayerGameTypeSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetPlayerGameTypePacket packet) {
      VarInts.writeInt(buffer, packet.getGamemode());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetPlayerGameTypePacket packet) {
      packet.setGamemode(VarInts.readInt(buffer));
   }

   protected SetPlayerGameTypeSerializer_v291() {
   }
}
