package com.nukkitx.protocol.bedrock.codec.v567.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ClientCheatAbilityPacket;
import io.netty.buffer.ByteBuf;

public class ClientCheatAbilitySerializer_v567 implements BedrockPacketSerializer<ClientCheatAbilityPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCheatAbilityPacket packet) {
      helper.writePlayerAbilities(buffer, packet);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCheatAbilityPacket packet) {
      helper.readPlayerAbilities(buffer, packet);
   }
}
