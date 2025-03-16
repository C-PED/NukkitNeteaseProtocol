package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PhotoTransferPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class PhotoTransferSerializer_v291 implements BedrockPacketSerializer<PhotoTransferPacket> {
   public static final PhotoTransferSerializer_v291 INSTANCE = new PhotoTransferSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PhotoTransferPacket packet) {
      helper.writeString(buffer, packet.getName());
      byte[] data = packet.getData();
      VarInts.writeUnsignedInt(buffer, data.length);
      buffer.writeBytes(data);
      helper.writeString(buffer, packet.getBookId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PhotoTransferPacket packet) {
      packet.setName(helper.readString(buffer));
      byte[] data = new byte[VarInts.readUnsignedInt(buffer)];
      buffer.readBytes(data);
      packet.setData(data);
      packet.setBookId(helper.readString(buffer));
   }

   protected PhotoTransferSerializer_v291() {
   }
}
