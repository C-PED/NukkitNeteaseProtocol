package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetEntityDataPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetEntityDataSerializer_v291 implements BedrockPacketSerializer<SetEntityDataPacket> {
   public static final SetEntityDataSerializer_v291 INSTANCE = new SetEntityDataSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetEntityDataPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      helper.writeEntityData(buffer, packet.getMetadata());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetEntityDataPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      helper.readEntityData(buffer, packet.getMetadata());
   }

   protected SetEntityDataSerializer_v291() {
   }
}
