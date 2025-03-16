package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.AddPlayerSerializer_v291;
import com.nukkitx.protocol.bedrock.packet.AddPlayerPacket;
import io.netty.buffer.ByteBuf;

public class AddPlayerSerializer_v388 extends AddPlayerSerializer_v291 {
   public static final AddPlayerSerializer_v388 INSTANCE = new AddPlayerSerializer_v388();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddPlayerPacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeIntLE(packet.getBuildPlatform());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddPlayerPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setBuildPlatform(buffer.readIntLE());
   }

   protected AddPlayerSerializer_v388() {
   }
}
