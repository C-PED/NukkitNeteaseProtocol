package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.BlockEntityDataPacket;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class BlockEntityDataSerializer_v291 implements BedrockPacketSerializer<BlockEntityDataPacket> {
   public static final BlockEntityDataSerializer_v291 INSTANCE = new BlockEntityDataSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BlockEntityDataPacket packet) {
      helper.writeBlockPosition(buffer, packet.getBlockPosition());
      helper.writeTag(buffer, packet.getData());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BlockEntityDataPacket packet) {
      packet.setBlockPosition(helper.readBlockPosition(buffer));
      packet.setData((NbtMap)helper.readTag(buffer, NbtMap.class));
   }

   protected BlockEntityDataSerializer_v291() {
   }
}
