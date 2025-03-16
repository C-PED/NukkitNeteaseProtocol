package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetLocalPlayerAsInitializedPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetLocalPlayerAsInitializedSerializer_v291 implements BedrockPacketSerializer<SetLocalPlayerAsInitializedPacket> {
   public static final SetLocalPlayerAsInitializedSerializer_v291 INSTANCE = new SetLocalPlayerAsInitializedSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetLocalPlayerAsInitializedPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetLocalPlayerAsInitializedPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
   }

   protected SetLocalPlayerAsInitializedSerializer_v291() {
   }
}
