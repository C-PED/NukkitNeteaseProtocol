package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ShowStoreOfferPacket;
import io.netty.buffer.ByteBuf;

public class ShowStoreOfferSerializer_v291 implements BedrockPacketSerializer<ShowStoreOfferPacket> {
   public static final ShowStoreOfferSerializer_v291 INSTANCE = new ShowStoreOfferSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ShowStoreOfferPacket packet) {
      helper.writeString(buffer, packet.getOfferId());
      buffer.writeBoolean(packet.isShownToAll());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ShowStoreOfferPacket packet) {
      packet.setOfferId(helper.readString(buffer));
      packet.setShownToAll(buffer.readBoolean());
   }

   protected ShowStoreOfferSerializer_v291() {
   }
}
