package com.nukkitx.protocol.bedrock.codec.v527.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.ee.LessonAction;
import com.nukkitx.protocol.bedrock.packet.LessonProgressPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class LessonProgressSerializer_v527 implements BedrockPacketSerializer<LessonProgressPacket> {
   private static final LessonAction[] ACTIONS = LessonAction.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LessonProgressPacket packet) {
      VarInts.writeInt(buffer, packet.getAction().ordinal());
      VarInts.writeInt(buffer, packet.getScore());
      helper.writeString(buffer, packet.getActivityId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LessonProgressPacket packet) {
      packet.setAction(ACTIONS[VarInts.readInt(buffer)]);
      packet.setScore(VarInts.readInt(buffer));
      packet.setActivityId(helper.readString(buffer));
   }
}
