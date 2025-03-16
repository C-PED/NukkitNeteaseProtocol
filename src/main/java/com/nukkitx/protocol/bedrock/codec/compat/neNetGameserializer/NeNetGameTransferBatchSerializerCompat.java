package com.nukkitx.protocol.bedrock.codec.compat.neNetGameserializer;

import com.neteasemc.protocol.netgame.NeteaseNetGameTransferBatchPacket;
import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.BedrockSession;
import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.GeyserTransferPacket;
import com.nukkitx.protocol.common.util.Zlib;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class NeNetGameTransferBatchSerializerCompat implements BedrockPacketSerializer<NeteaseNetGameTransferBatchPacket> {
   public static final NeNetGameTransferBatchSerializerCompat INSTANCE = new NeNetGameTransferBatchSerializerCompat();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NeteaseNetGameTransferBatchPacket packet) {
   }

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NeteaseNetGameTransferBatchPacket packet, BedrockSession session) {
      BedrockCodec codec = session.getMinecraftPacketCodec();
      ArrayList<Long> userIds = packet.getUserIds();
      ArrayList<BedrockPacket> packets = packet.getPackets();
      ByteBuf plainBuffer = ByteBufAllocator.DEFAULT.ioBuffer();
      ByteBuf packetBuffer = ByteBufAllocator.DEFAULT.ioBuffer();

      for(int i = 0; i < userIds.size(); ++i) {
         try {
            packetBuffer.clear();
            long userId = (Long)userIds.get(i);
            BedrockPacket p = (BedrockPacket)packets.get(i);
            BedrockSession userSession = session.getPlayerSession(userId);
            packetBuffer.writeIntLE((int)userId);
            if (p instanceof GeyserTransferPacket) {
               byte[] data = ((GeyserTransferPacket)p).getTransferData();
               packetBuffer.writeBytes(data, 0, data.length);
            } else {
               int id = codec.getId(p);
               int header = 0;
               header |= id & 1023;
               header |= 0;
               header |= 0;
               VarInts.writeUnsignedInt(packetBuffer, header);
               codec.tryEncode(codec.getHelper(), packetBuffer, p, userSession);
            }

            VarInts.writeUnsignedInt(plainBuffer, packetBuffer.writerIndex());
            plainBuffer.writeBytes(packetBuffer, packetBuffer.readableBytes());
         } catch (Exception e) {
            InternalLoggerFactory.getInstance(BedrockCodecHelper.class).error("Error occurred whilst encoding " + packet.getClass().getSimpleName(), e);
         }
      }

      try {
         Zlib.RAW.deflate(plainBuffer, buffer, 1);
      } catch (DataFormatException e) {
         e.printStackTrace();
      }

      packetBuffer.release();
      plainBuffer.release();
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NeteaseNetGameTransferBatchPacket packet) {
   }

   private NeNetGameTransferBatchSerializerCompat() {
   }
}
