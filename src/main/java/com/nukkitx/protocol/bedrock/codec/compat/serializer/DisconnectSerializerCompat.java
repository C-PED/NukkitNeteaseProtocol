package com.nukkitx.protocol.bedrock.codec.compat.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.DisconnectFailReason;
import com.nukkitx.protocol.bedrock.packet.DisconnectPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class DisconnectSerializerCompat implements BedrockPacketSerializer<DisconnectPacket> {
   private final boolean reasonEnum;

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
      if (this.reasonEnum) {
         VarInts.writeInt(buffer, packet.getReason().ordinal());
      }

      buffer.writeBoolean(packet.isMessageSkipped());
      if (!packet.isMessageSkipped()) {
         helper.writeString(buffer, packet.getKickMessage());
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, DisconnectPacket packet) {
      if (this.reasonEnum) {
         packet.setReason(DisconnectFailReason.values()[VarInts.readInt(buffer)]);
      }

      packet.setMessageSkipped(buffer.readBoolean());
      if (!packet.isMessageSkipped()) {
         packet.setKickMessage(helper.readString(buffer));
      }

   }

   public DisconnectSerializerCompat(boolean reasonEnum) {
      this.reasonEnum = reasonEnum;
   }
}
