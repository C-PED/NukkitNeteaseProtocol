package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.BlockPickRequestPacket;
import io.netty.buffer.ByteBuf;

public class BlockPickRequestSerializer_v291 implements BedrockPacketSerializer<BlockPickRequestPacket> {
   public static final BlockPickRequestSerializer_v291 INSTANCE = new BlockPickRequestSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BlockPickRequestPacket packet) {
      helper.writeVector3i(buffer, packet.getBlockPosition());
      buffer.writeBoolean(packet.isAddUserData());
      buffer.writeByte(packet.getHotbarSlot());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BlockPickRequestPacket packet) {
      packet.setBlockPosition(helper.readVector3i(buffer));
      packet.setAddUserData(buffer.readBoolean());
      packet.setHotbarSlot(buffer.readUnsignedByte());
   }

   protected BlockPickRequestSerializer_v291() {
   }
}
