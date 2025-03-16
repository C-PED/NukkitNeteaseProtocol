package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.inventory.CraftingType;
import com.nukkitx.protocol.bedrock.packet.CraftingEventPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class CraftingEventSerializer_v291 implements BedrockPacketSerializer<CraftingEventPacket> {
   public static final CraftingEventSerializer_v291 INSTANCE = new CraftingEventSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingEventPacket packet) {
      buffer.writeByte(packet.getContainerId());
      VarInts.writeInt(buffer, packet.getType().ordinal());
      helper.writeUuid(buffer, packet.getUuid());
      List var10002 = packet.getInputs();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeItem);
      var10002 = packet.getOutputs();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeItem);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingEventPacket packet) {
      packet.setContainerId(buffer.readByte());
      packet.setType(CraftingType.values()[VarInts.readInt(buffer)]);
      packet.setUuid(helper.readUuid(buffer));
      List var10002 = packet.getInputs();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readItem, 9);
      var10002 = packet.getOutputs();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readItem, 2);
   }

   protected CraftingEventSerializer_v291() {
   }
}
