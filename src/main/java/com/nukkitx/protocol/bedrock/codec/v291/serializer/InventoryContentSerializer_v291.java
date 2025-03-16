package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.InventoryContentPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class InventoryContentSerializer_v291 implements BedrockPacketSerializer<InventoryContentPacket> {
   public static final InventoryContentSerializer_v291 INSTANCE = new InventoryContentSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryContentPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getContainerId());
      helper.writeArray(buffer, packet.getContents(), (buf, item) -> helper.writeItem(buf, item));
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryContentPacket packet) {
      packet.setContainerId(VarInts.readUnsignedInt(buffer));
      helper.readArray(buffer, packet.getContents(), buf -> helper.readItem(buf));
   }

   protected InventoryContentSerializer_v291() {
   }
}
