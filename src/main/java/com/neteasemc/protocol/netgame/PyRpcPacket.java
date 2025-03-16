package com.neteasemc.protocol.netgame;

import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.BedrockPacketHandler;
import com.nukkitx.protocol.bedrock.packet.BedrockPacketType;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.Arrays;

public class PyRpcPacket implements BedrockPacket {
   byte[] data;
   long msgId;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PYRPC_PACKET;
   }

   public byte[] getData() {
      return this.data;
   }

   public long getMsgId() {
      return this.msgId;
   }

   public void setData(byte[] data) {
      this.data = data;
   }

   public void setMsgId(long msgId) {
      this.msgId = msgId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PyRpcPacket)) {
         return false;
      } else {
         PyRpcPacket other = (PyRpcPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.msgId != other.msgId) {
            return false;
         } else {
            return Arrays.equals(this.data, other.data);
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PyRpcPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $msgId = this.msgId;
      result = result * 59 + (int)($msgId >>> 32 ^ $msgId);
      result = result * 59 + Arrays.hashCode(this.data);
      return result;
   }

   public String toString() {
      return "PyRpcPacket(data=" + Arrays.toString(this.data) + ", msgId=" + this.msgId + ")";
   }
}
