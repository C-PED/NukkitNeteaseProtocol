package com.nukkitx.protocol.bedrock.data;

import com.nukkitx.protocol.bedrock.data.command.CommandPermission;
import java.util.List;

public interface PlayerAbilityHolder {
   long getUniqueEntityId();

   void setUniqueEntityId(long var1);

   PlayerPermission getPlayerPermission();

   void setPlayerPermission(PlayerPermission var1);

   CommandPermission getCommandPermission();

   void setCommandPermission(CommandPermission var1);

   List<AbilityLayer> getAbilityLayers();

   void setAbilityLayers(List<AbilityLayer> var1);
}
