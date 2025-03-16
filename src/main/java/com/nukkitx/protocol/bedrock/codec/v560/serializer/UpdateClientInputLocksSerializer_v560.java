package com.nukkitx.protocol.bedrock.codec.v560.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.UpdateClientInputLocksPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class UpdateClientInputLocksSerializer_v560 implements BedrockPacketSerializer<UpdateClientInputLocksPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateClientInputLocksPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getLockComponentData());
      helper.writeVector3f(buffer, packet.getServerPosition());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateClientInputLocksPacket packet) {
      packet.setLockComponentData(VarInts.readUnsignedInt(buffer));
      packet.setServerPosition(helper.readVector3f(buffer));
   }
}
