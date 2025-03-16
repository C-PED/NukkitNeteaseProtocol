package com.nukkitx.protocol.bedrock.codec.v527.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v428.serializer.PlayerAuthInputSerializer_v428;
import com.nukkitx.protocol.bedrock.data.InputInteractionModel;
import com.nukkitx.protocol.bedrock.packet.PlayerAuthInputPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class PlayerAuthInputSerializer_v527 extends PlayerAuthInputSerializer_v428 {
   protected static final InputInteractionModel[] VALUES = InputInteractionModel.values();

   protected void readInteractionModel(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
      packet.setInputInteractionModel(VALUES[VarInts.readUnsignedInt(buffer)]);
   }

   protected void writeInteractionModel(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getInputInteractionModel().ordinal());
   }
}
