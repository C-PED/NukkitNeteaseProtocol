package com.nukkitx.protocol.bedrock.codec.v534.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.UpdateAbilitiesPacket;
import io.netty.buffer.ByteBuf;

public class UpdateAbilitiesSerializer_v534 implements BedrockPacketSerializer<UpdateAbilitiesPacket> {
   public static final UpdateAbilitiesSerializer_v534 INSTANCE = new UpdateAbilitiesSerializer_v534();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateAbilitiesPacket packet) {
      helper.writePlayerAbilities(buffer, packet);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateAbilitiesPacket packet) {
      helper.readPlayerAbilities(buffer, packet);
   }

   protected UpdateAbilitiesSerializer_v534() {
   }
}
