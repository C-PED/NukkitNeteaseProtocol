package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ItemStackRequestPacket;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class ItemStackRequestSerializer_v407 implements BedrockPacketSerializer<ItemStackRequestPacket> {
   public static final ItemStackRequestSerializer_v407 INSTANCE = new ItemStackRequestSerializer_v407();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ItemStackRequestPacket packet) {
      List var10002 = packet.getRequests();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeItemStackRequest);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ItemStackRequestPacket packet) {
      List var10002 = packet.getRequests();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readItemStackRequest, 64);
   }

   protected ItemStackRequestSerializer_v407() {
   }
}
