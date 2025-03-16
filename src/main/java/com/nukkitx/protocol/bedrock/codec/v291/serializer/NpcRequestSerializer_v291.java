package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.NpcRequestType;
import com.nukkitx.protocol.bedrock.packet.NpcRequestPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class NpcRequestSerializer_v291 implements BedrockPacketSerializer<NpcRequestPacket> {
   public static final NpcRequestSerializer_v291 INSTANCE = new NpcRequestSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NpcRequestPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      buffer.writeByte(packet.getRequestType().ordinal());
      helper.writeString(buffer, packet.getCommand());
      buffer.writeByte(packet.getActionType());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NpcRequestPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setRequestType(NpcRequestType.values()[buffer.readUnsignedByte()]);
      packet.setCommand(helper.readString(buffer));
      packet.setActionType(buffer.readUnsignedByte());
   }

   protected NpcRequestSerializer_v291() {
   }
}
