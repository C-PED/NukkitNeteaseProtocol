package com.nukkitx.protocol.bedrock.codec.v408;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.v407.Bedrock_v407;

public class Bedrock_v408 extends Bedrock_v407 {
   public static BedrockCodec CODEC;

   static {
      CODEC = Bedrock_v407.CODEC.toBuilder().protocolVersion(408).minecraftVersion("1.16.20").build();
   }
}
