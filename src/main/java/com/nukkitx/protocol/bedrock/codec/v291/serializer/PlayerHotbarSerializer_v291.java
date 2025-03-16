package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PlayerHotbarPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class PlayerHotbarSerializer_v291 implements BedrockPacketSerializer<PlayerHotbarPacket> {
   public static final PlayerHotbarSerializer_v291 INSTANCE = new PlayerHotbarSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerHotbarPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getSelectedHotbarSlot());
      buffer.writeByte(packet.getContainerId());
      buffer.writeBoolean(packet.isSelectHotbarSlot());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerHotbarPacket packet) {
      packet.setSelectedHotbarSlot(VarInts.readUnsignedInt(buffer));
      packet.setContainerId(buffer.readUnsignedByte());
      packet.setSelectHotbarSlot(buffer.readBoolean());
   }

   protected PlayerHotbarSerializer_v291() {
   }
}
