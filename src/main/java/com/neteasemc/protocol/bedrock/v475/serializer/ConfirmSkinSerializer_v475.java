package com.neteasemc.protocol.bedrock.v475.serializer;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ConfirmSkinPacket;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;

public class ConfirmSkinSerializer_v475 implements BedrockPacketSerializer<ConfirmSkinPacket> {
   public static final ConfirmSkinSerializer_v475 INSTANCE = new ConfirmSkinSerializer_v475();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ConfirmSkinPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getEntries().size());

      for(ConfirmSkinPacket.SkinEntry entry : packet.getEntries()) {
         buffer.writeBoolean(entry.isValid());
         helper.writeUuid(buffer, entry.getUuid());
         helper.writeByteArray(buffer, entry.getSkinBytes());
      }

      for(ConfirmSkinPacket.SkinEntry entry : packet.getEntries()) {
         helper.writeString(buffer, entry.getUidStr());
      }

      for(ConfirmSkinPacket.SkinEntry entry : packet.getEntries()) {
         helper.writeString(buffer, entry.getGeoStr());
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ConfirmSkinPacket packet) {
      int size = VarInts.readUnsignedInt(buffer);
      List<ConfirmSkinPacket.SkinEntry> entries = new ArrayList();

      for(int i = 0; i < size; ++i) {
         ConfirmSkinPacket.SkinEntry entry = new ConfirmSkinPacket.SkinEntry();
         entry.setValid(buffer.readBoolean());
         entry.setUuid(helper.readUuid(buffer));
         entry.setSkinBytes(helper.readByteArray(buffer));
         entries.add(entry);
      }

      if (buffer.isReadable()) {
         for(int i = 0; i < size; ++i) {
            ((ConfirmSkinPacket.SkinEntry)entries.get(i)).setUidStr(helper.readString(buffer));
         }
      }

      if (buffer.isReadable()) {
         for(int i = 0; i < size; ++i) {
            ((ConfirmSkinPacket.SkinEntry)entries.get(i)).setGeoStr(helper.readString(buffer));
         }
      }

   }
}
