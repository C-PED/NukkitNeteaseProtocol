package com.nukkitx.protocol.bedrock.codec.v390;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.v389.Bedrock_v389;
import com.nukkitx.protocol.bedrock.codec.v390.serializer.PlayerListSerializer_v390;
import com.nukkitx.protocol.bedrock.codec.v390.serializer.PlayerSkinSerializer_v390;
import com.nukkitx.protocol.bedrock.packet.PlayerListPacket;
import com.nukkitx.protocol.bedrock.packet.PlayerSkinPacket;

public class Bedrock_v390 extends Bedrock_v389 {
   public static BedrockCodec CODEC;

   static {
      CODEC = Bedrock_v389.CODEC.toBuilder().protocolVersion(390).minecraftVersion("1.14.60").helper(() -> new BedrockCodecHelper_v390(ENTITY_DATA, GAME_RULE_TYPES)).updateSerializer(PlayerListPacket.class, PlayerListSerializer_v390.INSTANCE).updateSerializer(PlayerSkinPacket.class, PlayerSkinSerializer_v390.INSTANCE).build();
   }
}
