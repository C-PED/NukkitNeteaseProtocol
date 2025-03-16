package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerType;
import com.nukkitx.protocol.bedrock.packet.ContainerOpenPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class ContainerOpenSerializer_v291 implements BedrockPacketSerializer<ContainerOpenPacket> {
   public static final ContainerOpenSerializer_v291 INSTANCE = new ContainerOpenSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ContainerOpenPacket packet) {
      buffer.writeByte(packet.getId());
      buffer.writeByte(packet.getType().getId());
      helper.writeBlockPosition(buffer, packet.getBlockPosition());
      VarInts.writeLong(buffer, packet.getUniqueEntityId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ContainerOpenPacket packet) {
      packet.setId(buffer.readByte());
      packet.setType(ContainerType.from(buffer.readByte()));
      packet.setBlockPosition(helper.readBlockPosition(buffer));
      packet.setUniqueEntityId(VarInts.readLong(buffer));
   }

   protected ContainerOpenSerializer_v291() {
   }
}
