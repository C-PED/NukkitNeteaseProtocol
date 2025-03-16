package com.nukkitx.protocol.bedrock.codec.v567.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.CommandRequestSerializer_v291;
import com.nukkitx.protocol.bedrock.packet.CommandRequestPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class CommandRequestSerializer_v567 extends CommandRequestSerializer_v291 {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandRequestPacket packet) {
      super.serialize(buffer, helper, packet);
      VarInts.writeInt(buffer, packet.getVersion());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandRequestPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setVersion(VarInts.readInt(buffer));
   }
}
