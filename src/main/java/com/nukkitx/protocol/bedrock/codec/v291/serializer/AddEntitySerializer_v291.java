package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.AttributeData;
import com.nukkitx.protocol.bedrock.packet.AddEntityPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class AddEntitySerializer_v291 implements BedrockPacketSerializer<AddEntityPacket> {
   public static final AddEntitySerializer_v291 INSTANCE = new AddEntitySerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddEntityPacket packet) {
      VarInts.writeLong(buffer, packet.getUniqueEntityId());
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      VarInts.writeUnsignedInt(buffer, packet.getEntityType());
      helper.writeVector3f(buffer, packet.getPosition());
      helper.writeVector3f(buffer, packet.getMotion());
      helper.writeVector2f(buffer, packet.getRotation());
      buffer.writeFloatLE(packet.getHeadRotation());
      helper.writeArray(buffer, packet.getAttributes(), this::writeAttribute);
      helper.writeEntityData(buffer, packet.getMetadata());
      List var10002 = packet.getEntityLinks();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeEntityLink);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddEntityPacket packet) {
      packet.setUniqueEntityId(VarInts.readLong(buffer));
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setEntityType(VarInts.readUnsignedInt(buffer));
      packet.setPosition(helper.readVector3f(buffer));
      packet.setMotion(helper.readVector3f(buffer));
      packet.setRotation(helper.readVector2f(buffer));
      packet.setHeadRotation(buffer.readFloatLE());
      helper.readArray(buffer, packet.getAttributes(), this::readAttribute);
      helper.readEntityData(buffer, packet.getMetadata());
      List var10002 = packet.getEntityLinks();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readEntityLink);
   }

   public AttributeData readAttribute(ByteBuf buffer, BedrockCodecHelper helper) {
      String name = helper.readString(buffer);
      float min = buffer.readFloatLE();
      float max = buffer.readFloatLE();
      float val = buffer.readFloatLE();
      return new AttributeData(name, min, max, val);
   }

   public void writeAttribute(ByteBuf buffer, BedrockCodecHelper helper, AttributeData attribute) {
      Objects.requireNonNull(attribute, "attribute is null");
      helper.writeString(buffer, attribute.getName());
      buffer.writeFloatLE(attribute.getMinimum());
      buffer.writeFloatLE(attribute.getMaximum());
      buffer.writeFloatLE(attribute.getValue());
   }

   protected AddEntitySerializer_v291() {
   }
}
