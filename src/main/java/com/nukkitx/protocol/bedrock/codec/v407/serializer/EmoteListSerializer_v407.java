package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.EmoteListPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class EmoteListSerializer_v407 implements BedrockPacketSerializer<EmoteListPacket> {
   public static final EmoteListSerializer_v407 INSTANCE = new EmoteListSerializer_v407();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EmoteListPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      List var10002 = packet.getPieceIds();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeUuid);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EmoteListPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      List var10002 = packet.getPieceIds();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readUuid);
   }

   protected EmoteListSerializer_v407() {
   }
}
