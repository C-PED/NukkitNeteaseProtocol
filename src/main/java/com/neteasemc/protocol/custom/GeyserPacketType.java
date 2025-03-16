package com.neteasemc.protocol.custom;

public enum GeyserPacketType {
   SKIN_CONFIRM,
   CUSTOM_ITEM,
   PLAYER_INFO,
   SPIGOT_INFO,
   FORM,
   SET_ENTITY_DATA,
   NETEASE_JSON,
   TRANSFER;

   // $FF: synthetic method
   private static GeyserPacketType[] $values() {
      return new GeyserPacketType[]{SKIN_CONFIRM, CUSTOM_ITEM, PLAYER_INFO, SPIGOT_INFO, FORM, SET_ENTITY_DATA, NETEASE_JSON, TRANSFER};
   }
}
