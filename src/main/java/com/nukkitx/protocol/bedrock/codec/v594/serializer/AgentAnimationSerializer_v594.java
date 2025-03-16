package com.nukkitx.protocol.bedrock.codec.v594.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.AgentAnimationPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class AgentAnimationSerializer_v594 implements BedrockPacketSerializer<AgentAnimationPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AgentAnimationPacket packet) {
      buffer.writeByte(packet.getAnimation());
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AgentAnimationPacket packet) {
      packet.setAnimation(buffer.readByte());
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
   }
}
