package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ServerSettingsResponsePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class ServerSettingsResponseSerializer_v291 implements BedrockPacketSerializer<ServerSettingsResponsePacket> {
   public static final ServerSettingsResponseSerializer_v291 INSTANCE = new ServerSettingsResponseSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerSettingsResponsePacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getFormId());
      helper.writeString(buffer, packet.getFormData());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerSettingsResponsePacket packet) {
      packet.setFormId(VarInts.readUnsignedInt(buffer));
      packet.setFormData(helper.readString(buffer));
   }

   protected ServerSettingsResponseSerializer_v291() {
   }
}
