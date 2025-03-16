package com.nukkitx.protocol.bedrock.codec.v544.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v354.serializer.ClientboundMapItemDataSerializer_v354;
import com.nukkitx.protocol.bedrock.data.MapDecoration;
import com.nukkitx.protocol.bedrock.data.MapTrackedObject;
import com.nukkitx.protocol.bedrock.packet.ClientboundMapItemDataPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.longs.LongList;
import java.util.List;

public class ClientboundMapItemDataSerializer_v544 extends ClientboundMapItemDataSerializer_v354 {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
      VarInts.writeLong(buffer, packet.getUniqueMapId());
      int type = 0;
      int[] colors = packet.getColors();
      if (colors != null && colors.length > 0) {
         type |= 2;
      }

      List<MapDecoration> decorations = packet.getDecorations();
      List<MapTrackedObject> trackedObjects = packet.getTrackedObjects();
      if (!decorations.isEmpty() && !trackedObjects.isEmpty()) {
         type |= 4;
      }

      LongList trackedEntityIds = packet.getTrackedEntityIds();
      if (!trackedEntityIds.isEmpty()) {
         type |= 8;
      }

      VarInts.writeUnsignedInt(buffer, type);
      buffer.writeByte(packet.getDimensionId());
      buffer.writeBoolean(packet.isLocked());
      helper.writeBlockPosition(buffer, packet.getOrigin());
      if ((type & 8) != 0) {
         this.writeMapCreation(buffer, helper, packet);
      }

      if ((type & 14) != 0) {
         buffer.writeByte(packet.getScale());
      }

      if ((type & 4) != 0) {
         this.writeMapDecorations(buffer, helper, packet);
      }

      if ((type & 2) != 0) {
         this.writeTextureUpdate(buffer, helper, packet);
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
      packet.setUniqueMapId(VarInts.readLong(buffer));
      int type = VarInts.readUnsignedInt(buffer);
      packet.setDimensionId(buffer.readUnsignedByte());
      packet.setLocked(buffer.readBoolean());
      packet.setOrigin(helper.readBlockPosition(buffer));
      if ((type & 8) != 0) {
         this.readMapCreation(buffer, helper, packet);
      }

      if ((type & 14) != 0) {
         packet.setScale(buffer.readUnsignedByte());
      }

      if ((type & 4) != 0) {
         this.readMapDecorations(buffer, helper, packet);
      }

      if ((type & 2) != 0) {
         this.readTextureUpdate(buffer, helper, packet);
      }

   }
}
