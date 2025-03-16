package com.nukkitx.protocol.bedrock.codec.v486.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.CodeBuilderCategoryType;
import com.nukkitx.protocol.bedrock.data.CodeBuilderOperationType;
import com.nukkitx.protocol.bedrock.packet.CodeBuilderSourcePacket;
import io.netty.buffer.ByteBuf;

public class CodeBuilderSourceSerializer_v486 implements BedrockPacketSerializer<CodeBuilderSourcePacket> {
   public static final CodeBuilderSourceSerializer_v486 INSTANCE = new CodeBuilderSourceSerializer_v486();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CodeBuilderSourcePacket packet) {
      buffer.writeByte(packet.getOperation().ordinal());
      buffer.writeByte(packet.getCategory().ordinal());
      helper.writeString(buffer, packet.getValue());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CodeBuilderSourcePacket packet) {
      packet.setOperation(CodeBuilderOperationType.values()[buffer.readByte()]);
      packet.setCategory(CodeBuilderCategoryType.values()[buffer.readByte()]);
      packet.setValue(helper.readString(buffer));
   }

   protected CodeBuilderSourceSerializer_v486() {
   }
}
