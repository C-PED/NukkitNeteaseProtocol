package com.nukkitx.protocol.bedrock.codec.v527.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.Ability;
import com.nukkitx.protocol.bedrock.packet.RequestAbilityPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class RequestAbilitySerializer_v527 implements BedrockPacketSerializer<RequestAbilityPacket> {
   protected static final Ability[] ABILITIES = Ability.values();
   protected static final Ability.Type[] TYPES = Ability.Type.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RequestAbilityPacket packet) {
      VarInts.writeInt(buffer, packet.getAbility().ordinal());
      buffer.writeByte(packet.getType().ordinal());
      buffer.writeBoolean(packet.isBoolValue());
      buffer.writeFloatLE(packet.getFloatValue());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RequestAbilityPacket packet) {
      packet.setAbility(ABILITIES[VarInts.readInt(buffer)]);
      packet.setType(TYPES[buffer.readUnsignedByte()]);
      packet.setBoolValue(buffer.readBoolean());
      packet.setFloatValue(buffer.readFloatLE());
   }
}
