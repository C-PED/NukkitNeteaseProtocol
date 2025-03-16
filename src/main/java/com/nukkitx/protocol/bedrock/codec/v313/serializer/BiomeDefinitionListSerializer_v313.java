package com.nukkitx.protocol.bedrock.codec.v313.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.BiomeDefinitionListPacket;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class BiomeDefinitionListSerializer_v313 implements BedrockPacketSerializer<BiomeDefinitionListPacket> {
   public static final BiomeDefinitionListSerializer_v313 INSTANCE = new BiomeDefinitionListSerializer_v313();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionListPacket packet) {
      helper.writeTag(buffer, packet.getDefinitions());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BiomeDefinitionListPacket packet) {
      packet.setDefinitions((NbtMap)helper.readTag(buffer, NbtMap.class));
   }

   protected BiomeDefinitionListSerializer_v313() {
   }
}
