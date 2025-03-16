package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.AddPlayerPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class AddPlayerSerializer_v291 implements BedrockPacketSerializer<AddPlayerPacket> {
   public static final AddPlayerSerializer_v291 INSTANCE = new AddPlayerSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddPlayerPacket packet) {
      helper.writeUuid(buffer, packet.getUuid());
      helper.writeString(buffer, packet.getUsername());
      VarInts.writeLong(buffer, packet.getUniqueEntityId());
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      helper.writeString(buffer, packet.getPlatformChatId());
      helper.writeVector3f(buffer, packet.getPosition());
      helper.writeVector3f(buffer, packet.getMotion());
      helper.writeVector3f(buffer, packet.getRotation());
      helper.writeItem(buffer, packet.getHand());
      helper.writeEntityData(buffer, packet.getMetadata());
      AdventureSettingsSerializer_v291.INSTANCE.serialize(buffer, helper, packet.getAdventureSettings());
      List var10002 = packet.getEntityLinks();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeEntityLink);
      helper.writeString(buffer, packet.getDeviceId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddPlayerPacket packet) {
      packet.setUuid(helper.readUuid(buffer));
      packet.setUsername(helper.readString(buffer));
      packet.setUniqueEntityId(VarInts.readLong(buffer));
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setPlatformChatId(helper.readString(buffer));
      packet.setPosition(helper.readVector3f(buffer));
      packet.setMotion(helper.readVector3f(buffer));
      packet.setRotation(helper.readVector3f(buffer));
      packet.setHand(helper.readItem(buffer));
      helper.readEntityData(buffer, packet.getMetadata());
      AdventureSettingsSerializer_v291.INSTANCE.deserialize(buffer, helper, packet.getAdventureSettings());
      List var10002 = packet.getEntityLinks();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readEntityLink);
      packet.setDeviceId(helper.readString(buffer));
   }

   protected AddPlayerSerializer_v291() {
   }
}
