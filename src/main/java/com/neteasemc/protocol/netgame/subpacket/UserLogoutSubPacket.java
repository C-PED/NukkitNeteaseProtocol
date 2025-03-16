package com.neteasemc.protocol.netgame.subpacket;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.common.PacketSignal;
import io.netty.buffer.ByteBuf;

public class UserLogoutSubPacket implements SubPacket {
   private byte mReason;
   private int mNeteaseId;
   private String mMessage;
   private boolean mIsPeUser = true;

   public UserLogoutSubPacket() {
   }

   public UserLogoutSubPacket(int neteaseId, byte reason, String message) {
      this.mNeteaseId = neteaseId;
      this.mReason = reason;
      this.mMessage = message;
   }

   public UserLogoutSubPacket(ByteBuf buf, BedrockCodecHelper helper) {
      this.doRead(buf, helper);
   }

   public void doWrite(ByteBuf buffer, BedrockCodecHelper helper) {
      buffer.writeIntLE(this.mNeteaseId);
      buffer.writeByte(this.mReason);
      helper.writeString(buffer, this.mMessage);
      buffer.writeBoolean(this.mIsPeUser);
   }

   public void doRead(ByteBuf buffer, BedrockCodecHelper helper) {
      this.mNeteaseId = buffer.readIntLE();
      this.mReason = buffer.readByte();
      this.mMessage = helper.readString(buffer);
      this.mIsPeUser = buffer.readBoolean();
   }

   public byte getUserLogoutReason() {
      return this.mReason;
   }

   public long getNeteaseId() {
      return (long)this.mNeteaseId;
   }

   public String getMessage() {
      return this.mMessage;
   }

   public boolean getIsPeUser() {
      return this.mIsPeUser;
   }

   public int getNetgamePacketId() {
      return 12;
   }

   public PacketSignal handle(SubPacketHandler handler) {
      return handler.handle(this);
   }

   public class UserLogoutReason {
      public static final byte NotExist = 0;
      public static final byte AnotherLogin = 1;
      public static final byte GMKick = 2;
      public static final byte Leave = 3;
      public static final byte ShutDown = 4;
   }
}
