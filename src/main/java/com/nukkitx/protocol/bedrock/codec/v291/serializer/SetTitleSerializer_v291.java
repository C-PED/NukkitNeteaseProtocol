package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetTitlePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetTitleSerializer_v291 implements BedrockPacketSerializer<SetTitlePacket> {
   public static final SetTitleSerializer_v291 INSTANCE = new SetTitleSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetTitlePacket packet) {
      VarInts.writeInt(buffer, packet.getType().ordinal());
      helper.writeString(buffer, packet.getText());
      VarInts.writeInt(buffer, packet.getFadeInTime());
      VarInts.writeInt(buffer, packet.getStayTime());
      VarInts.writeInt(buffer, packet.getFadeOutTime());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetTitlePacket packet) {
      packet.setType(SetTitlePacket.Type.values()[VarInts.readInt(buffer)]);
      packet.setText(helper.readString(buffer));
      packet.setFadeInTime(VarInts.readInt(buffer));
      packet.setStayTime(VarInts.readInt(buffer));
      packet.setFadeOutTime(VarInts.readInt(buffer));
   }

   protected SetTitleSerializer_v291() {
   }
}
