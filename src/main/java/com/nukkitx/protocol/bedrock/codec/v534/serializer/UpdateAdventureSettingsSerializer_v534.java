package com.nukkitx.protocol.bedrock.codec.v534.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.UpdateAdventureSettingsPacket;
import io.netty.buffer.ByteBuf;

public class UpdateAdventureSettingsSerializer_v534 implements BedrockPacketSerializer<UpdateAdventureSettingsPacket> {
   public static final UpdateAdventureSettingsSerializer_v534 INSTANCE = new UpdateAdventureSettingsSerializer_v534();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateAdventureSettingsPacket packet) {
      buffer.writeBoolean(packet.isNoPvM());
      buffer.writeBoolean(packet.isNoMvP());
      buffer.writeBoolean(packet.isImmutableWorld());
      buffer.writeBoolean(packet.isShowNameTags());
      buffer.writeBoolean(packet.isAutoJump());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateAdventureSettingsPacket packet) {
      packet.setNoPvM(buffer.readBoolean());
      packet.setNoMvP(buffer.readBoolean());
      packet.setImmutableWorld(buffer.readBoolean());
      packet.setShowNameTags(buffer.readBoolean());
      packet.setAutoJump(buffer.readBoolean());
   }

   protected UpdateAdventureSettingsSerializer_v534() {
   }
}
