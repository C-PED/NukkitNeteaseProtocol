package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class NpcDialoguePacket implements BedrockPacket {
   private long uniqueEntityId;
   private Action action;
   private String dialogue;
   private String sceneName;
   private String npcName;
   private String actionJson;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.NPC_DIALOGUE;
   }

   public long getUniqueEntityId() {
      return this.uniqueEntityId;
   }

   public Action getAction() {
      return this.action;
   }

   public String getDialogue() {
      return this.dialogue;
   }

   public String getSceneName() {
      return this.sceneName;
   }

   public String getNpcName() {
      return this.npcName;
   }

   public String getActionJson() {
      return this.actionJson;
   }

   public void setUniqueEntityId(long uniqueEntityId) {
      this.uniqueEntityId = uniqueEntityId;
   }

   public void setAction(Action action) {
      this.action = action;
   }

   public void setDialogue(String dialogue) {
      this.dialogue = dialogue;
   }

   public void setSceneName(String sceneName) {
      this.sceneName = sceneName;
   }

   public void setNpcName(String npcName) {
      this.npcName = npcName;
   }

   public void setActionJson(String actionJson) {
      this.actionJson = actionJson;
   }

   public String toString() {
      return "NpcDialoguePacket(uniqueEntityId=" + this.getUniqueEntityId() + ", action=" + this.getAction() + ", dialogue=" + this.getDialogue() + ", sceneName=" + this.getSceneName() + ", npcName=" + this.getNpcName() + ", actionJson=" + this.getActionJson() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NpcDialoguePacket)) {
         return false;
      } else {
         NpcDialoguePacket other = (NpcDialoguePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.uniqueEntityId != other.uniqueEntityId) {
            return false;
         } else {
            Object this$action = this.action;
            Object other$action = other.action;
            if (this$action == null) {
               if (other$action != null) {
                  return false;
               }
            } else if (!this$action.equals(other$action)) {
               return false;
            }

            Object this$dialogue = this.dialogue;
            Object other$dialogue = other.dialogue;
            if (this$dialogue == null) {
               if (other$dialogue != null) {
                  return false;
               }
            } else if (!this$dialogue.equals(other$dialogue)) {
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

            Object this$npcName = this.npcName;
            Object other$npcName = other.npcName;
            if (this$npcName == null) {
               if (other$npcName != null) {
                  return false;
               }
            } else if (!this$npcName.equals(other$npcName)) {
               return false;
            }

            Object this$actionJson = this.actionJson;
            Object other$actionJson = other.actionJson;
            if (this$actionJson == null) {
               if (other$actionJson != null) {
                  return false;
               }
            } else if (!this$actionJson.equals(other$actionJson)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof NpcDialoguePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $uniqueEntityId = this.uniqueEntityId;
      result = result * 59 + (int)($uniqueEntityId >>> 32 ^ $uniqueEntityId);
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      Object $dialogue = this.dialogue;
      result = result * 59 + ($dialogue == null ? 43 : $dialogue.hashCode());
      Object $sceneName = this.sceneName;
      result = result * 59 + ($sceneName == null ? 43 : $sceneName.hashCode());
      Object $npcName = this.npcName;
      result = result * 59 + ($npcName == null ? 43 : $npcName.hashCode());
      Object $actionJson = this.actionJson;
      result = result * 59 + ($actionJson == null ? 43 : $actionJson.hashCode());
      return result;
   }

   public static enum Action {
      OPEN,
      CLOSE;

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{OPEN, CLOSE};
      }
   }
}
