package com.neteasemc.protocol.netgame.subpacket;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.common.PacketSignal;
import io.netty.buffer.ByteBuf;

public class PingServerRequestSubPacket implements SubPacket {
   public void doWrite(ByteBuf buffer, BedrockCodecHelper helper) {
   }

   public void doRead(ByteBuf buffer, BedrockCodecHelper helper) {
   }

   public int getNetgamePacketId() {
      return 9;
   }

   public PacketSignal handle(SubPacketHandler handler) {
      return handler.handle(this);
   }
}
