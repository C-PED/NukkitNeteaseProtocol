package com.nukkitx.protocol.bedrock.codec.v545;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.v544.Bedrock_v544;

public class Bedrock_v545 extends Bedrock_v544 {
   public static final BedrockCodec CODEC;

   static {
      CODEC = Bedrock_v544.CODEC.toBuilder().protocolVersion(545).minecraftVersion("1.19.21").build();
   }
}
