package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.AddHangingEntityPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class AddHangingEntitySerializer_v291 implements BedrockPacketSerializer<AddHangingEntityPacket> {
   public static final AddHangingEntitySerializer_v291 INSTANCE = new AddHangingEntitySerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddHangingEntityPacket packet) {
      VarInts.writeLong(buffer, packet.getUniqueEntityId());
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      helper.writeBlockPosition(buffer, packet.getPosition().toInt());
      VarInts.writeInt(buffer, packet.getDirection());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddHangingEntityPacket packet) {
      packet.setUniqueEntityId(VarInts.readLong(buffer));
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setPosition(helper.readBlockPosition(buffer).toFloat());
      packet.setDirection(VarInts.readInt(buffer));
   }

   protected AddHangingEntitySerializer_v291() {
   }
}
