package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.HurtArmorPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class HurtArmorSerializer_v407 implements BedrockPacketSerializer<HurtArmorPacket> {
   public static final HurtArmorSerializer_v407 INSTANCE = new HurtArmorSerializer_v407();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, HurtArmorPacket packet) {
      VarInts.writeInt(buffer, packet.getCause());
      VarInts.writeInt(buffer, packet.getDamage());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, HurtArmorPacket packet) {
      packet.setCause(VarInts.readInt(buffer));
      packet.setDamage(VarInts.readInt(buffer));
   }

   protected HurtArmorSerializer_v407() {
   }
}
