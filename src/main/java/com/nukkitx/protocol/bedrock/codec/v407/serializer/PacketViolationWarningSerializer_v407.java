package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.PacketViolationSeverity;
import com.nukkitx.protocol.bedrock.data.PacketViolationType;
import com.nukkitx.protocol.bedrock.packet.PacketViolationWarningPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class PacketViolationWarningSerializer_v407 implements BedrockPacketSerializer<PacketViolationWarningPacket> {
   public static final PacketViolationWarningSerializer_v407 INSTANCE = new PacketViolationWarningSerializer_v407();
   protected static final PacketViolationType[] TYPES = PacketViolationType.values();
   protected static final PacketViolationSeverity[] SEVERITIES = PacketViolationSeverity.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PacketViolationWarningPacket packet) {
      VarInts.writeInt(buffer, packet.getType().ordinal() - 1);
      VarInts.writeInt(buffer, packet.getSeverity().ordinal() - 1);
      VarInts.writeInt(buffer, packet.getPacketCauseId());
      helper.writeString(buffer, packet.getContext());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PacketViolationWarningPacket packet) {
      packet.setType(TYPES[VarInts.readInt(buffer) + 1]);
      packet.setSeverity(SEVERITIES[VarInts.readInt(buffer) + 1]);
      packet.setPacketCauseId(VarInts.readInt(buffer));
      packet.setContext(helper.readString(buffer));
   }

   protected PacketViolationWarningSerializer_v407() {
   }
}
