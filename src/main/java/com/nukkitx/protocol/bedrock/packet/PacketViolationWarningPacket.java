package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.PacketViolationSeverity;
import com.nukkitx.protocol.bedrock.data.PacketViolationType;
import com.nukkitx.protocol.common.PacketSignal;

public class PacketViolationWarningPacket implements BedrockPacket {
   private PacketViolationType type;
   private PacketViolationSeverity severity;
   private int packetCauseId;
   private String context;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PACKET_VIOLATION_WARNING;
   }

   public PacketViolationType getType() {
      return this.type;
   }

   public PacketViolationSeverity getSeverity() {
      return this.severity;
   }

   public int getPacketCauseId() {
      return this.packetCauseId;
   }

   public String getContext() {
      return this.context;
   }

   public void setType(PacketViolationType type) {
      this.type = type;
   }

   public void setSeverity(PacketViolationSeverity severity) {
      this.severity = severity;
   }

   public void setPacketCauseId(int packetCauseId) {
      this.packetCauseId = packetCauseId;
   }

   public void setContext(String context) {
      this.context = context;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PacketViolationWarningPacket)) {
         return false;
      } else {
         PacketViolationWarningPacket other = (PacketViolationWarningPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.packetCauseId != other.packetCauseId) {
            return false;
         } else {
            Object this$type = this.type;
            Object other$type = other.type;
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$severity = this.severity;
            Object other$severity = other.severity;
            if (this$severity == null) {
               if (other$severity != null) {
                  return false;
               }
            } else if (!this$severity.equals(other$severity)) {
               return false;
            }

            Object this$context = this.context;
            Object other$context = other.context;
            if (this$context == null) {
               if (other$context != null) {
                  return false;
               }
            } else if (!this$context.equals(other$context)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PacketViolationWarningPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.packetCauseId;
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $severity = this.severity;
      result = result * 59 + ($severity == null ? 43 : $severity.hashCode());
      Object $context = this.context;
      result = result * 59 + ($context == null ? 43 : $context.hashCode());
      return result;
   }

   public String toString() {
      return "PacketViolationWarningPacket(type=" + this.type + ", severity=" + this.severity + ", packetCauseId=" + this.packetCauseId + ", context=" + this.context + ")";
   }
}
