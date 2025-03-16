package com.nukkitx.protocol.bedrock.codec.v471.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PhotoInfoRequestPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class PhotoInfoRequestSerializer_v471 implements BedrockPacketSerializer<PhotoInfoRequestPacket> {
   public static final PhotoInfoRequestSerializer_v471 INSTANCE = new PhotoInfoRequestSerializer_v471();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PhotoInfoRequestPacket packet) {
      VarInts.writeLong(buffer, packet.getPhotoId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PhotoInfoRequestPacket packet) {
      packet.setPhotoId(VarInts.readLong(buffer));
   }

   protected PhotoInfoRequestSerializer_v471() {
   }
}
