package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.AutomationClientConnectPacket;
import io.netty.buffer.ByteBuf;

public class AutomationClientConnectSerializer_v291 implements BedrockPacketSerializer<AutomationClientConnectPacket> {
   public static final AutomationClientConnectSerializer_v291 INSTANCE = new AutomationClientConnectSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AutomationClientConnectPacket packet) {
      helper.writeString(buffer, packet.getAddress());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AutomationClientConnectPacket packet) {
      packet.setAddress(helper.readString(buffer));
   }

   protected AutomationClientConnectSerializer_v291() {
   }
}
