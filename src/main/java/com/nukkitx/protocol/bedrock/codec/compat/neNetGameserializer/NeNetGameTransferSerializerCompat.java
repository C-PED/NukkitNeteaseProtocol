package com.nukkitx.protocol.bedrock.codec.compat.neNetGameserializer;

import com.neteasemc.protocol.netgame.NeteaseNetGameTransferPacket;
import com.nukkitx.protocol.bedrock.BedrockSession;
import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.GeyserTransferPacket;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.List;

public class NeNetGameTransferSerializerCompat implements BedrockPacketSerializer<NeteaseNetGameTransferPacket> {
   public static final NeNetGameTransferSerializerCompat INSTANCE = new NeNetGameTransferSerializerCompat();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NeteaseNetGameTransferPacket packet) {
   }

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NeteaseNetGameTransferPacket packet, BedrockSession session) {
      long userId = packet.getUserId();
      BedrockSession userSession = session.getPlayerSession(userId);
      if (userSession != null) {
         buffer.writeIntLE((int)packet.getUserId());
         BedrockPacket subPacket = packet.getSubPacket();
         if (subPacket instanceof GeyserTransferPacket) {
            byte[] data = ((GeyserTransferPacket)subPacket).getTransferData();
            buffer.writeBytes(data, 0, data.length);
         } else {
            userSession.getWrapperSerializer().serialize(buffer, userSession.getPacketCodec(), Collections.singletonList(subPacket), userSession.getCompressionLevel(), userSession);
         }

      }
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NeteaseNetGameTransferPacket packet) {
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NeteaseNetGameTransferPacket packet, BedrockSession session) {
      long userId = buffer.readUnsignedIntLE();
      BedrockSession userSession = session.getPlayerSession(userId);
      if (userSession != null) {
         packet.setUserId(userId);
         List<BedrockPacket> subPackets = new ObjectArrayList();
         userSession.getWrapperSerializer().deserialize(buffer, userSession.getPacketCodec(), subPackets, session);
         if (subPackets.size() == 1) {
            packet.setSubPacket((BedrockPacket)subPackets.get(0));
         }

         userSession.getBatchHandler().handle(userSession, buffer, subPackets);
      }
   }

   private NeNetGameTransferSerializerCompat() {
   }
}
