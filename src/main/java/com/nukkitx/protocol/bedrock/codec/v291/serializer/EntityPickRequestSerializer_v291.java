package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.EntityPickRequestPacket;
import io.netty.buffer.ByteBuf;

public class EntityPickRequestSerializer_v291 implements BedrockPacketSerializer<EntityPickRequestPacket> {
   public static final EntityPickRequestSerializer_v291 INSTANCE = new EntityPickRequestSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EntityPickRequestPacket packet) {
      buffer.writeLongLE(packet.getRuntimeEntityId());
      buffer.writeByte(packet.getHotbarSlot());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EntityPickRequestPacket packet) {
      packet.setRuntimeEntityId(buffer.readLongLE());
      packet.setHotbarSlot(buffer.readUnsignedByte());
   }

   protected EntityPickRequestSerializer_v291() {
   }
}
