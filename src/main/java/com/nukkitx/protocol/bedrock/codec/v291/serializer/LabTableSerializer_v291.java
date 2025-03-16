package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.inventory.LabTableReactionType;
import com.nukkitx.protocol.bedrock.data.inventory.LabTableType;
import com.nukkitx.protocol.bedrock.packet.LabTablePacket;
import io.netty.buffer.ByteBuf;

public class LabTableSerializer_v291 implements BedrockPacketSerializer<LabTablePacket> {
   public static final LabTableSerializer_v291 INSTANCE = new LabTableSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LabTablePacket packet) {
      buffer.writeByte(packet.getType().ordinal());
      helper.writeVector3i(buffer, packet.getPosition());
      buffer.writeByte(packet.getReactionType().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LabTablePacket packet) {
      packet.setType(LabTableType.values()[buffer.readUnsignedByte()]);
      packet.setPosition(helper.readVector3i(buffer));
      packet.setReactionType(LabTableReactionType.values()[buffer.readUnsignedByte()]);
   }

   protected LabTableSerializer_v291() {
   }
}
