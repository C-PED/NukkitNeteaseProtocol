package com.nukkitx.protocol.bedrock.codec.v448.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.NpcDialoguePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class NpcDialogueSerializer_v448 implements BedrockPacketSerializer<NpcDialoguePacket> {
   public static final NpcDialogueSerializer_v448 INSTANCE = new NpcDialogueSerializer_v448();
   private static final NpcDialoguePacket.Action[] VALUES = NpcDialoguePacket.Action.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NpcDialoguePacket packet) {
      buffer.writeLongLE(packet.getUniqueEntityId());
      VarInts.writeInt(buffer, packet.getAction().ordinal());
      helper.writeString(buffer, packet.getDialogue());
      helper.writeString(buffer, packet.getSceneName());
      helper.writeString(buffer, packet.getNpcName());
      helper.writeString(buffer, packet.getActionJson());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NpcDialoguePacket packet) {
      packet.setUniqueEntityId(buffer.readLongLE());
      packet.setAction(VALUES[VarInts.readInt(buffer)]);
      packet.setDialogue(helper.readString(buffer));
      packet.setSceneName(helper.readString(buffer));
      packet.setNpcName(helper.readString(buffer));
      packet.setActionJson(helper.readString(buffer));
   }

   protected NpcDialogueSerializer_v448() {
   }
}
