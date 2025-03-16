package com.nukkitx.protocol.bedrock.codec.v354.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerType;
import com.nukkitx.protocol.bedrock.packet.UpdateTradePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class UpdateTradeSerializer_v354 implements BedrockPacketSerializer<UpdateTradePacket> {
   public static final UpdateTradeSerializer_v354 INSTANCE = new UpdateTradeSerializer_v354();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateTradePacket packet) {
      buffer.writeByte(packet.getContainerId());
      buffer.writeByte(packet.getContainerType().getId());
      VarInts.writeInt(buffer, packet.getSize());
      VarInts.writeInt(buffer, packet.getTradeTier());
      VarInts.writeLong(buffer, packet.getTraderUniqueEntityId());
      VarInts.writeLong(buffer, packet.getPlayerUniqueEntityId());
      helper.writeString(buffer, packet.getDisplayName());
      buffer.writeBoolean(packet.isNewTradingUi());
      buffer.writeBoolean(packet.isUsingEconomyTrade());
      helper.writeTag(buffer, packet.getOffers());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateTradePacket packet) {
      packet.setContainerId(buffer.readByte());
      packet.setContainerType(ContainerType.from(buffer.readByte()));
      packet.setSize(VarInts.readInt(buffer));
      packet.setTradeTier(VarInts.readInt(buffer));
      packet.setTraderUniqueEntityId(VarInts.readLong(buffer));
      packet.setPlayerUniqueEntityId(VarInts.readLong(buffer));
      packet.setDisplayName(helper.readString(buffer));
      packet.setNewTradingUi(buffer.readBoolean());
      packet.setUsingEconomyTrade(buffer.readBoolean());
      packet.setOffers((NbtMap)helper.readTag(buffer, NbtMap.class));
   }

   protected UpdateTradeSerializer_v354() {
   }
}
