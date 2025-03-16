package com.nukkitx.protocol.bedrock.codec.v390.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PlayerListPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class PlayerListSerializer_v390 implements BedrockPacketSerializer<PlayerListPacket> {
   public static final PlayerListSerializer_v390 INSTANCE = new PlayerListSerializer_v390();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
      buffer.writeByte(packet.getAction().ordinal());
      VarInts.writeUnsignedInt(buffer, packet.getEntries().size());
      if (packet.getAction() == PlayerListPacket.Action.ADD) {
         for(PlayerListPacket.Entry entry : packet.getEntries()) {
            this.writeEntryBase(buffer, helper, entry);
         }

         for(PlayerListPacket.Entry entry : packet.getEntries()) {
            buffer.writeBoolean(entry.isTrustedSkin());
         }
      } else {
         for(PlayerListPacket.Entry entry : packet.getEntries()) {
            helper.writeUuid(buffer, entry.getUuid());
         }
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
      PlayerListPacket.Action action = PlayerListPacket.Action.values()[buffer.readUnsignedByte()];
      packet.setAction(action);
      int length = VarInts.readUnsignedInt(buffer);
      if (action == PlayerListPacket.Action.ADD) {
         for(int i = 0; i < length; ++i) {
            packet.getEntries().add(this.readEntryBase(buffer, helper));
         }

         for(int i = 0; i < length && buffer.isReadable(); ++i) {
            ((PlayerListPacket.Entry)packet.getEntries().get(i)).setTrustedSkin(buffer.readBoolean());
         }
      } else {
         for(int i = 0; i < length; ++i) {
            packet.getEntries().add(new PlayerListPacket.Entry(helper.readUuid(buffer)));
         }
      }

   }

   protected void writeEntryBase(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket.Entry entry) {
      helper.writeUuid(buffer, entry.getUuid());
      VarInts.writeLong(buffer, entry.getEntityId());
      helper.writeString(buffer, entry.getName());
      helper.writeString(buffer, entry.getXuid());
      helper.writeString(buffer, entry.getPlatformChatId());
      buffer.writeIntLE(entry.getBuildPlatform());
      helper.writeSkin(buffer, entry.getSkin());
      buffer.writeBoolean(entry.isTeacher());
      buffer.writeBoolean(entry.isHost());
   }

   protected PlayerListPacket.Entry readEntryBase(ByteBuf buffer, BedrockCodecHelper helper) {
      PlayerListPacket.Entry entry = new PlayerListPacket.Entry(helper.readUuid(buffer));
      entry.setEntityId(VarInts.readLong(buffer));
      entry.setName(helper.readString(buffer));
      entry.setXuid(helper.readString(buffer));
      entry.setPlatformChatId(helper.readString(buffer));
      entry.setBuildPlatform(buffer.readIntLE());
      entry.setSkin(helper.readSkin(buffer));
      entry.setTeacher(buffer.readBoolean());
      entry.setHost(buffer.readBoolean());
      return entry;
   }

   protected PlayerListSerializer_v390() {
   }
}
