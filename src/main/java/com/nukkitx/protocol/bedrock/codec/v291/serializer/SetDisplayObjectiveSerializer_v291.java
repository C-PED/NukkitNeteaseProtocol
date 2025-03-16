package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetDisplayObjectivePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetDisplayObjectiveSerializer_v291 implements BedrockPacketSerializer<SetDisplayObjectivePacket> {
   public static final SetDisplayObjectiveSerializer_v291 INSTANCE = new SetDisplayObjectiveSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetDisplayObjectivePacket packet) {
      helper.writeString(buffer, packet.getDisplaySlot());
      helper.writeString(buffer, packet.getObjectiveId());
      helper.writeString(buffer, packet.getDisplayName());
      helper.writeString(buffer, packet.getCriteria());
      VarInts.writeInt(buffer, packet.getSortOrder());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetDisplayObjectivePacket packet) {
      packet.setDisplaySlot(helper.readString(buffer));
      packet.setObjectiveId(helper.readString(buffer));
      packet.setDisplayName(helper.readString(buffer));
      packet.setCriteria(helper.readString(buffer));
      packet.setSortOrder(VarInts.readInt(buffer));
   }

   protected SetDisplayObjectiveSerializer_v291() {
   }
}
