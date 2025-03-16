package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.MinecraftPacket;
import com.nukkitx.protocol.common.PacketSignal;

public interface BedrockPacket extends MinecraftPacket {
   PacketSignal handle(BedrockPacketHandler var1);

   BedrockPacketType getPacketType();
}
