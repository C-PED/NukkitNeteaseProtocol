package com.nukkitx.protocol.bedrock.codec.v465.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v440.serializer.AddVolumeEntitySerializer_v440;
import com.nukkitx.protocol.bedrock.packet.AddVolumeEntityPacket;
import io.netty.buffer.ByteBuf;

public class AddVolumeEntitySerializer_v465 extends AddVolumeEntitySerializer_v440 {
   public static final AddVolumeEntitySerializer_v465 INSTANCE = new AddVolumeEntitySerializer_v465();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddVolumeEntityPacket packet) {
      super.serialize(buffer, helper, packet);
      helper.writeString(buffer, packet.getEngineVersion());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddVolumeEntityPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setEngineVersion(helper.readString(buffer));
   }

   protected AddVolumeEntitySerializer_v465() {
   }
}
