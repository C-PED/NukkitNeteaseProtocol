package com.neteasemc.protocol.netgame.subpacket;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.common.PacketSignal;
import io.netty.buffer.ByteBuf;

public class PingServerResponseSubPacket implements SubPacket {
   private int mOnlineNum;
   private long mAppVersion;
   private int mPeOnline;
   private int mPcOnline;

   public PingServerResponseSubPacket() {
   }

   public PingServerResponseSubPacket(int onlineNum, long appVersion, int peOnline, int pcOnline) {
      this.mOnlineNum = onlineNum;
      this.mAppVersion = appVersion;
      this.mPeOnline = peOnline;
      this.mPcOnline = pcOnline;
   }

   public void doWrite(ByteBuf buffer, BedrockCodecHelper helper) {
      buffer.writeIntLE(this.mOnlineNum);
      buffer.writeIntLE((int)this.mAppVersion);
      buffer.writeIntLE(this.mPeOnline);
      buffer.writeIntLE(this.mPcOnline);
   }

   public void doRead(ByteBuf buffer, BedrockCodecHelper helper) {
   }

   public int getNetgamePacketId() {
      return 10;
   }

   public PacketSignal handle(SubPacketHandler handler) {
      return handler.handle(this);
   }
}
