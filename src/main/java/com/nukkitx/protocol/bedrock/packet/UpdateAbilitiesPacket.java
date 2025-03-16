package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.AbilityLayer;
import com.nukkitx.protocol.bedrock.data.PlayerAbilityHolder;
import com.nukkitx.protocol.bedrock.data.PlayerPermission;
import com.nukkitx.protocol.bedrock.data.command.CommandPermission;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class UpdateAbilitiesPacket implements BedrockPacket, PlayerAbilityHolder {
   private long uniqueEntityId;
   private PlayerPermission playerPermission;
   private CommandPermission commandPermission;
   private List<AbilityLayer> abilityLayers = new ObjectArrayList();

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.UPDATE_ABILITIES;
   }

   public long getUniqueEntityId() {
      return this.uniqueEntityId;
   }

   public PlayerPermission getPlayerPermission() {
      return this.playerPermission;
   }

   public CommandPermission getCommandPermission() {
      return this.commandPermission;
   }

   public List<AbilityLayer> getAbilityLayers() {
      return this.abilityLayers;
   }

   public void setUniqueEntityId(long uniqueEntityId) {
      this.uniqueEntityId = uniqueEntityId;
   }

   public void setPlayerPermission(PlayerPermission playerPermission) {
      this.playerPermission = playerPermission;
   }

   public void setCommandPermission(CommandPermission commandPermission) {
      this.commandPermission = commandPermission;
   }

   public void setAbilityLayers(List<AbilityLayer> abilityLayers) {
      this.abilityLayers = abilityLayers;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UpdateAbilitiesPacket)) {
         return false;
      } else {
         UpdateAbilitiesPacket other = (UpdateAbilitiesPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.uniqueEntityId != other.uniqueEntityId) {
            return false;
         } else {
            Object this$playerPermission = this.playerPermission;
            Object other$playerPermission = other.playerPermission;
            if (this$playerPermission == null) {
               if (other$playerPermission != null) {
                  return false;
               }
            } else if (!this$playerPermission.equals(other$playerPermission)) {
               return false;
            }

            Object this$commandPermission = this.commandPermission;
            Object other$commandPermission = other.commandPermission;
            if (this$commandPermission == null) {
               if (other$commandPermission != null) {
                  return false;
               }
            } else if (!this$commandPermission.equals(other$commandPermission)) {
               return false;
            }

            Object this$abilityLayers = this.abilityLayers;
            Object other$abilityLayers = other.abilityLayers;
            if (this$abilityLayers == null) {
               if (other$abilityLayers != null) {
                  return false;
               }
            } else if (!this$abilityLayers.equals(other$abilityLayers)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UpdateAbilitiesPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $uniqueEntityId = this.uniqueEntityId;
      result = result * 59 + (int)($uniqueEntityId >>> 32 ^ $uniqueEntityId);
      Object $playerPermission = this.playerPermission;
      result = result * 59 + ($playerPermission == null ? 43 : $playerPermission.hashCode());
      Object $commandPermission = this.commandPermission;
      result = result * 59 + ($commandPermission == null ? 43 : $commandPermission.hashCode());
      Object $abilityLayers = this.abilityLayers;
      result = result * 59 + ($abilityLayers == null ? 43 : $abilityLayers.hashCode());
      return result;
   }

   public String toString() {
      return "UpdateAbilitiesPacket(uniqueEntityId=" + this.uniqueEntityId + ", playerPermission=" + this.playerPermission + ", commandPermission=" + this.commandPermission + ", abilityLayers=" + this.abilityLayers + ")";
   }
}
