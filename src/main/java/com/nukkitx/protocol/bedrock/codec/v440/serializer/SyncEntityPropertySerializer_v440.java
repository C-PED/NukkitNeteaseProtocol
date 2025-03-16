package com.nukkitx.protocol.bedrock.codec.v440.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SyncEntityPropertyPacket;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class SyncEntityPropertySerializer_v440 implements BedrockPacketSerializer<SyncEntityPropertyPacket> {
   public static final SyncEntityPropertySerializer_v440 INSTANCE = new SyncEntityPropertySerializer_v440();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SyncEntityPropertyPacket packet) {
      helper.writeTag(buffer, packet.getData());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SyncEntityPropertyPacket packet) {
      packet.setData((NbtMap)helper.readTag(buffer, NbtMap.class));
   }

   protected SyncEntityPropertySerializer_v440() {
   }
}
