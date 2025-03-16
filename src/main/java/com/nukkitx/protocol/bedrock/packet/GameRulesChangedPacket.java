package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.GameRuleData;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class GameRulesChangedPacket implements BedrockPacket {
   private final List<GameRuleData<?>> gameRules = new ObjectArrayList();

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.GAME_RULES_CHANGED;
   }

   public List<GameRuleData<?>> getGameRules() {
      return this.gameRules;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GameRulesChangedPacket)) {
         return false;
      } else {
         GameRulesChangedPacket other = (GameRulesChangedPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$gameRules = this.gameRules;
            Object other$gameRules = other.gameRules;
            if (this$gameRules == null) {
               if (other$gameRules != null) {
                  return false;
               }
            } else if (!this$gameRules.equals(other$gameRules)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof GameRulesChangedPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $gameRules = this.gameRules;
      result = result * 59 + ($gameRules == null ? 43 : $gameRules.hashCode());
      return result;
   }

   public String toString() {
      return "GameRulesChangedPacket(gameRules=" + this.gameRules + ")";
   }
}
