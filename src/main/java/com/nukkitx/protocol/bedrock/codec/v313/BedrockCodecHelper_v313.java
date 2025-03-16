package com.nukkitx.protocol.bedrock.codec.v313;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.BedrockCodecHelper_v291;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.InventorySource;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class BedrockCodecHelper_v313 extends BedrockCodecHelper_v291 {
   public BedrockCodecHelper_v313(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes) {
      super(entityData, gameRulesTypes);
   }

   public InventorySource readSource(ByteBuf buffer) {
      InventorySource.Type type = InventorySource.Type.byId(VarInts.readUnsignedInt(buffer.duplicate()));
      return type == InventorySource.Type.UNTRACKED_INTERACTION_UI ? InventorySource.fromUntrackedInteractionUI(VarInts.readInt(buffer)) : super.readSource(buffer);
   }
}
