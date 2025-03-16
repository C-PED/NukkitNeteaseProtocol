package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PurchaseReceiptPacket;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class PurchaseReceiptSerializer_v291 implements BedrockPacketSerializer<PurchaseReceiptPacket> {
   public static final PurchaseReceiptSerializer_v291 INSTANCE = new PurchaseReceiptSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PurchaseReceiptPacket packet) {
      List var10002 = packet.getReceipts();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeString);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PurchaseReceiptPacket packet) {
      List var10002 = packet.getReceipts();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readString);
   }

   protected PurchaseReceiptSerializer_v291() {
   }
}
