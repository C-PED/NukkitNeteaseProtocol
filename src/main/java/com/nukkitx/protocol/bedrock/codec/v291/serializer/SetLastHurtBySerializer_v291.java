package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetLastHurtByPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetLastHurtBySerializer_v291 implements BedrockPacketSerializer<SetLastHurtByPacket> {
   public static final SetLastHurtBySerializer_v291 INSTANCE = new SetLastHurtBySerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetLastHurtByPacket packet) {
      VarInts.writeInt(buffer, packet.getEntityTypeId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetLastHurtByPacket packet) {
      packet.setEntityTypeId(VarInts.readInt(buffer));
   }

   protected SetLastHurtBySerializer_v291() {
   }
}
