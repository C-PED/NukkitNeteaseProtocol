package com.nukkitx.protocol.bedrock.codec.v503.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ChangeMobPropertyPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class ChangeMobPropertySerializer_v503 implements BedrockPacketSerializer<ChangeMobPropertyPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ChangeMobPropertyPacket packet) {
      VarInts.writeLong(buffer, packet.getUniqueEntityId());
      helper.writeString(buffer, packet.getProperty());
      buffer.writeBoolean(packet.isBoolValue());
      helper.writeString(buffer, packet.getStringValue());
      VarInts.writeInt(buffer, packet.getIntValue());
      buffer.writeFloatLE(packet.getFloatValue());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ChangeMobPropertyPacket packet) {
      packet.setUniqueEntityId(VarInts.readLong(buffer));
      packet.setProperty(helper.readString(buffer));
      packet.setBoolValue(buffer.readBoolean());
      packet.setStringValue(helper.readString(buffer));
      packet.setIntValue(VarInts.readInt(buffer));
      packet.setFloatValue(buffer.readFloatLE());
   }
}
