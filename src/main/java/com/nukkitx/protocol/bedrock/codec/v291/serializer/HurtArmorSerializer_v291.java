package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.HurtArmorPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class HurtArmorSerializer_v291 implements BedrockPacketSerializer<HurtArmorPacket> {
   public static final HurtArmorSerializer_v291 INSTANCE = new HurtArmorSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, HurtArmorPacket packet) {
      VarInts.writeInt(buffer, packet.getDamage());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, HurtArmorPacket packet) {
      packet.setDamage(VarInts.readInt(buffer));
   }

   protected HurtArmorSerializer_v291() {
   }
}
