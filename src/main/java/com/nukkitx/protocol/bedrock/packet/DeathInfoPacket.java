package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class DeathInfoPacket implements BedrockPacket {
   private String causeAttackName;
   private final List<String> messageList = new ObjectArrayList();

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.DEATH_INFO;
   }

   public String getCauseAttackName() {
      return this.causeAttackName;
   }

   public List<String> getMessageList() {
      return this.messageList;
   }

   public void setCauseAttackName(String causeAttackName) {
      this.causeAttackName = causeAttackName;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeathInfoPacket)) {
         return false;
      } else {
         DeathInfoPacket other = (DeathInfoPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$causeAttackName = this.causeAttackName;
            Object other$causeAttackName = other.causeAttackName;
            if (this$causeAttackName == null) {
               if (other$causeAttackName != null) {
                  return false;
               }
            } else if (!this$causeAttackName.equals(other$causeAttackName)) {
               return false;
            }

            Object this$messageList = this.messageList;
            Object other$messageList = other.messageList;
            if (this$messageList == null) {
               if (other$messageList != null) {
                  return false;
               }
            } else if (!this$messageList.equals(other$messageList)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof DeathInfoPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $causeAttackName = this.causeAttackName;
      result = result * 59 + ($causeAttackName == null ? 43 : $causeAttackName.hashCode());
      Object $messageList = this.messageList;
      result = result * 59 + ($messageList == null ? 43 : $messageList.hashCode());
      return result;
   }

   public String toString() {
      return "DeathInfoPacket(causeAttackName=" + this.causeAttackName + ", messageList=" + this.messageList + ")";
   }
}
