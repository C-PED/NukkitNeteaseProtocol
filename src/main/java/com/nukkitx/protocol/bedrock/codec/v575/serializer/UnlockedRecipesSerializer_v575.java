package com.nukkitx.protocol.bedrock.codec.v575.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.UnlockedRecipesPacket;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class UnlockedRecipesSerializer_v575 implements BedrockPacketSerializer<UnlockedRecipesPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UnlockedRecipesPacket packet) {
      buffer.writeBoolean(packet.getAction() == UnlockedRecipesPacket.ActionType.NEWLY_UNLOCKED);
      List var10002 = packet.getUnlockedRecipes();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeString);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UnlockedRecipesPacket packet) {
      packet.setAction(buffer.readBoolean() ? UnlockedRecipesPacket.ActionType.NEWLY_UNLOCKED : UnlockedRecipesPacket.ActionType.INITIALLY_UNLOCKED);
      List var10002 = packet.getUnlockedRecipes();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readString);
   }
}
