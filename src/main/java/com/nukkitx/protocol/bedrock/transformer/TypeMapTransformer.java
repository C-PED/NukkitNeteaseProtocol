package com.nukkitx.protocol.bedrock.transformer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataMap;
import com.nukkitx.protocol.common.util.TypeMap;

public final class TypeMapTransformer<T> implements EntityDataTransformer<Integer, T> {
   private final TypeMap<T> typeMap;

   public Integer serialize(BedrockCodecHelper helper, EntityDataMap map, T value) {
      return this.typeMap.getId(value);
   }

   @Override
   public T deserialize(BedrockCodecHelper var1, EntityDataMap var2, Object var3) {
      return this.typeMap.getType((Integer) var3);
   }

   public TypeMapTransformer(TypeMap<T> typeMap) {
      this.typeMap = typeMap;
   }
}
