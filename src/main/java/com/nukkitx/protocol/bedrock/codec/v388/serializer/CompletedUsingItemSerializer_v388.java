package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.inventory.ItemUseType;
import com.nukkitx.protocol.bedrock.packet.CompletedUsingItemPacket;
import io.netty.buffer.ByteBuf;

public class CompletedUsingItemSerializer_v388 implements BedrockPacketSerializer<CompletedUsingItemPacket> {
   public static final CompletedUsingItemSerializer_v388 INSTANCE = new CompletedUsingItemSerializer_v388();
   private static final ItemUseType[] VALUES = ItemUseType.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CompletedUsingItemPacket packet) {
      buffer.writeShortLE(packet.getItemId());
      buffer.writeIntLE(packet.getType().ordinal() - 1);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CompletedUsingItemPacket packet) {
      packet.setItemId(buffer.readUnsignedShortLE());
      packet.setType(VALUES[buffer.readIntLE() + 1]);
   }

   private CompletedUsingItemSerializer_v388() {
   }
}
