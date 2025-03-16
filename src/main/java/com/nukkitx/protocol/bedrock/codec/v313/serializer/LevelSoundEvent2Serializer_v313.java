package com.nukkitx.protocol.bedrock.codec.v313.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class LevelSoundEvent2Serializer_v313 implements BedrockPacketSerializer<LevelSoundEvent2Packet> {
   private final TypeMap<SoundEvent> soundEvents;

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LevelSoundEvent2Packet packet) {
      buffer.writeByte(this.soundEvents.getId(packet.getSound()));
      helper.writeVector3f(buffer, packet.getPosition());
      VarInts.writeInt(buffer, packet.getExtraData());
      helper.writeString(buffer, packet.getIdentifier());
      buffer.writeBoolean(packet.isBabySound());
      buffer.writeBoolean(packet.isRelativeVolumeDisabled());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LevelSoundEvent2Packet packet) {
      packet.setSound(this.soundEvents.getType(buffer.readUnsignedByte()));
      packet.setPosition(helper.readVector3f(buffer));
      packet.setExtraData(VarInts.readInt(buffer));
      packet.setIdentifier(helper.readString(buffer));
      packet.setBabySound(buffer.readBoolean());
      packet.setRelativeVolumeDisabled(buffer.readBoolean());
   }

   public LevelSoundEvent2Serializer_v313(TypeMap<SoundEvent> soundEvents) {
      this.soundEvents = soundEvents;
   }
}
