package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.packet.CreativeContentPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class CreativeContentSerializer_v407 implements BedrockPacketSerializer<CreativeContentPacket> {
   public static final CreativeContentSerializer_v407 INSTANCE = new CreativeContentSerializer_v407();
   private static final ItemData[] EMPTY = new ItemData[0];

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CreativeContentPacket packet) {
      helper.writeArray(buffer, packet.getContents(), this::writeCreativeItem);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CreativeContentPacket packet) {
      packet.setContents((ItemData[])helper.readArray(buffer, EMPTY, this::readCreativeItem));
   }

   protected ItemData readCreativeItem(ByteBuf buffer, BedrockCodecHelper helper) {
      int netId = VarInts.readUnsignedInt(buffer);
      ItemData item = helper.readItemInstance(buffer);
      item.setNetId(netId);
      return item;
   }

   protected void writeCreativeItem(ByteBuf buffer, BedrockCodecHelper helper, ItemData item) {
      VarInts.writeUnsignedInt(buffer, item.getNetId());
      helper.writeItemInstance(buffer, item);
   }

   protected CreativeContentSerializer_v407() {
   }
}
