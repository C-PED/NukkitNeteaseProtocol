package com.nukkitx.protocol.bedrock.codec.v313.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.AvailableEntityIdentifiersPacket;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class AvailableEntityIdentifiersSerializer_v313 implements BedrockPacketSerializer<AvailableEntityIdentifiersPacket> {
   public static final AvailableEntityIdentifiersSerializer_v313 INSTANCE = new AvailableEntityIdentifiersSerializer_v313();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AvailableEntityIdentifiersPacket packet) {
      helper.writeTag(buffer, packet.getIdentifiers());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AvailableEntityIdentifiersPacket packet) {
      packet.setIdentifiers((NbtMap)helper.readTag(buffer, NbtMap.class));
   }

   protected AvailableEntityIdentifiersSerializer_v313() {
   }
}
