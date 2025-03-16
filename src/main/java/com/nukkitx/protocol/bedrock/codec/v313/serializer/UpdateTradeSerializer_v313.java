package com.nukkitx.protocol.bedrock.codec.v313.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerType;
import com.nukkitx.protocol.bedrock.packet.UpdateTradePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class UpdateTradeSerializer_v313 implements BedrockPacketSerializer<UpdateTradePacket> {
   public static final UpdateTradeSerializer_v313 INSTANCE = new UpdateTradeSerializer_v313();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateTradePacket packet) {
      buffer.writeByte(packet.getContainerId());
      buffer.writeByte(packet.getContainerType().getId());
      VarInts.writeInt(buffer, packet.getSize());
      VarInts.writeInt(buffer, packet.isNewTradingUi() ? 40 : 0);
      VarInts.writeInt(buffer, packet.getTradeTier());
      buffer.writeBoolean(packet.isRecipeAddedOnUpdate());
      VarInts.writeLong(buffer, packet.getTraderUniqueEntityId());
      VarInts.writeLong(buffer, packet.getPlayerUniqueEntityId());
      helper.writeString(buffer, packet.getDisplayName());
      helper.writeTag(buffer, packet.getOffers());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateTradePacket packet) {
      packet.setContainerId(buffer.readByte());
      packet.setContainerType(ContainerType.from(buffer.readByte()));
      packet.setSize(VarInts.readInt(buffer));
      packet.setNewTradingUi(VarInts.readInt(buffer) >= 40);
      packet.setTradeTier(VarInts.readInt(buffer));
      packet.setRecipeAddedOnUpdate(buffer.readBoolean());
      packet.setTraderUniqueEntityId(VarInts.readLong(buffer));
      packet.setPlayerUniqueEntityId(VarInts.readLong(buffer));
      packet.setDisplayName(helper.readString(buffer));
      packet.setOffers((NbtMap)helper.readTag(buffer, NbtMap.class));
   }

   protected UpdateTradeSerializer_v313() {
   }
}
