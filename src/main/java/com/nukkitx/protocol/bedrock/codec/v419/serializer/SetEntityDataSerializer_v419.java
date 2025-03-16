package com.nukkitx.protocol.bedrock.codec.v419.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.SetEntityDataSerializer_v291;
import com.nukkitx.protocol.bedrock.packet.SetEntityDataPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetEntityDataSerializer_v419 extends SetEntityDataSerializer_v291 {
   public static final SetEntityDataSerializer_v419 INSTANCE = new SetEntityDataSerializer_v419();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetEntityDataPacket packet) {
      super.serialize(buffer, helper, packet);
      VarInts.writeUnsignedLong(buffer, packet.getTick());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetEntityDataPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setTick(VarInts.readUnsignedLong(buffer));
   }

   protected SetEntityDataSerializer_v419() {
   }
}
