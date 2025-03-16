package com.nukkitx.protocol.bedrock.handler;

import com.nukkitx.protocol.bedrock.BedrockSession;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import io.netty.buffer.ByteBuf;
import java.util.Collection;

public interface BatchHandler {
   void handle(BedrockSession var1, ByteBuf var2, Collection<BedrockPacket> var3);
}
