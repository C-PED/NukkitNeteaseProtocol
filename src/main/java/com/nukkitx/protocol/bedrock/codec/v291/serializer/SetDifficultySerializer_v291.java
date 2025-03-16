package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetDifficultyPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetDifficultySerializer_v291 implements BedrockPacketSerializer<SetDifficultyPacket> {
   public static final SetDifficultySerializer_v291 INSTANCE = new SetDifficultySerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetDifficultyPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getDifficulty());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetDifficultyPacket packet) {
      packet.setDifficulty(VarInts.readUnsignedInt(buffer));
   }

   protected SetDifficultySerializer_v291() {
   }
}
