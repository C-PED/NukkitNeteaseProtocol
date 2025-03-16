package com.nukkitx.protocol.bedrock.codec.v557.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v534.serializer.AddEntitySerializer_v534;
import com.nukkitx.protocol.bedrock.packet.AddEntityPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class AddEntitySerializer_v557 extends AddEntitySerializer_v534 {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddEntityPacket packet) {
      VarInts.writeLong(buffer, packet.getUniqueEntityId());
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      helper.writeString(buffer, packet.getIdentifier());
      helper.writeVector3f(buffer, packet.getPosition());
      helper.writeVector3f(buffer, packet.getMotion());
      helper.writeVector2f(buffer, packet.getRotation());
      buffer.writeFloatLE(packet.getHeadRotation());
      buffer.writeFloatLE(packet.getBodyRotation());
      helper.writeArray(buffer, packet.getAttributes(), this::writeAttribute);
      helper.writeEntityData(buffer, packet.getMetadata());
      helper.writeEntityProperties(buffer, packet.getProperties());
      List var10002 = packet.getEntityLinks();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeEntityLink);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddEntityPacket packet) {
      packet.setUniqueEntityId(VarInts.readLong(buffer));
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setIdentifier(helper.readString(buffer));
      packet.setPosition(helper.readVector3f(buffer));
      packet.setMotion(helper.readVector3f(buffer));
      packet.setRotation(helper.readVector2f(buffer));
      packet.setHeadRotation(buffer.readFloatLE());
      packet.setBodyRotation(buffer.readFloatLE());
      helper.readArray(buffer, packet.getAttributes(), this::readAttribute);
      helper.readEntityData(buffer, packet.getMetadata());
      helper.readEntityProperties(buffer, packet.getProperties());
      List var10002 = packet.getEntityLinks();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readEntityLink);
   }
}
