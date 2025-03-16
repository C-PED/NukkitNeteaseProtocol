package com.nukkitx.protocol.bedrock.codec.v361.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.UpdateBlockPropertiesPacket;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class UpdateBlockPropertiesSerializer_v361 implements BedrockPacketSerializer<UpdateBlockPropertiesPacket> {
   public static final UpdateBlockPropertiesSerializer_v361 INSTANCE = new UpdateBlockPropertiesSerializer_v361();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateBlockPropertiesPacket packet) {
      helper.writeTag(buffer, packet.getProperties());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateBlockPropertiesPacket packet) {
      packet.setProperties((NbtMap)helper.readTag(buffer, NbtMap.class));
   }

   protected UpdateBlockPropertiesSerializer_v361() {
   }
}
