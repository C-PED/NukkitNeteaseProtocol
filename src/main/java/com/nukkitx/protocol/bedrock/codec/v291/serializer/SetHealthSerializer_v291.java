package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetHealthPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetHealthSerializer_v291 implements BedrockPacketSerializer<SetHealthPacket> {
   public static final SetHealthSerializer_v291 INSTANCE = new SetHealthSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetHealthPacket packet) {
      VarInts.writeInt(buffer, packet.getHealth());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetHealthPacket packet) {
      packet.setHealth(VarInts.readInt(buffer));
   }

   protected SetHealthSerializer_v291() {
   }
}
