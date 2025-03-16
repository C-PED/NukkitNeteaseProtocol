package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.MultiplayerMode;
import com.nukkitx.protocol.bedrock.packet.MultiplayerSettingsPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class MultiplayerSettingsSerializer_v388 implements BedrockPacketSerializer<MultiplayerSettingsPacket> {
   public static final MultiplayerSettingsSerializer_v388 INSTANCE = new MultiplayerSettingsSerializer_v388();
   private static final MultiplayerMode[] VALUES = MultiplayerMode.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MultiplayerSettingsPacket packet) {
      VarInts.writeInt(buffer, packet.getMode().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MultiplayerSettingsPacket packet) {
      packet.setMode(VALUES[VarInts.readInt(buffer)]);
   }

   private MultiplayerSettingsSerializer_v388() {
   }
}
