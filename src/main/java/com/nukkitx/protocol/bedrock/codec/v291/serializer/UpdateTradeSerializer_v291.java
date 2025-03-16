package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerType;
import com.nukkitx.protocol.bedrock.packet.UpdateTradePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class UpdateTradeSerializer_v291 implements BedrockPacketSerializer<UpdateTradePacket> {
   public static final UpdateTradeSerializer_v291 INSTANCE = new UpdateTradeSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateTradePacket packet) {
      buffer.writeByte(packet.getContainerId());
      buffer.writeByte(packet.getContainerType().getId());
      VarInts.writeInt(buffer, packet.getSize());
      VarInts.writeInt(buffer, packet.isUsingEconomyTrade() ? 40 : 0);
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
      packet.setUsingEconomyTrade(VarInts.readInt(buffer) >= 40);
      packet.setRecipeAddedOnUpdate(buffer.readBoolean());
      packet.setTraderUniqueEntityId(VarInts.readLong(buffer));
      packet.setPlayerUniqueEntityId(VarInts.readLong(buffer));
      packet.setDisplayName(helper.readString(buffer));
      packet.setOffers((NbtMap)helper.readTag(buffer, NbtMap.class));
   }

   protected UpdateTradeSerializer_v291() {
   }
}
