package com.nukkitx.protocol.bedrock.codec.v419.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.UpdateAttributesSerializer_v291;
import com.nukkitx.protocol.bedrock.packet.UpdateAttributesPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class UpdateAttributesSerializer_v419 extends UpdateAttributesSerializer_v291 {
   public static final UpdateAttributesSerializer_v419 INSTANCE = new UpdateAttributesSerializer_v419();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateAttributesPacket packet) {
      super.serialize(buffer, helper, packet);
      VarInts.writeUnsignedLong(buffer, packet.getTick());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateAttributesPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setTick(VarInts.readUnsignedLong(buffer));
   }

   protected UpdateAttributesSerializer_v419() {
   }
}
