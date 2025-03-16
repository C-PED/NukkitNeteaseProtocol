package com.nukkitx.protocol.bedrock.codec.v440.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.RemoveVolumeEntityPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class RemoveVolumeEntitySerializer_v440 implements BedrockPacketSerializer<RemoveVolumeEntityPacket> {
   public static final RemoveVolumeEntitySerializer_v440 INSTANCE = new RemoveVolumeEntitySerializer_v440();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RemoveVolumeEntityPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RemoveVolumeEntityPacket packet) {
      packet.setId(VarInts.readUnsignedInt(buffer));
   }

   protected RemoveVolumeEntitySerializer_v440() {
   }
}
