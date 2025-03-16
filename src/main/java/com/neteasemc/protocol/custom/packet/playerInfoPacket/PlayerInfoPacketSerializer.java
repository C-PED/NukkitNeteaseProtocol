package com.neteasemc.protocol.custom.packet.playerInfoPacket;

import com.neteasemc.protocol.custom.GeyserPacketSerializer;
import com.nukkitx.protocol.common.util.CommonWriteUtil;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class PlayerInfoPacketSerializer implements GeyserPacketSerializer<PlayerInfoPacket> {
   public static final PlayerInfoPacketSerializer INSTANCE = new PlayerInfoPacketSerializer();

   public void serialize(ByteBuf buffer, PlayerInfoPacket packet) {
      CommonWriteUtil.writeString(buffer, packet.getGameId());
      CommonWriteUtil.writeString(buffer, packet.getGameKey());
      buffer.writeInt(packet.getReviewStage());
      CommonWriteUtil.writeString(buffer, packet.getGasServerUrl());
      CommonWriteUtil.writeString(buffer, packet.getWebServerUrl());
      buffer.writeIntLE((int)packet.getProxyUid());
      CommonWriteUtil.writeUuid(buffer, packet.getUuid());
   }

   public void deserialize(ByteBuf buffer, PlayerInfoPacket packet) {
      String gameId = CommonWriteUtil.readString(buffer);
      String gameKey = CommonWriteUtil.readString(buffer);
      int reviewStage = buffer.readInt();
      String gasServerUrl = CommonWriteUtil.readString(buffer);
      String webServerUrl = CommonWriteUtil.readString(buffer);
      long proxyUid = buffer.readUnsignedIntLE();
      UUID uuid = CommonWriteUtil.readUuid(buffer);
      packet.setReviewStage(reviewStage);
      packet.setGameId(gameId);
      packet.setGameKey(gameKey);
      packet.setGasServerUrl(gasServerUrl);
      packet.setWebServerUrl(webServerUrl);
      packet.setProxyUid(proxyUid);
      packet.setUuid(uuid);
   }
}
