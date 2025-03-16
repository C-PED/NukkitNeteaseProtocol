package com.nukkitx.protocol.bedrock.codec.v589.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v575.serializer.UnlockedRecipesSerializer_v575;
import com.nukkitx.protocol.bedrock.packet.UnlockedRecipesPacket;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class UnlockedRecipesSerializer_v589 extends UnlockedRecipesSerializer_v575 {
   protected static final UnlockedRecipesPacket.ActionType[] ACTIONS = UnlockedRecipesPacket.ActionType.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UnlockedRecipesPacket packet) {
      buffer.writeIntLE(packet.getAction().ordinal());
      List var10002 = packet.getUnlockedRecipes();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeString);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UnlockedRecipesPacket packet) {
      packet.setAction(ACTIONS[buffer.readIntLE()]);
      List var10002 = packet.getUnlockedRecipes();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readString);
   }
}
