package com.nukkitx.protocol.bedrock.codec.v340.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.LecternUpdatePacket;
import io.netty.buffer.ByteBuf;

public class LecternUpdateSerializer_v340 implements BedrockPacketSerializer<LecternUpdatePacket> {
   public static final LecternUpdateSerializer_v340 INSTANCE = new LecternUpdateSerializer_v340();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LecternUpdatePacket packet) {
      buffer.writeByte(packet.getPage());
      helper.writeBlockPosition(buffer, packet.getBlockPosition());
      buffer.writeBoolean(packet.isDroppingBook());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LecternUpdatePacket packet) {
      packet.setPage(buffer.readUnsignedByte());
      packet.setBlockPosition(helper.readBlockPosition(buffer));
      packet.setDroppingBook(buffer.readBoolean());
   }

   protected LecternUpdateSerializer_v340() {
   }
}
