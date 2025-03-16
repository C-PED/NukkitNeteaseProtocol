package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.CommandRequestPacket;
import io.netty.buffer.ByteBuf;

public class CommandRequestSerializer_v291 implements BedrockPacketSerializer<CommandRequestPacket> {
   public static final CommandRequestSerializer_v291 INSTANCE = new CommandRequestSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandRequestPacket packet) {
      helper.writeString(buffer, packet.getCommand());
      helper.writeCommandOrigin(buffer, packet.getCommandOriginData());
      buffer.writeBoolean(packet.isInternal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandRequestPacket packet) {
      packet.setCommand(helper.readString(buffer));
      packet.setCommandOriginData(helper.readCommandOrigin(buffer));
      packet.setInternal(buffer.readBoolean());
   }

   protected CommandRequestSerializer_v291() {
   }
}
