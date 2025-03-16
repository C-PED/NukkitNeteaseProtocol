package com.nukkitx.protocol.bedrock.codec.v527.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.PlayerPermission;
import com.nukkitx.protocol.bedrock.packet.RequestPermissionsPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class RequestPermissionsSerializer_v527 implements BedrockPacketSerializer<RequestPermissionsPacket> {
   private static final PlayerPermission[] VALUES = PlayerPermission.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RequestPermissionsPacket packet) {
      buffer.writeLongLE(packet.getUniqueEntityId());
      VarInts.writeInt(buffer, packet.getPermissions().ordinal());
      buffer.writeShortLE(packet.getCustomPermissions());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RequestPermissionsPacket packet) {
      packet.setUniqueEntityId(buffer.readLongLE());
      packet.setPermissions(VALUES[VarInts.readInt(buffer)]);
      packet.setCustomPermissions(buffer.readUnsignedShortLE());
   }
}
