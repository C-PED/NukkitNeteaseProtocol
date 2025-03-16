package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.entity.EntityEventType;
import com.nukkitx.protocol.bedrock.packet.EntityEventPacket;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class EntityEventSerializer_v291 implements BedrockPacketSerializer<EntityEventPacket> {
   private static final InternalLogger log = InternalLoggerFactory.getInstance(EntityEventSerializer_v291.class);
   private final TypeMap<EntityEventType> typeMap;

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EntityEventPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      buffer.writeByte(this.typeMap.getId(packet.getType()));
      VarInts.writeInt(buffer, packet.getData());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EntityEventPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      int event = buffer.readUnsignedByte();
      packet.setType(this.typeMap.getType(event));
      packet.setData(VarInts.readInt(buffer));
      if (packet.getType() == null) {
         log.debug("Unknown EntityEvent {} in packet {}", event, packet);
      }

   }

   public EntityEventSerializer_v291(TypeMap<EntityEventType> typeMap) {
      this.typeMap = typeMap;
   }
}
