package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.command.SoftEnumUpdateType;
import com.nukkitx.protocol.bedrock.packet.UpdateSoftEnumPacket;
import io.netty.buffer.ByteBuf;

public class UpdateSoftEnumSerializer_v291 implements BedrockPacketSerializer<UpdateSoftEnumPacket> {
   public static final UpdateSoftEnumSerializer_v291 INSTANCE = new UpdateSoftEnumSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateSoftEnumPacket packet) {
      helper.writeCommandEnum(buffer, packet.getSoftEnum());
      buffer.writeByte(packet.getType().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateSoftEnumPacket packet) {
      packet.setSoftEnum(helper.readCommandEnum(buffer, true));
      packet.setType(SoftEnumUpdateType.values()[buffer.readByte()]);
   }

   protected UpdateSoftEnumSerializer_v291() {
   }
}
