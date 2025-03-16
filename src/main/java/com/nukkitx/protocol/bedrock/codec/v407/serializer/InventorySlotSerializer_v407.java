package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.InventorySlotPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class InventorySlotSerializer_v407 implements BedrockPacketSerializer<InventorySlotPacket> {
   public static final InventorySlotSerializer_v407 INSTANCE = new InventorySlotSerializer_v407();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventorySlotPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getContainerId());
      VarInts.writeUnsignedInt(buffer, packet.getSlot());
      helper.writeNetItem(buffer, packet.getItem());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventorySlotPacket packet) {
      packet.setContainerId(VarInts.readUnsignedInt(buffer));
      packet.setSlot(VarInts.readUnsignedInt(buffer));
      packet.setItem(helper.readNetItem(buffer));
   }

   protected InventorySlotSerializer_v407() {
   }
}
