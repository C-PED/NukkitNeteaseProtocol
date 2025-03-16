package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.NpcRequestType;
import com.nukkitx.protocol.common.PacketSignal;

public class NpcRequestPacket implements BedrockPacket {
   private long runtimeEntityId;
   private NpcRequestType requestType;
   private String command;
   private int actionType;
   private String sceneName;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.NPC_REQUEST;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public NpcRequestType getRequestType() {
      return this.requestType;
   }

   public String getCommand() {
      return this.command;
   }

   public int getActionType() {
      return this.actionType;
   }

   public String getSceneName() {
      return this.sceneName;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setRequestType(NpcRequestType requestType) {
      this.requestType = requestType;
   }

   public void setCommand(String command) {
      this.command = command;
   }

   public void setActionType(int actionType) {
      this.actionType = actionType;
   }

   public void setSceneName(String sceneName) {
      this.sceneName = sceneName;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NpcRequestPacket)) {
         return false;
      } else {
         NpcRequestPacket other = (NpcRequestPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.actionType != other.actionType) {
            return false;
         } else {
            Object this$requestType = this.requestType;
            Object other$requestType = other.requestType;
            if (this$requestType == null) {
               if (other$requestType != null) {
                  return false;
               }
            } else if (!this$requestType.equals(other$requestType)) {
               return false;
            }

            Object this$command = this.command;
            Object other$command = other.command;
            if (this$command == null) {
               if (other$command != null) {
                  return false;
               }
            } else if (!this$command.equals(other$command)) {
               return false;
            }

            Object this$sceneName = this.sceneName;
            Object other$sceneName = other.sceneName;
            if (this$sceneName == null) {
               if (other$sceneName != null) {
                  return false;
               }
            } else if (!this$sceneName.equals(other$sceneName)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof NpcRequestPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      result = result * 59 + this.actionType;
      Object $requestType = this.requestType;
      result = result * 59 + ($requestType == null ? 43 : $requestType.hashCode());
      Object $command = this.command;
      result = result * 59 + ($command == null ? 43 : $command.hashCode());
      Object $sceneName = this.sceneName;
      result = result * 59 + ($sceneName == null ? 43 : $sceneName.hashCode());
      return result;
   }

   public String toString() {
      return "NpcRequestPacket(runtimeEntityId=" + this.runtimeEntityId + ", requestType=" + this.requestType + ", command=" + this.command + ", actionType=" + this.actionType + ", sceneName=" + this.sceneName + ")";
   }
}
