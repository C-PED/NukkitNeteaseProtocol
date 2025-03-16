package com.nukkitx.protocol.bedrock.transformer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataMap;

public final class BooleanTransformer implements EntityDataTransformer<Byte, Boolean> {
   public static final BooleanTransformer INSTANCE = new BooleanTransformer();

   public Byte serialize(BedrockCodecHelper helper, EntityDataMap map, Boolean value) {
      return (byte)(value == Boolean.TRUE ? 1 : 0);
   }

   @Override
   public Boolean deserialize(BedrockCodecHelper var1, EntityDataMap var2, Object var3) {
      int vaule = (int) var3;
      return vaule == 1 ? Boolean.TRUE : Boolean.FALSE;
   }

   private BooleanTransformer() {
   }
}
