package com.nukkitx.protocol.bedrock.codec.compat;

import com.neteasemc.protocol.netgame.NeteaseNetGameTransferBatchPacket;
import com.neteasemc.protocol.netgame.NeteaseNetGameTransferPacket;
import com.neteasemc.protocol.netgame.NetworkGamePacket;
import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.compat.neNetGameserializer.NeNetGameSerializerCompat;
import com.nukkitx.protocol.bedrock.codec.compat.neNetGameserializer.NeNetGameTransferBatchSerializerCompat;
import com.nukkitx.protocol.bedrock.codec.compat.neNetGameserializer.NeNetGameTransferSerializerCompat;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;

public class NeBedrockProxyCompat {
   public static BedrockCodec NETEASE_PROXY_COMPAT_CODEC;

   static {
      NETEASE_PROXY_COMPAT_CODEC = BedrockCodec.builder().helper(() -> NoopBedrockCodecHelper.INSTANCE).registerPacket(NetworkGamePacket.class, NeNetGameSerializerCompat.INSTANCE, 254, PacketRecipient.BOTH).registerPacket(NeteaseNetGameTransferPacket.class, NeNetGameTransferSerializerCompat.INSTANCE, 253, PacketRecipient.BOTH).registerPacket(NeteaseNetGameTransferBatchPacket.class, NeNetGameTransferBatchSerializerCompat.INSTANCE, 251, PacketRecipient.BOTH).protocolVersion(0).minecraftVersion("0.0.0").build();
   }
}
