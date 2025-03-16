package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.RiderJumpPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class RiderJumpSerializer_v291 implements BedrockPacketSerializer<RiderJumpPacket> {
   public static final RiderJumpSerializer_v291 INSTANCE = new RiderJumpSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RiderJumpPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getJumpStrength());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RiderJumpPacket packet) {
      packet.setJumpStrength(VarInts.readInt(buffer));
   }

   protected RiderJumpSerializer_v291() {
   }
}
