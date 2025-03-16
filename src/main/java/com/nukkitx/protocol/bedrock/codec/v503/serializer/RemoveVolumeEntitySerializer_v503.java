package com.nukkitx.protocol.bedrock.codec.v503.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v440.serializer.RemoveVolumeEntitySerializer_v440;
import com.nukkitx.protocol.bedrock.packet.RemoveVolumeEntityPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class RemoveVolumeEntitySerializer_v503 extends RemoveVolumeEntitySerializer_v440 {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RemoveVolumeEntityPacket packet) {
      super.serialize(buffer, helper, packet);
      VarInts.writeInt(buffer, packet.getDimension());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RemoveVolumeEntityPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setDimension(VarInts.readInt(buffer));
   }
}
