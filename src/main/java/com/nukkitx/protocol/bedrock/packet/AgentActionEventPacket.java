package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.ee.AgentActionType;
import com.nukkitx.protocol.common.PacketSignal;

public class AgentActionEventPacket implements BedrockPacket {
   private String requestId;
   private AgentActionType actionType;
   private String responseJson;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.AGENT_ACTION_EVENT;
   }

   public String getRequestId() {
      return this.requestId;
   }

   public AgentActionType getActionType() {
      return this.actionType;
   }

   public String getResponseJson() {
      return this.responseJson;
   }

   public void setRequestId(String requestId) {
      this.requestId = requestId;
   }

   public void setActionType(AgentActionType actionType) {
      this.actionType = actionType;
   }

   public void setResponseJson(String responseJson) {
      this.responseJson = responseJson;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AgentActionEventPacket)) {
         return false;
      } else {
         AgentActionEventPacket other = (AgentActionEventPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$requestId = this.requestId;
            Object other$requestId = other.requestId;
            if (this$requestId == null) {
               if (other$requestId != null) {
                  return false;
               }
            } else if (!this$requestId.equals(other$requestId)) {
               return false;
            }

            Object this$actionType = this.actionType;
            Object other$actionType = other.actionType;
            if (this$actionType == null) {
               if (other$actionType != null) {
                  return false;
               }
            } else if (!this$actionType.equals(other$actionType)) {
               return false;
            }

            Object this$responseJson = this.responseJson;
            Object other$responseJson = other.responseJson;
            if (this$responseJson == null) {
               if (other$responseJson != null) {
                  return false;
               }
            } else if (!this$responseJson.equals(other$responseJson)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AgentActionEventPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $requestId = this.requestId;
      result = result * 59 + ($requestId == null ? 43 : $requestId.hashCode());
      Object $actionType = this.actionType;
      result = result * 59 + ($actionType == null ? 43 : $actionType.hashCode());
      Object $responseJson = this.responseJson;
      result = result * 59 + ($responseJson == null ? 43 : $responseJson.hashCode());
      return result;
   }

   public String toString() {
      return "AgentActionEventPacket(requestId=" + this.requestId + ", actionType=" + this.actionType + ", responseJson=" + this.responseJson + ")";
   }
}
