package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PositionTrackingDBServerBroadcastPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class PositionTrackingDBServerBroadcastSerializer_v407 implements BedrockPacketSerializer<PositionTrackingDBServerBroadcastPacket> {
   public static final PositionTrackingDBServerBroadcastSerializer_v407 INSTANCE = new PositionTrackingDBServerBroadcastSerializer_v407();
   protected static final PositionTrackingDBServerBroadcastPacket.Action[] ACTIONS = PositionTrackingDBServerBroadcastPacket.Action.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PositionTrackingDBServerBroadcastPacket packet) {
      buffer.writeByte(packet.getAction().ordinal());
      VarInts.writeInt(buffer, packet.getTrackingId());
      helper.writeTag(buffer, packet.getTag());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PositionTrackingDBServerBroadcastPacket packet) {
      packet.setAction(ACTIONS[buffer.readByte()]);
      packet.setTrackingId(VarInts.readInt(buffer));
      packet.setTag((NbtMap)helper.readTag(buffer, NbtMap.class));
   }

   protected PositionTrackingDBServerBroadcastSerializer_v407() {
   }
}
