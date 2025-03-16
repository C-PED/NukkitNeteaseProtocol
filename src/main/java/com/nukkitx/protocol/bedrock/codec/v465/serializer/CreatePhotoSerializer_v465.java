package com.nukkitx.protocol.bedrock.codec.v465.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.CreatePhotoPacket;
import io.netty.buffer.ByteBuf;

public class CreatePhotoSerializer_v465 implements BedrockPacketSerializer<CreatePhotoPacket> {
   public static final CreatePhotoSerializer_v465 INSTANCE = new CreatePhotoSerializer_v465();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CreatePhotoPacket packet) {
      buffer.writeLongLE(packet.getId());
      helper.writeString(buffer, packet.getPhotoName());
      helper.writeString(buffer, packet.getPhotoItemName());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CreatePhotoPacket packet) {
      packet.setId(buffer.readLongLE());
      packet.setPhotoName(helper.readString(buffer));
      packet.setPhotoItemName(helper.readString(buffer));
   }

   protected CreatePhotoSerializer_v465() {
   }
}
