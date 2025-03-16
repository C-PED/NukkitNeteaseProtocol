package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.MobArmorEquipmentPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class MobArmorEquipmentSerializer_v291 implements BedrockPacketSerializer<MobArmorEquipmentPacket> {
   public static final MobArmorEquipmentSerializer_v291 INSTANCE = new MobArmorEquipmentSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MobArmorEquipmentPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      helper.writeItem(buffer, packet.getHelmet());
      helper.writeItem(buffer, packet.getChestplate());
      helper.writeItem(buffer, packet.getLeggings());
      helper.writeItem(buffer, packet.getBoots());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MobArmorEquipmentPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setHelmet(helper.readItem(buffer));
      packet.setChestplate(helper.readItem(buffer));
      packet.setLeggings(helper.readItem(buffer));
      packet.setBoots(helper.readItem(buffer));
   }

   protected MobArmorEquipmentSerializer_v291() {
   }
}
