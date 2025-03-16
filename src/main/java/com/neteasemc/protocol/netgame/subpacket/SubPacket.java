package com.neteasemc.protocol.netgame.subpacket;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.common.PacketSignal;
import io.netty.buffer.ByteBuf;

public interface SubPacket {
   int getNetgamePacketId();

   default void doWrite(ByteBuf buffer, BedrockCodecHelper helper) {
      throw new UnsupportedOperationException("subpacket's do write not implements");
   }

   default void doRead(ByteBuf buffer, BedrockCodecHelper helper) {
      throw new UnsupportedOperationException("subpacket's do read not implements");
   }

   default void serialize(ByteBuf buffer, BedrockCodecHelper helper) {
      this.doWrite(buffer, helper);
   }

   default void deserialize(ByteBuf buffer, BedrockCodecHelper helper) {
      this.doRead(buffer, helper);
   }

   default PacketSignal handle(SubPacketHandler handler) {
      return PacketSignal.UNHANDLED;
   }
}
