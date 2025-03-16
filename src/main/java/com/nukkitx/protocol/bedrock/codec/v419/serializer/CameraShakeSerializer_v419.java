package com.nukkitx.protocol.bedrock.codec.v419.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.CameraShakeType;
import com.nukkitx.protocol.bedrock.packet.CameraShakePacket;
import io.netty.buffer.ByteBuf;

public class CameraShakeSerializer_v419 implements BedrockPacketSerializer<CameraShakePacket> {
   public static final CameraShakeSerializer_v419 INSTANCE = new CameraShakeSerializer_v419();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraShakePacket packet) {
      buffer.writeFloatLE(packet.getIntensity());
      buffer.writeFloatLE(packet.getDuration());
      buffer.writeByte(packet.getShakeType().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraShakePacket packet) {
      packet.setIntensity(buffer.readFloatLE());
      packet.setDuration(buffer.readFloatLE());
      packet.setShakeType(CameraShakeType.values()[buffer.readByte()]);
   }

   protected CameraShakeSerializer_v419() {
   }
}
