package com.nukkitx.protocol.bedrock.codec.v419.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.AnimateEntityPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.longs.LongList;
import it.unimi.dsi.fastutil.longs.LongListIterator;

public class AnimateEntitySerializer_v419 implements BedrockPacketSerializer<AnimateEntityPacket> {
   public static final AnimateEntitySerializer_v419 INSTANCE = new AnimateEntitySerializer_v419();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AnimateEntityPacket packet) {
      helper.writeString(buffer, packet.getAnimation());
      helper.writeString(buffer, packet.getNextState());
      helper.writeString(buffer, packet.getStopExpression());
      helper.writeString(buffer, packet.getController());
      buffer.writeFloatLE(packet.getBlendOutTime());
      LongList runtimeIds = packet.getRuntimeEntityIds();
      VarInts.writeUnsignedInt(buffer, runtimeIds.size());
      LongListIterator var5 = runtimeIds.iterator();

      while(var5.hasNext()) {
         long runtimeId = (Long)var5.next();
         VarInts.writeUnsignedLong(buffer, runtimeId);
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AnimateEntityPacket packet) {
      packet.setAnimation(helper.readString(buffer));
      packet.setNextState(helper.readString(buffer));
      packet.setStopExpression(helper.readString(buffer));
      packet.setController(helper.readString(buffer));
      packet.setBlendOutTime(buffer.readFloatLE());
      LongList runtimeIds = packet.getRuntimeEntityIds();
      int count = VarInts.readUnsignedInt(buffer);

      for(int i = 0; i < count; ++i) {
         runtimeIds.add(VarInts.readUnsignedLong(buffer));
      }

   }

   protected AnimateEntitySerializer_v419() {
   }
}
