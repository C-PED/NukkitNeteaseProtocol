package com.nukkitx.protocol.bedrock.codec.v332.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class LevelSoundEventSerializer_v332 implements BedrockPacketSerializer<LevelSoundEventPacket> {
   private final TypeMap<SoundEvent> typeMap;

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LevelSoundEventPacket packet) {
      VarInts.writeUnsignedInt(buffer, this.typeMap.getId(packet.getSound()));
      helper.writeVector3f(buffer, packet.getPosition());
      VarInts.writeInt(buffer, packet.getExtraData());
      helper.writeString(buffer, packet.getIdentifier());
      buffer.writeBoolean(packet.isBabySound());
      buffer.writeBoolean(packet.isRelativeVolumeDisabled());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LevelSoundEventPacket packet) {
      packet.setSound(this.typeMap.getType(VarInts.readUnsignedInt(buffer)));
      packet.setPosition(helper.readVector3f(buffer));
      packet.setExtraData(VarInts.readInt(buffer));
      packet.setIdentifier(helper.readString(buffer));
      packet.setBabySound(buffer.readBoolean());
      packet.setRelativeVolumeDisabled(buffer.readBoolean());
   }

   public LevelSoundEventSerializer_v332(TypeMap<SoundEvent> typeMap) {
      this.typeMap = typeMap;
   }
}
