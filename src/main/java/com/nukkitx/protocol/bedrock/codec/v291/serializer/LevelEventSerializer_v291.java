package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.packet.LevelEventPacket;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class LevelEventSerializer_v291 implements BedrockPacketSerializer<LevelEventPacket> {
   private final TypeMap<LevelEventType> typeMap;

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LevelEventPacket packet) {
      VarInts.writeInt(buffer, this.typeMap.getId(packet.getType()));
      helper.writeVector3f(buffer, packet.getPosition());
      VarInts.writeInt(buffer, packet.getData());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LevelEventPacket packet) {
      int eventId = VarInts.readInt(buffer);
      packet.setType(this.typeMap.getType(eventId));
      packet.setPosition(helper.readVector3f(buffer));
      packet.setData(VarInts.readInt(buffer));
   }

   public LevelEventSerializer_v291(TypeMap<LevelEventType> typeMap) {
      this.typeMap = typeMap;
   }
}
