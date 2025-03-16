package com.nukkitx.protocol.bedrock.wrapper;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public final class BedrockWrapperSerializers {
   private static final Int2ObjectMap<BedrockWrapperSerializer> SERIALIZERS = new Int2ObjectOpenHashMap();

   public static BedrockWrapperSerializer getSerializer(int protocolVersion) {
      return (BedrockWrapperSerializer)SERIALIZERS.get(protocolVersion);
   }

   private BedrockWrapperSerializers() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   static {
      SERIALIZERS.put(7, BedrockWrapperSerializerV7.INSTANCE);
      SERIALIZERS.put(8, BedrockWrapperSerializerV9_10.V10);
      SERIALIZERS.put(9, BedrockWrapperSerializerV9_10.V9);
      SERIALIZERS.put(10, BedrockWrapperSerializerV9_10.V10);
      SERIALIZERS.defaultReturnValue(BedrockWrapperSerializerV9_10.V9);
   }
}
