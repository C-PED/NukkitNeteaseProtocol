package com.nukkitx.protocol.bedrock.data.inventory;

public enum ItemUseType {
   UNKNOWN,
   EQUIP_ARMOR,
   EAT,
   ATTACK,
   CONSUME,
   THROW,
   SHOOT,
   PLACE,
   FILL_BOTTLE,
   FILL_BUCKET,
   POUR_BUCKET,
   USE_TOOL,
   INTERACT,
   RETRIEVED,
   DYED,
   TRADED,
   BRUSHING_COMPLETED;

   // $FF: synthetic method
   private static ItemUseType[] $values() {
      return new ItemUseType[]{UNKNOWN, EQUIP_ARMOR, EAT, ATTACK, CONSUME, THROW, SHOOT, PLACE, FILL_BOTTLE, FILL_BUCKET, POUR_BUCKET, USE_TOOL, INTERACT, RETRIEVED, DYED, TRADED, BRUSHING_COMPLETED};
   }
}
