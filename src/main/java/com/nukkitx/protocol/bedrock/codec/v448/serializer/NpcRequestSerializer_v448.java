package com.nukkitx.protocol.bedrock.codec.v448.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.NpcRequestSerializer_v291;
import com.nukkitx.protocol.bedrock.packet.NpcRequestPacket;
import io.netty.buffer.ByteBuf;

public class NpcRequestSerializer_v448 extends NpcRequestSerializer_v291 {
   public static final NpcRequestSerializer_v448 INSTANCE = new NpcRequestSerializer_v448();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NpcRequestPacket packet) {
      super.serialize(buffer, helper, packet);
      helper.writeString(buffer, packet.getSceneName());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NpcRequestPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setSceneName(helper.readString(buffer));
   }

   protected NpcRequestSerializer_v448() {
   }
}
