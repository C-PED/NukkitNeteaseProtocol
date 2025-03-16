package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.InventoryContentPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class InventoryContentSerializer_v407 implements BedrockPacketSerializer<InventoryContentPacket> {
   public static final InventoryContentSerializer_v407 INSTANCE = new InventoryContentSerializer_v407();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryContentPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getContainerId());
      helper.writeArray(buffer, packet.getContents(), (buf, item) -> helper.writeNetItem(buf, item));
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryContentPacket packet) {
      packet.setContainerId(VarInts.readUnsignedInt(buffer));
      helper.readArray(buffer, packet.getContents(), buf -> helper.readNetItem(buf));
   }

   protected InventoryContentSerializer_v407() {
   }
}
