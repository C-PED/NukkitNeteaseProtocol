package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.UpdateEquipPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class UpdateEquipSerializer_v291 implements BedrockPacketSerializer<UpdateEquipPacket> {
   public static final UpdateEquipSerializer_v291 INSTANCE = new UpdateEquipSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateEquipPacket packet) {
      buffer.writeByte(packet.getWindowId());
      buffer.writeByte(packet.getWindowType());
      VarInts.writeInt(buffer, packet.getSize());
      VarInts.writeLong(buffer, packet.getUniqueEntityId());
      helper.writeTag(buffer, packet.getTag());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateEquipPacket packet) {
      packet.setWindowId(buffer.readUnsignedByte());
      packet.setWindowType(buffer.readUnsignedByte());
      packet.setSize(VarInts.readInt(buffer));
      packet.setUniqueEntityId(VarInts.readLong(buffer));
      packet.setTag((NbtMap)helper.readTag(buffer, NbtMap.class));
   }

   protected UpdateEquipSerializer_v291() {
   }
}
