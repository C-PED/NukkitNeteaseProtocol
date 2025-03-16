package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ItemFrameDropItemPacket;
import io.netty.buffer.ByteBuf;

public class ItemFrameDropItemSerializer_v291 implements BedrockPacketSerializer<ItemFrameDropItemPacket> {
   public static final ItemFrameDropItemSerializer_v291 INSTANCE = new ItemFrameDropItemSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ItemFrameDropItemPacket packet) {
      helper.writeBlockPosition(buffer, packet.getBlockPosition());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ItemFrameDropItemPacket packet) {
      packet.setBlockPosition(helper.readBlockPosition(buffer));
   }

   protected ItemFrameDropItemSerializer_v291() {
   }
}
