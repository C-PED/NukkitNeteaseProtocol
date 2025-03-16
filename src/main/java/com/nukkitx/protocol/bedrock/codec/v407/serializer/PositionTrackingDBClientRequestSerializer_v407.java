package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PositionTrackingDBClientRequestPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class PositionTrackingDBClientRequestSerializer_v407 implements BedrockPacketSerializer<PositionTrackingDBClientRequestPacket> {
   public static final PositionTrackingDBClientRequestSerializer_v407 INSTANCE = new PositionTrackingDBClientRequestSerializer_v407();
   protected static final PositionTrackingDBClientRequestPacket.Action[] ACTIONS = PositionTrackingDBClientRequestPacket.Action.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PositionTrackingDBClientRequestPacket packet) {
      buffer.writeByte(packet.getAction().ordinal());
      VarInts.writeInt(buffer, packet.getTrackingId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PositionTrackingDBClientRequestPacket packet) {
      packet.setAction(ACTIONS[buffer.readByte()]);
      packet.setTrackingId(VarInts.readInt(buffer));
   }

   protected PositionTrackingDBClientRequestSerializer_v407() {
   }
}
