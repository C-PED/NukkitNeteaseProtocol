package com.nukkitx.protocol.bedrock.codec.v534.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.DeathInfoPacket;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class DeathInfoSerializer_v534 implements BedrockPacketSerializer<DeathInfoPacket> {
   public static final DeathInfoSerializer_v534 INSTANCE = new DeathInfoSerializer_v534();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DeathInfoPacket packet) {
      helper.writeString(buffer, packet.getCauseAttackName());
      List var10002 = packet.getMessageList();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeString);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DeathInfoPacket packet) {
      packet.setCauseAttackName(helper.readString(buffer));
      List var10002 = packet.getMessageList();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readString);
   }

   protected DeathInfoSerializer_v534() {
   }
}
