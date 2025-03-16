package com.nukkitx.protocol.bedrock.codec.v503.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.AddVolumeEntityPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class AddVolumeEntitySerializer_v503 implements BedrockPacketSerializer<AddVolumeEntityPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddVolumeEntityPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getId());
      helper.writeTag(buffer, packet.getData());
      helper.writeString(buffer, packet.getIdentifier());
      helper.writeString(buffer, packet.getInstanceName());
      helper.writeBlockPosition(buffer, packet.getMinBounds());
      helper.writeBlockPosition(buffer, packet.getMaxBounds());
      VarInts.writeInt(buffer, packet.getDimension());
      helper.writeString(buffer, packet.getEngineVersion());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddVolumeEntityPacket packet) {
      packet.setId(VarInts.readUnsignedInt(buffer));
      packet.setData((NbtMap)helper.readTag(buffer, NbtMap.class));
      packet.setIdentifier(helper.readString(buffer));
      packet.setInstanceName(helper.readString(buffer));
      packet.setMinBounds(helper.readBlockPosition(buffer));
      packet.setMaxBounds(helper.readBlockPosition(buffer));
      packet.setDimension(VarInts.readInt(buffer));
      packet.setEngineVersion(helper.readString(buffer));
   }
}
