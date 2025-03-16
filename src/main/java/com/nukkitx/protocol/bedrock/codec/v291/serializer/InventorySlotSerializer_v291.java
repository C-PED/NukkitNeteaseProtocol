package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.InventorySlotPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class InventorySlotSerializer_v291 implements BedrockPacketSerializer<InventorySlotPacket> {
   public static final InventorySlotSerializer_v291 INSTANCE = new InventorySlotSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventorySlotPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getContainerId());
      VarInts.writeUnsignedInt(buffer, packet.getSlot());
      helper.writeItem(buffer, packet.getItem());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventorySlotPacket packet) {
      packet.setContainerId(VarInts.readUnsignedInt(buffer));
      packet.setSlot(VarInts.readUnsignedInt(buffer));
      packet.setItem(helper.readItem(buffer));
   }

   protected InventorySlotSerializer_v291() {
   }
}
