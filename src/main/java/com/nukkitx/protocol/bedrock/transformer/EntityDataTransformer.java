package com.nukkitx.protocol.bedrock.transformer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataMap;

public interface EntityDataTransformer<S, D> {
   EntityDataTransformer<?, ?> IDENTITY = new EntityDataTransformer<Object, Object>() {
      public Object serialize(BedrockCodecHelper helper, EntityDataMap map, Object value) {
         return value;
      }

      public Object deserialize(BedrockCodecHelper helper, EntityDataMap map, Object value) {
         return value;
      }
   };

   static <S, D> EntityDataTransformer<S, D> identity() {
      // 返回通配符类型的 IDENTITY
      return (EntityDataTransformer<S, D>) IDENTITY;
   }

   S serialize(BedrockCodecHelper var1, EntityDataMap var2, D var3);

   D deserialize(BedrockCodecHelper var1, EntityDataMap var2, Object var3);
}
