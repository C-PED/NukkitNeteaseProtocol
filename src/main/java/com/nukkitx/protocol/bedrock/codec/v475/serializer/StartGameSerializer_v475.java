package com.nukkitx.protocol.bedrock.codec.v475.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.StartGameSerializer_v465;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import io.netty.buffer.ByteBuf;

public class StartGameSerializer_v475 extends StartGameSerializer_v465 {
   public static final StartGameSerializer_v475 INSTANCE = new StartGameSerializer_v475();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeLongLE(packet.getBlockRegistryChecksum());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setBlockRegistryChecksum(buffer.readLongLE());
   }

   protected StartGameSerializer_v475() {
   }
}
