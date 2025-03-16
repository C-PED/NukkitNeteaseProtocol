package com.neteasemc.protocol.netgame.subpacket;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.common.PacketSignal;
import io.netty.buffer.ByteBuf;

public class MasterConnectSubPacket implements SubPacket {
   private int serverId;
   private int apolloId;
   private String apolloKey;

   public int getNetgamePacketId() {
      return 1;
   }

   public void doWrite(ByteBuf buffer, BedrockCodecHelper helper) {
      buffer.writeIntLE(this.serverId);
      buffer.writeIntLE(this.apolloId);
      helper.writeString(buffer, this.apolloKey);
   }

   public void doRead(ByteBuf buffer, BedrockCodecHelper helper) {
      this.serverId = buffer.readIntLE();
      this.apolloId = buffer.readIntLE();
      this.apolloKey = helper.readString(buffer);
   }

   public PacketSignal handle(SubPacketHandler handler) {
      return handler.handle(this);
   }

   public int getServerId() {
      return this.serverId;
   }

   public int getApolloId() {
      return this.apolloId;
   }

   public String getApolloKey() {
      return this.apolloKey;
   }

   public void setServerId(int serverId) {
      this.serverId = serverId;
   }

   public void setApolloId(int apolloId) {
      this.apolloId = apolloId;
   }

   public void setApolloKey(String apolloKey) {
      this.apolloKey = apolloKey;
   }

   public String toString() {
      return "MasterConnectSubPacket(serverId=" + this.getServerId() + ", apolloId=" + this.getApolloId() + ", apolloKey=" + this.getApolloKey() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MasterConnectSubPacket)) {
         return false;
      } else {
         MasterConnectSubPacket other = (MasterConnectSubPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.serverId != other.serverId) {
            return false;
         } else if (this.apolloId != other.apolloId) {
            return false;
         } else {
            Object this$apolloKey = this.apolloKey;
            Object other$apolloKey = other.apolloKey;
            if (this$apolloKey == null) {
               if (other$apolloKey != null) {
                  return false;
               }
            } else if (!this$apolloKey.equals(other$apolloKey)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MasterConnectSubPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.serverId;
      result = result * 59 + this.apolloId;
      Object $apolloKey = this.apolloKey;
      result = result * 59 + ($apolloKey == null ? 43 : $apolloKey.hashCode());
      return result;
   }
}
