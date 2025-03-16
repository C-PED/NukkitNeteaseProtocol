package com.nukkitx.protocol.bedrock.codec.v465.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.PhotoTransferSerializer_v291;
import com.nukkitx.protocol.bedrock.data.PhotoType;
import com.nukkitx.protocol.bedrock.packet.PhotoTransferPacket;
import io.netty.buffer.ByteBuf;

public class PhotoTransferSerializer_v465 extends PhotoTransferSerializer_v291 {
   public static final PhotoTransferSerializer_v465 INSTANCE = new PhotoTransferSerializer_v465();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PhotoTransferPacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeByte(packet.getPhotoType().ordinal());
      buffer.writeByte(packet.getSourceType().ordinal());
      buffer.writeLongLE(packet.getOwnerId());
      helper.writeString(buffer, packet.getNewPhotoName());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PhotoTransferPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setPhotoType(PhotoType.from(buffer.readByte()));
      packet.setSourceType(PhotoType.from(buffer.readByte()));
      packet.setOwnerId(buffer.readLongLE());
      packet.setNewPhotoName(helper.readString(buffer));
   }

   protected PhotoTransferSerializer_v465() {
   }
}
