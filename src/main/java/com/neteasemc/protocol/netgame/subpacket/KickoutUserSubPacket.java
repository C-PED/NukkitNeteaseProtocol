package com.neteasemc.protocol.netgame.subpacket;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.common.PacketSignal;
import io.netty.buffer.ByteBuf;

public class KickoutUserSubPacket implements SubPacket {
   private byte mKickoutType;
   private long mUserId;
   private int mProxyId;
   private int mGeyserId;
   private int mBungeeId;
   private String mMessage;

   public KickoutUserSubPacket() {
   }

   public KickoutUserSubPacket(ByteBuf buf, BedrockCodecHelper helper) {
      this.doRead(buf, helper);
   }

   public void doWrite(ByteBuf buffer, BedrockCodecHelper helper) {
      buffer.writeIntLE((int)this.mUserId);
      buffer.writeIntLE(this.mProxyId);
      buffer.writeIntLE(this.mGeyserId);
      buffer.writeIntLE(this.mBungeeId);
      buffer.writeByte(this.mKickoutType);
      helper.writeString(buffer, this.mMessage);
   }

   public void doRead(ByteBuf buffer, BedrockCodecHelper helper) {
      this.mUserId = buffer.readUnsignedIntLE();
      this.mProxyId = buffer.readIntLE();
      this.mGeyserId = buffer.readIntLE();
      this.mBungeeId = buffer.readIntLE();
      this.mKickoutType = buffer.readByte();
      this.mMessage = helper.readString(buffer);
   }

   public byte getKickoutType() {
      return this.mKickoutType;
   }

   public long getUserId() {
      return this.mUserId;
   }

   public int getProxyId() {
      return this.mProxyId;
   }

   public int getGeyserId() {
      return this.mGeyserId;
   }

   public String getMessage() {
      return this.mMessage;
   }

   public int getNetgamePacketId() {
      return 11;
   }

   public PacketSignal handle(SubPacketHandler handler) {
      return handler.handle(this);
   }

   public class KickoutType {
      public static final byte NoPlayer = 0;
      public static final byte DuplicatedLogin = 1;
      public static final byte GM = 2;
      public static final byte Success = 3;
      public static final byte Closed = 4;
   }
}
