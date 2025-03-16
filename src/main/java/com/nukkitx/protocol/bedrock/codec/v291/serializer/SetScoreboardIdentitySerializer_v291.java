package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PlayerListPacket;
import com.nukkitx.protocol.bedrock.packet.SetScoreboardIdentityPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class SetScoreboardIdentitySerializer_v291 implements BedrockPacketSerializer<SetScoreboardIdentityPacket> {
   public static final SetScoreboardIdentitySerializer_v291 INSTANCE = new SetScoreboardIdentitySerializer_v291();

   @Override
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetScoreboardIdentityPacket packet) {
      SetScoreboardIdentityPacket.Action action = packet.getAction();
      buffer.writeByte(action.ordinal());
      helper.writeArray(buffer, packet.getEntries(), (buf, entry) -> {
         VarInts.writeLong(buffer, entry.getScoreboardId());
         if (action == SetScoreboardIdentityPacket.Action.ADD) {
            helper.writeUuid(buffer, entry.getUuid());
         }
      });
   }

   @Override
   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetScoreboardIdentityPacket packet) {
      SetScoreboardIdentityPacket.Action action = SetScoreboardIdentityPacket.Action.values()[buffer.readUnsignedByte()];
      packet.setAction(action);
      helper.readArray(buffer, packet.getEntries(), (Function)((buf) -> {
         long scoreboardId = VarInts.readLong(buffer);
         UUID uuid = null;
         if (action == SetScoreboardIdentityPacket.Action.ADD) {
            uuid = helper.readUuid(buffer);
         }

         return new SetScoreboardIdentityPacket.Entry(scoreboardId, uuid);
      }));
   }

   protected SetScoreboardIdentitySerializer_v291() {
   }
}
