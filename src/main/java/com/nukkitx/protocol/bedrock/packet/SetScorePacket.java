package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.ScoreInfo;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class SetScorePacket implements BedrockPacket {
   private Action action;
   private List<ScoreInfo> infos = new ObjectArrayList();

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_SCORE;
   }

   public Action getAction() {
      return this.action;
   }

   public List<ScoreInfo> getInfos() {
      return this.infos;
   }

   public void setAction(Action action) {
      this.action = action;
   }

   public void setInfos(List<ScoreInfo> infos) {
      this.infos = infos;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetScorePacket)) {
         return false;
      } else {
         SetScorePacket other = (SetScorePacket)o;
         if (!other.canEqual(this)) {
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

            Object this$infos = this.infos;
            Object other$infos = other.infos;
            if (this$infos == null) {
               if (other$infos != null) {
                  return false;
               }
            } else if (!this$infos.equals(other$infos)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetScorePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      Object $infos = this.infos;
      result = result * 59 + ($infos == null ? 43 : $infos.hashCode());
      return result;
   }

   public String toString() {
      return "SetScorePacket(action=" + this.action + ", infos=" + this.infos + ")";
   }

   public static enum Action {
      SET,
      REMOVE;

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{SET, REMOVE};
      }
   }
}
