package com.neteasemc.protocol.netgame.subpacket;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.common.PacketSignal;
import io.netty.buffer.ByteBuf;

public class ProxyUserConnectSubPacket implements SubPacket {
   private byte mCmdId;
   private long mUserId;
   private String mTransData = "";
   private boolean mBTransfer = true;
   private boolean mIsReconnect = false;
   private boolean mIsPeUser = true;
   private int mBungeeId;

   public ProxyUserConnectSubPacket() {
   }

   public ProxyUserConnectSubPacket(ByteBuf buf, BedrockCodecHelper helper) {
      this.doRead(buf, helper);
   }

   public ProxyUserConnectSubPacket(byte cmdId, long userId, int bungeeId) {
      this.mCmdId = cmdId;
      this.mUserId = userId;
      this.mBungeeId = bungeeId;
   }

   public void doWrite(ByteBuf buffer, BedrockCodecHelper helper) {
      buffer.writeByte(this.mCmdId);
      buffer.writeIntLE((int)this.mUserId);
      buffer.writeBoolean(this.mBTransfer);
      helper.writeString(buffer, this.mTransData);
      buffer.writeBoolean(this.mIsReconnect);
      buffer.writeBoolean(this.mIsPeUser);
      buffer.writeIntLE(this.mBungeeId);
   }

   public void doRead(ByteBuf buffer, BedrockCodecHelper helper) {
      this.mCmdId = buffer.readByte();
      this.mUserId = buffer.readUnsignedIntLE();
      this.mBTransfer = buffer.readBoolean();
      this.mTransData = helper.readString(buffer);
      this.mIsReconnect = buffer.readBoolean();
      this.mIsPeUser = buffer.readBoolean();
      this.mBungeeId = buffer.readIntLE();
   }

   public byte getCmdId() {
      return this.mCmdId;
   }

   public long getUserId() {
      return this.mUserId;
   }

   public int getBungeeId() {
      return this.mBungeeId;
   }

   public int getNetgamePacketId() {
      return 8;
   }

   public boolean getPeUser() {
      return this.mIsPeUser;
   }

   public PacketSignal handle(SubPacketHandler handler) {
      return handler.handle(this);
   }

   public class ProxyConnectCmdType {
      public static final byte CMD_NONE = -1;
      public static final byte CMD_REQUEST_CREATE_USERCONN = 0;
      public static final byte CMD_REPLY_CREATE_USERCONN = 1;
      public static final byte CMD_REQUEST_BREAK_USERCONN = 2;
      public static final byte CMD_REPLY_BREAK_USERCONN = 3;
   }
}
