package com.nukkitx.protocol.bedrock.codec.compat;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.compat.serializer.DisconnectSerializerCompat;
import com.nukkitx.protocol.bedrock.codec.compat.serializer.LoginSerializerCompat;
import com.nukkitx.protocol.bedrock.codec.compat.serializer.PlayStatusSerializerCompat;
import com.nukkitx.protocol.bedrock.codec.compat.serializer.RequestNetworkSettingsSerializerCompat;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.packet.DisconnectPacket;
import com.nukkitx.protocol.bedrock.packet.LoginPacket;
import com.nukkitx.protocol.bedrock.packet.PlayStatusPacket;
import com.nukkitx.protocol.bedrock.packet.RequestNetworkSettingsPacket;

public class BedrockCompat {
   public static BedrockCodec CODEC;
   public static BedrockCodec CODEC_LEGACY;

   static {
      CODEC = BedrockCodec.builder().helper(() -> NoopBedrockCodecHelper.INSTANCE).registerPacket(LoginPacket::new, LoginSerializerCompat.INSTANCE, 1, PacketRecipient.SERVER).registerPacket(PlayStatusPacket::new, PlayStatusSerializerCompat.INSTANCE, 2, PacketRecipient.CLIENT).registerPacket(DisconnectPacket::new, new DisconnectSerializerCompat(true), 5, PacketRecipient.BOTH).registerPacket(RequestNetworkSettingsPacket::new, RequestNetworkSettingsSerializerCompat.INSTANCE, 193, PacketRecipient.SERVER).protocolVersion(0).minecraftVersion("0.0.0").build();
      CODEC_LEGACY = CODEC.toBuilder().updateSerializer(DisconnectPacket.class, new DisconnectSerializerCompat(false)).build();
   }
}
