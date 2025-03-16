package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.AttributeData;
import com.nukkitx.protocol.bedrock.packet.UpdateAttributesPacket;
import com.nukkitx.protocol.common.util.Preconditions;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class UpdateAttributesSerializer_v291 implements BedrockPacketSerializer<UpdateAttributesPacket> {
   public static final UpdateAttributesSerializer_v291 INSTANCE = new UpdateAttributesSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateAttributesPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      helper.writeArray(buffer, packet.getAttributes(), this::writeAttribute);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateAttributesPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      helper.readArray(buffer, packet.getAttributes(), this::readAttribute);
   }

   public AttributeData readAttribute(ByteBuf buffer, BedrockCodecHelper helper) {
      float min = buffer.readFloatLE();
      float max = buffer.readFloatLE();
      float val = buffer.readFloatLE();
      float def = buffer.readFloatLE();
      String name = helper.readString(buffer);
      return new AttributeData(name, min, max, val, def);
   }

   public void writeAttribute(ByteBuf buffer, BedrockCodecHelper helper, AttributeData attribute) {
      Preconditions.checkNotNull(attribute, "attribute");
      buffer.writeFloatLE(attribute.getMinimum());
      buffer.writeFloatLE(attribute.getMaximum());
      buffer.writeFloatLE(attribute.getValue());
      buffer.writeFloatLE(attribute.getDefaultValue());
      helper.writeString(buffer, attribute.getName());
   }

   protected UpdateAttributesSerializer_v291() {
   }
}
