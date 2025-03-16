package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ExplodePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class ExplodeSerializer_v291 implements BedrockPacketSerializer<ExplodePacket> {
   public static final ExplodeSerializer_v291 INSTANCE = new ExplodeSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ExplodePacket packet) {
      helper.writeVector3f(buffer, packet.getPosition());
      VarInts.writeInt(buffer, (int)(packet.getRadius() * 32.0F));
      List var10002 = packet.getRecords();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeVector3i);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ExplodePacket packet) {
      packet.setPosition(helper.readVector3f(buffer));
      packet.setRadius((float)VarInts.readInt(buffer) / 32.0F);
      List var10002 = packet.getRecords();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readVector3i);
   }

   protected ExplodeSerializer_v291() {
   }
}
