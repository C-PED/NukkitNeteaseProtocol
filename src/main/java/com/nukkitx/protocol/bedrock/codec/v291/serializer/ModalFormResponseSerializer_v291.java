package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ModalFormResponsePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class ModalFormResponseSerializer_v291 implements BedrockPacketSerializer<ModalFormResponsePacket> {
   public static final ModalFormResponseSerializer_v291 INSTANCE = new ModalFormResponseSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ModalFormResponsePacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getFormId());
      helper.writeString(buffer, packet.getFormData());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ModalFormResponsePacket packet) {
      packet.setFormId(VarInts.readUnsignedInt(buffer));
      packet.setFormData(helper.readString(buffer));
   }

   protected ModalFormResponseSerializer_v291() {
   }
}
