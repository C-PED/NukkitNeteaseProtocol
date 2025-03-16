package com.nukkitx.protocol.bedrock.codec.v361.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.packet.LevelEventGenericPacket;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtType;

public class LevelEventGenericSerializer_v361 implements BedrockPacketSerializer<LevelEventGenericPacket> {
   private final TypeMap<LevelEventType> typeMap;

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LevelEventGenericPacket packet) {
      VarInts.writeInt(buffer, this.typeMap.getId(packet.getType()));
      helper.writeTagValue(buffer, packet.getTag());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LevelEventGenericPacket packet) {
      int eventId = VarInts.readInt(buffer);
      packet.setType(this.typeMap.getType(eventId));
      packet.setTag(helper.readTagValue(buffer, NbtType.COMPOUND));
   }

   public LevelEventGenericSerializer_v361(TypeMap<LevelEventType> typeMap) {
      this.typeMap = typeMap;
   }
}
