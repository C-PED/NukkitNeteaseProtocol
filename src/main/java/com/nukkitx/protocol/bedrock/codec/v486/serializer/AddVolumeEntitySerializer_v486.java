package com.nukkitx.protocol.bedrock.codec.v486.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.AddVolumeEntitySerializer_v465;
import com.nukkitx.protocol.bedrock.packet.AddVolumeEntityPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class AddVolumeEntitySerializer_v486 extends AddVolumeEntitySerializer_v465 {
   public static final AddVolumeEntitySerializer_v486 INSTANCE = new AddVolumeEntitySerializer_v486();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddVolumeEntityPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getId());
      helper.writeTag(buffer, packet.getData());
      helper.writeString(buffer, packet.getIdentifier());
      helper.writeString(buffer, packet.getInstanceName());
      helper.writeString(buffer, packet.getEngineVersion());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddVolumeEntityPacket packet) {
      packet.setId(VarInts.readUnsignedInt(buffer));
      packet.setData((NbtMap)helper.readTag(buffer, NbtMap.class));
      packet.setIdentifier(helper.readString(buffer));
      packet.setInstanceName(helper.readString(buffer));
      packet.setEngineVersion(helper.readString(buffer));
   }

   protected AddVolumeEntitySerializer_v486() {
   }
}
