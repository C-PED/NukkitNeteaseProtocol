package com.nukkitx.protocol.bedrock.codec.v448.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.SetTitleSerializer_v291;
import com.nukkitx.protocol.bedrock.packet.SetTitlePacket;
import io.netty.buffer.ByteBuf;

public class SetTitleSerializer_v448 extends SetTitleSerializer_v291 {
   public static final SetTitleSerializer_v448 INSTANCE = new SetTitleSerializer_v448();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetTitlePacket packet) {
      super.serialize(buffer, helper, packet);
      helper.writeString(buffer, packet.getXuid());
      helper.writeString(buffer, packet.getPlatformOnlineId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetTitlePacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setXuid(helper.readString(buffer));
      packet.setPlatformOnlineId(helper.readString(buffer));
   }

   protected SetTitleSerializer_v448() {
   }
}
