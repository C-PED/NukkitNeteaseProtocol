package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.BossEventPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class BossEventSerializer_v291 implements BedrockPacketSerializer<BossEventPacket> {
   public static final BossEventSerializer_v291 INSTANCE = new BossEventSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
      VarInts.writeLong(buffer, packet.getBossUniqueEntityId());
      VarInts.writeUnsignedInt(buffer, packet.getAction().ordinal());
      this.serializeAction(buffer, helper, packet);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
      packet.setBossUniqueEntityId(VarInts.readLong(buffer));
      packet.setAction(BossEventPacket.Action.values()[VarInts.readUnsignedInt(buffer)]);
      this.deserializeAction(buffer, helper, packet);
   }

   protected void serializeAction(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
      switch (packet.getAction()) {
         case REGISTER_PLAYER:
         case UNREGISTER_PLAYER:
            VarInts.writeLong(buffer, packet.getPlayerUniqueEntityId());
            break;
         case CREATE:
            helper.writeString(buffer, packet.getTitle());
            buffer.writeFloatLE(packet.getHealthPercentage());
         case UPDATE_PROPERTIES:
            buffer.writeShortLE(packet.getDarkenSky());
         case UPDATE_STYLE:
            VarInts.writeUnsignedInt(buffer, packet.getColor());
            VarInts.writeUnsignedInt(buffer, packet.getOverlay());
            break;
         case UPDATE_PERCENTAGE:
            buffer.writeFloatLE(packet.getHealthPercentage());
            break;
         case UPDATE_NAME:
            helper.writeString(buffer, packet.getTitle());
         case REMOVE:
            break;
         default:
            throw new RuntimeException("BossEvent transactionType was unknown!");
      }

   }

   protected void deserializeAction(ByteBuf buffer, BedrockCodecHelper helper, BossEventPacket packet) {
      switch (packet.getAction()) {
         case REGISTER_PLAYER:
         case UNREGISTER_PLAYER:
            packet.setPlayerUniqueEntityId(VarInts.readLong(buffer));
            break;
         case CREATE:
            packet.setTitle(helper.readString(buffer));
            packet.setHealthPercentage(buffer.readFloatLE());
         case UPDATE_PROPERTIES:
            packet.setDarkenSky(buffer.readUnsignedShortLE());
         case UPDATE_STYLE:
            packet.setColor(VarInts.readUnsignedInt(buffer));
            packet.setOverlay(VarInts.readUnsignedInt(buffer));
            break;
         case UPDATE_PERCENTAGE:
            packet.setHealthPercentage(buffer.readFloatLE());
            break;
         case UPDATE_NAME:
            packet.setTitle(helper.readString(buffer));
      }

   }

   protected BossEventSerializer_v291() {
   }
}
