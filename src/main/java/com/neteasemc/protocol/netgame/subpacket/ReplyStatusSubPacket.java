package com.neteasemc.protocol.netgame.subpacket;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.common.PacketSignal;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;

public class ReplyStatusSubPacket implements SubPacket {
   ServerReplyStatus status;
   long version = 0L;
   int networkProtocolVersion = 0;
   Map<String, String> modVersionMap = new HashMap();

   public int getNetgamePacketId() {
      return 2;
   }

   public void doWrite(ByteBuf buffer, BedrockCodecHelper helper) {
      buffer.writeByte(this.status.ordinal());
      buffer.writeIntLE((int)this.version);
      buffer.writeIntLE(this.networkProtocolVersion);
      VarInts.writeUnsignedInt(buffer, this.modVersionMap.size());

      for(Map.Entry<String, String> entry : this.modVersionMap.entrySet()) {
         helper.writeString(buffer, (String)entry.getKey());
         helper.writeString(buffer, (String)entry.getValue());
      }

   }

   public void doRead(ByteBuf buffer, BedrockCodecHelper helper) {
      this.status = ServerReplyStatus.values()[buffer.readByte()];
      this.version = buffer.readUnsignedInt();
      this.networkProtocolVersion = buffer.readIntLE();
      int size = VarInts.readUnsignedInt(buffer);

      for(int i = 0; i < size; ++i) {
         this.modVersionMap.put(helper.readString(buffer), helper.readString(buffer));
      }

   }

   public PacketSignal handle(SubPacketHandler handler) {
      return handler.handle(this);
   }

   public int getNetworkProtocolVersion() {
      return this.networkProtocolVersion;
   }

   public Map<String, String> getModVersionMap() {
      return this.modVersionMap;
   }

   public void setNetworkProtocolVersion(int networkProtocolVersion) {
      this.networkProtocolVersion = networkProtocolVersion;
   }

   public void setModVersionMap(Map<String, String> modVersionMap) {
      this.modVersionMap = modVersionMap;
   }

   public String toString() {
      return "ReplyStatusSubPacket(status=" + this.getStatus() + ", version=" + this.getVersion() + ", networkProtocolVersion=" + this.getNetworkProtocolVersion() + ", modVersionMap=" + this.getModVersionMap() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ReplyStatusSubPacket)) {
         return false;
      } else {
         ReplyStatusSubPacket other = (ReplyStatusSubPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.version != other.version) {
            return false;
         } else if (this.networkProtocolVersion != other.networkProtocolVersion) {
            return false;
         } else {
            Object this$status = this.status;
            Object other$status = other.status;
            if (this$status == null) {
               if (other$status != null) {
                  return false;
               }
            } else if (!this$status.equals(other$status)) {
               return false;
            }

            Object this$modVersionMap = this.modVersionMap;
            Object other$modVersionMap = other.modVersionMap;
            if (this$modVersionMap == null) {
               if (other$modVersionMap != null) {
                  return false;
               }
            } else if (!this$modVersionMap.equals(other$modVersionMap)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ReplyStatusSubPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $version = this.version;
      result = result * 59 + (int)($version >>> 32 ^ $version);
      result = result * 59 + this.networkProtocolVersion;
      Object $status = this.status;
      result = result * 59 + ($status == null ? 43 : $status.hashCode());
      Object $modVersionMap = this.modVersionMap;
      result = result * 59 + ($modVersionMap == null ? 43 : $modVersionMap.hashCode());
      return result;
   }

   public ServerReplyStatus getStatus() {
      return this.status;
   }

   public void setStatus(ServerReplyStatus status) {
      this.status = status;
   }

   public long getVersion() {
      return this.version;
   }

   public void setVersion(long version) {
      this.version = version;
   }

   public static enum ServerReplyStatus {
      None,
      Normal,
      Abnormal;

      // $FF: synthetic method
      private static ServerReplyStatus[] $values() {
         return new ServerReplyStatus[]{None, Normal, Abnormal};
      }
   }
}
