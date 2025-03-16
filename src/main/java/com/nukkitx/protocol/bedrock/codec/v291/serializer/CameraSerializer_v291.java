package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.CameraPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class CameraSerializer_v291 implements BedrockPacketSerializer<CameraPacket> {
   public static final CameraSerializer_v291 INSTANCE = new CameraSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraPacket packet) {
      VarInts.writeLong(buffer, packet.getCameraUniqueEntityId());
      VarInts.writeLong(buffer, packet.getPlayerUniqueEntityId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraPacket packet) {
      packet.setCameraUniqueEntityId(VarInts.readLong(buffer));
      packet.setPlayerUniqueEntityId(VarInts.readLong(buffer));
   }

   protected CameraSerializer_v291() {
   }
}
