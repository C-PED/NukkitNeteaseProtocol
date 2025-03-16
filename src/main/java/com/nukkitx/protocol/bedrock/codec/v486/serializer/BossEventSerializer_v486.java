package com.nukkitx.protocol.bedrock.codec.v486.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.BossEventSerializer_v291;
import com.nukkitx.protocol.bedrock.packet.BossEventPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class BossEventSerializer_v486 extends BossEventSerializer_v291 {
   public static final BossEventSerializer_v486 INSTANCE = new BossEventSerializer_v486();

   protected void serializeAction(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
      if (packet.getAction() == BossEventPacket.Action.QUERY) {
         VarInts.writeLong(buffer, packet.getPlayerUniqueEntityId());
      } else {
         super.serializeAction(buffer, helper, packet);
      }

   }

   protected void deserializeAction(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
      if (packet.getAction() == BossEventPacket.Action.QUERY) {
         packet.setPlayerUniqueEntityId(VarInts.readLong(buffer));
      } else {
         super.deserializeAction(buffer, helper, packet);
      }

   }

   protected BossEventSerializer_v486() {
   }
}
