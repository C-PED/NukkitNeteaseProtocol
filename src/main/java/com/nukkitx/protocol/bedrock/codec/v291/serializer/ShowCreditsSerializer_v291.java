package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ShowCreditsPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class ShowCreditsSerializer_v291 implements BedrockPacketSerializer<ShowCreditsPacket> {
   public static final ShowCreditsSerializer_v291 INSTANCE = new ShowCreditsSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ShowCreditsPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      VarInts.writeInt(buffer, packet.getStatus().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ShowCreditsPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setStatus(ShowCreditsPacket.Status.values()[VarInts.readInt(buffer)]);
   }

   protected ShowCreditsSerializer_v291() {
   }
}
