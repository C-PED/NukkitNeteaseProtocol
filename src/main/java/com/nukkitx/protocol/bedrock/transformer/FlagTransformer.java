package com.nukkitx.protocol.bedrock.transformer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataMap;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.common.util.TypeMap;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.EnumSet;

public final class FlagTransformer implements EntityDataTransformer<Long, EnumSet<EntityFlag>> {
   private static final InternalLogger log = InternalLoggerFactory.getInstance(FlagTransformer.class);
   private final TypeMap<EntityFlag> typeMap;
   private final int index;

   public Long serialize(BedrockCodecHelper helper, EntityDataMap map, EnumSet<EntityFlag> flags) {
      long value = 0L;
      int lower = this.index * 64;
      int upper = lower + 64;

      for(EntityFlag flag : flags) {
         int flagIndex = this.typeMap.getId(flag);
         if (flagIndex >= lower && flagIndex < upper) {
            value |= 1L << (flagIndex & 63);
         }
      }

      return value;
   }

   @Override
   public EnumSet<EntityFlag> deserialize(BedrockCodecHelper var1, EntityDataMap var2, Object var3) {
      EnumSet<EntityFlag> flags = var2.getOrCreateFlags();
      int lower = this.index * 64;
      int upper = lower + 64;

      for(int i = lower; i < upper; ++i) {
         int idx = i & 63;
         Long vaule = (Long) var3;
         if ((vaule & 1L << idx) != 0L) {
            EntityFlag flag = this.typeMap.getType(i);
            if (flag != null) {
               flags.add(flag);
            } else {
               log.debug("Unknown entity flag detected with index {}", i);
            }
         }
      }

      return flags;
   }

   public FlagTransformer(TypeMap<EntityFlag> typeMap, int index) {
      this.typeMap = typeMap;
      this.index = index;
   }
}
