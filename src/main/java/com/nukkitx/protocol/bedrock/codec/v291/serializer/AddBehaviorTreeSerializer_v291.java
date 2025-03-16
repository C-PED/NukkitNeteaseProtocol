package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.AddBehaviorTreePacket;
import io.netty.buffer.ByteBuf;

public class AddBehaviorTreeSerializer_v291 implements BedrockPacketSerializer<AddBehaviorTreePacket> {
   public static final AddBehaviorTreeSerializer_v291 INSTANCE = new AddBehaviorTreeSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddBehaviorTreePacket packet) {
      helper.writeString(buffer, packet.getBehaviorTreeJson());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddBehaviorTreePacket packet) {
      packet.setBehaviorTreeJson(helper.readString(buffer));
   }

   protected AddBehaviorTreeSerializer_v291() {
   }
}
