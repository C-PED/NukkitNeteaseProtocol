package com.nukkitx.protocol.bedrock.codec.v534.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v503.serializer.AddPlayerSerializer_v503;
import com.nukkitx.protocol.bedrock.packet.AddPlayerPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class AddPlayerSerializer_v534 extends AddPlayerSerializer_v503 {
   public static final AddPlayerSerializer_v534 INSTANCE = new AddPlayerSerializer_v534();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddPlayerPacket packet) {
      helper.writeUuid(buffer, packet.getUuid());
      helper.writeString(buffer, packet.getUsername());
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      helper.writeString(buffer, packet.getPlatformChatId());
      helper.writeVector3f(buffer, packet.getPosition());
      helper.writeVector3f(buffer, packet.getMotion());
      helper.writeVector3f(buffer, packet.getRotation());
      helper.writeItem(buffer, packet.getHand());
      VarInts.writeInt(buffer, packet.getGameType().ordinal());
      helper.writeEntityData(buffer, packet.getMetadata());
      helper.writePlayerAbilities(buffer, packet);
      List var10002 = packet.getEntityLinks();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeEntityLink);
      helper.writeString(buffer, packet.getDeviceId());
      buffer.writeIntLE(packet.getBuildPlatform());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddPlayerPacket packet) {
      packet.setUuid(helper.readUuid(buffer));
      packet.setUsername(helper.readString(buffer));
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setPlatformChatId(helper.readString(buffer));
      packet.setPosition(helper.readVector3f(buffer));
      packet.setMotion(helper.readVector3f(buffer));
      packet.setRotation(helper.readVector3f(buffer));
      packet.setHand(helper.readItem(buffer));
      packet.setGameType(VALUES[VarInts.readInt(buffer)]);
      helper.readEntityData(buffer, packet.getMetadata());
      helper.readPlayerAbilities(buffer, packet);
      List var10002 = packet.getEntityLinks();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readEntityLink);
      packet.setDeviceId(helper.readString(buffer));
      packet.setBuildPlatform(buffer.readIntLE());
   }

   protected AddPlayerSerializer_v534() {
   }
}
