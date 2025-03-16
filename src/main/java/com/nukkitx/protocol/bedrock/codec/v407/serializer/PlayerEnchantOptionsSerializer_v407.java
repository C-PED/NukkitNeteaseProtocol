package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.inventory.EnchantData;
import com.nukkitx.protocol.bedrock.data.inventory.EnchantOptionData;
import com.nukkitx.protocol.bedrock.packet.PlayerEnchantOptionsPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class PlayerEnchantOptionsSerializer_v407 implements BedrockPacketSerializer<PlayerEnchantOptionsPacket> {
   public static final PlayerEnchantOptionsSerializer_v407 INSTANCE = new PlayerEnchantOptionsSerializer_v407();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerEnchantOptionsPacket packet) {
      helper.writeArray(buffer, packet.getOptions(), this::writeOption);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerEnchantOptionsPacket packet) {
      helper.readArray(buffer, packet.getOptions(), this::readOption);
   }

   protected void writeOption(ByteBuf buffer, BedrockCodecHelper helper, EnchantOptionData option) {
      VarInts.writeUnsignedInt(buffer, option.getCost());
      buffer.writeIntLE(option.getPrimarySlot());
      helper.writeArray(buffer, option.getEnchants0(), this::serializeEnchant);
      helper.writeArray(buffer, option.getEnchants1(), this::serializeEnchant);
      helper.writeArray(buffer, option.getEnchants2(), this::serializeEnchant);
      helper.writeArray(buffer, option.getEnchants3(), this::serializeEnchant);
      helper.writeString(buffer, option.getEnchantName());
      VarInts.writeUnsignedInt(buffer, option.getEnchantNetId());
   }

   protected EnchantOptionData readOption(ByteBuf buffer, BedrockCodecHelper helper) {
      int cost = VarInts.readUnsignedInt(buffer);
      int primarySlot = buffer.readIntLE();
      List<EnchantData> enchants1 = new ObjectArrayList();
      helper.readArray(buffer, enchants1, this::deserializeEnchant);
      List<EnchantData> enchants2 = new ObjectArrayList();
      helper.readArray(buffer, enchants2, this::deserializeEnchant);
      List<EnchantData> enchants3 = new ObjectArrayList();
      helper.readArray(buffer, enchants3, this::deserializeEnchant);
      List<EnchantData> enchants4 = new ObjectArrayList();
      helper.readArray(buffer, enchants4, this::deserializeEnchant);
      String enchantName = helper.readString(buffer);
      int enchantNetId = VarInts.readUnsignedInt(buffer);
      return new EnchantOptionData(cost, primarySlot, enchants1, enchants2, enchants3, enchants4, enchantName, enchantNetId);
   }

   protected void serializeEnchant(ByteBuf buffer, BedrockCodecHelper helper, EnchantData enchant) {
      buffer.writeByte(enchant.getType());
      buffer.writeByte(enchant.getLevel());
      helper.writeString(buffer, enchant.getModEhchantIdentifier());
   }

   protected EnchantData deserializeEnchant(ByteBuf buffer, BedrockCodecHelper helper) {
      int type = buffer.readUnsignedByte();
      int level = buffer.readUnsignedByte();
      String modEnchantId = helper.readString(buffer);
      return new EnchantData(type, level, modEnchantId);
   }

   protected PlayerEnchantOptionsSerializer_v407() {
   }
}
