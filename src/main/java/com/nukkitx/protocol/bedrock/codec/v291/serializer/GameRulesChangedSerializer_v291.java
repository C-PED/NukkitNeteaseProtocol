package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.GameRulesChangedPacket;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class GameRulesChangedSerializer_v291 implements BedrockPacketSerializer<GameRulesChangedPacket> {
   public static final GameRulesChangedSerializer_v291 INSTANCE = new GameRulesChangedSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, GameRulesChangedPacket packet) {
      List var10002 = packet.getGameRules();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeGameRule);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, GameRulesChangedPacket packet) {
      List var10002 = packet.getGameRules();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readGameRule);
   }

   protected GameRulesChangedSerializer_v291() {
   }
}
