package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.GuiDataPickItemPacket;
import io.netty.buffer.ByteBuf;

public class GuiDataPickItemSerializer_v291 implements BedrockPacketSerializer<GuiDataPickItemPacket> {
   public static final GuiDataPickItemSerializer_v291 INSTANCE = new GuiDataPickItemSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, GuiDataPickItemPacket packet) {
      helper.writeString(buffer, packet.getDescription());
      helper.writeString(buffer, packet.getItemEffects());
      buffer.writeIntLE(packet.getHotbarSlot());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, GuiDataPickItemPacket packet) {
      packet.setDescription(helper.readString(buffer));
      packet.setItemEffects(helper.readString(buffer));
      packet.setHotbarSlot(buffer.readIntLE());
   }

   protected GuiDataPickItemSerializer_v291() {
   }
}
