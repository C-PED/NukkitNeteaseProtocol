package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class UnlockedRecipesPacket implements BedrockPacket {
   private ActionType action;
   private final List<String> unlockedRecipes = new ObjectArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.UNLOCKED_RECIPES;
   }

   public ActionType getAction() {
      return this.action;
   }

   public List<String> getUnlockedRecipes() {
      return this.unlockedRecipes;
   }

   public void setAction(ActionType action) {
      this.action = action;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UnlockedRecipesPacket)) {
         return false;
      } else {
         UnlockedRecipesPacket other = (UnlockedRecipesPacket)o;
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

            Object this$unlockedRecipes = this.unlockedRecipes;
            Object other$unlockedRecipes = other.unlockedRecipes;
            if (this$unlockedRecipes == null) {
               if (other$unlockedRecipes != null) {
                  return false;
               }
            } else if (!this$unlockedRecipes.equals(other$unlockedRecipes)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UnlockedRecipesPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      Object $unlockedRecipes = this.unlockedRecipes;
      result = result * 59 + ($unlockedRecipes == null ? 43 : $unlockedRecipes.hashCode());
      return result;
   }

   public String toString() {
      return "UnlockedRecipesPacket(action=" + this.action + ", unlockedRecipes=" + this.unlockedRecipes + ")";
   }

   public static enum ActionType {
      EMPTY,
      INITIALLY_UNLOCKED,
      NEWLY_UNLOCKED,
      REMOVE_UNLOCKED,
      REMOVE_ALL;

      // $FF: synthetic method
      private static ActionType[] $values() {
         return new ActionType[]{EMPTY, INITIALLY_UNLOCKED, NEWLY_UNLOCKED, REMOVE_UNLOCKED, REMOVE_ALL};
      }
   }
}
