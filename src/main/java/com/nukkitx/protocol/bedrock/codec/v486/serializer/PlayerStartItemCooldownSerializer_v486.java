package com.nukkitx.protocol.bedrock.codec.v486.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PlayerStartItemCooldownPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class PlayerStartItemCooldownSerializer_v486 implements BedrockPacketSerializer<PlayerStartItemCooldownPacket> {
   public static final PlayerStartItemCooldownSerializer_v486 INSTANCE = new PlayerStartItemCooldownSerializer_v486();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerStartItemCooldownPacket packet) {
      helper.writeString(buffer, packet.getItemCategory());
      VarInts.writeInt(buffer, packet.getCooldownDuration());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerStartItemCooldownPacket packet) {
      packet.setItemCategory(helper.readString(buffer));
      packet.setCooldownDuration(VarInts.readInt(buffer));
   }

   protected PlayerStartItemCooldownSerializer_v486() {
   }
}
