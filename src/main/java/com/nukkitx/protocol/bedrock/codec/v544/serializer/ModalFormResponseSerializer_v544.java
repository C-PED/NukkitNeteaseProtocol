package com.nukkitx.protocol.bedrock.codec.v544.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.ModalFormResponseSerializer_v291;
import com.nukkitx.protocol.bedrock.data.ModalFormCancelReason;
import com.nukkitx.protocol.bedrock.packet.ModalFormResponsePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class ModalFormResponseSerializer_v544 extends ModalFormResponseSerializer_v291 {
   protected static final ModalFormCancelReason[] VALUES = ModalFormCancelReason.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ModalFormResponsePacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getFormId());
      Predicate var10002 = Objects::nonNull;
      String var10003 = packet.getFormData();
      Objects.requireNonNull(helper);
      helper.writeOptional(buffer, var10002, var10003, helper::writeString);
      helper.writeOptional(buffer, Optional::isPresent, packet.getCancelReason(), (buf, reason) -> buf.writeByte(((ModalFormCancelReason)reason.get()).ordinal()));
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ModalFormResponsePacket packet) {
      packet.setFormId(VarInts.readUnsignedInt(buffer));
      Objects.requireNonNull(helper);
      packet.setFormData((String)helper.readOptional(buffer, (Object)null, helper::readString));
      packet.setCancelReason((Optional)helper.readOptional(buffer, Optional.empty(), (byteBuf) -> Optional.of(VALUES[byteBuf.readByte()])));
   }
}
