package com.nukkitx.protocol.bedrock.wrapper;

import com.nukkitx.protocol.bedrock.BedrockSession;
import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import io.netty.buffer.ByteBuf;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Collection;

public abstract class BedrockWrapperSerializer {
   protected static final InternalLogger log = InternalLoggerFactory.getInstance(BedrockWrapperSerializerV9_10.class);

   public abstract void serialize(ByteBuf var1, BedrockCodec var2, Collection<BedrockPacket> var3, int var4, BedrockSession var5);

   public abstract void deserialize(ByteBuf var1, BedrockCodec var2, Collection<BedrockPacket> var3, BedrockSession var4);
}
