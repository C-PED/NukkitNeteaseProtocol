package com.nukkitx.protocol.bedrock.transformer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.data.definitions.BlockDefinition;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataMap;
import com.nukkitx.protocol.common.util.DefinitionUtils;

public class BlockDefinitionTransformer implements EntityDataTransformer<Integer, BlockDefinition> {
   @Override
   public Integer serialize(BedrockCodecHelper helper, EntityDataMap map, BlockDefinition value) {
      if (helper.getBlockDefinitions() == null) {
         return value.getRuntimeId();
      }

      // Make sure definition is present in known block registry
      return DefinitionUtils.checkDefinition(helper.getBlockDefinitions(), value).getRuntimeId();
   }

   @Override
   public BlockDefinition deserialize(BedrockCodecHelper helper, EntityDataMap var2, Object var3) {
      if (helper.getBlockDefinitions() == null) {
         return null;
      }
      return helper.getBlockDefinitions().getDefinition((Integer) var3);
   }
}
