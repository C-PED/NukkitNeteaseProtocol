package com.nukkitx.protocol.bedrock.codec.v428.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.CameraShakeSerializer_v419;
import com.nukkitx.protocol.bedrock.data.CameraShakeAction;
import com.nukkitx.protocol.bedrock.packet.CameraShakePacket;
import io.netty.buffer.ByteBuf;

public class CameraShakeSerializer_v428 extends CameraShakeSerializer_v419 {
   public static final CameraShakeSerializer_v428 INSTANCE = new CameraShakeSerializer_v428();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraShakePacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeByte(packet.getShakeAction().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraShakePacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setShakeAction(CameraShakeAction.values()[buffer.readByte()]);
   }

   protected CameraShakeSerializer_v428() {
   }
}
