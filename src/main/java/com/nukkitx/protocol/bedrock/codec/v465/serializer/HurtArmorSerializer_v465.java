package com.nukkitx.protocol.bedrock.codec.v465.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v407.serializer.HurtArmorSerializer_v407;
import com.nukkitx.protocol.bedrock.packet.HurtArmorPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class HurtArmorSerializer_v465 extends HurtArmorSerializer_v407 {
   public static final HurtArmorSerializer_v465 INSTANCE = new HurtArmorSerializer_v465();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, HurtArmorPacket packet) {
      super.serialize(buffer, helper, packet);
      VarInts.writeUnsignedLong(buffer, packet.getArmorSlots());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, HurtArmorPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setArmorSlots(VarInts.readUnsignedLong(buffer));
   }

   protected HurtArmorSerializer_v465() {
   }
}
