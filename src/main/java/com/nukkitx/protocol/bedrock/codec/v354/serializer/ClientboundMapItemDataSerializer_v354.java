package com.nukkitx.protocol.bedrock.codec.v354.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.MapDecoration;
import com.nukkitx.protocol.bedrock.data.MapTrackedObject;
import com.nukkitx.protocol.bedrock.packet.ClientboundMapItemDataPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.longs.LongList;
import it.unimi.dsi.fastutil.longs.LongListIterator;
import java.util.List;

public class ClientboundMapItemDataSerializer_v354 implements BedrockPacketSerializer<ClientboundMapItemDataPacket> {
   public static final ClientboundMapItemDataSerializer_v354 INSTANCE = new ClientboundMapItemDataSerializer_v354();
   protected static final int FLAG_TEXTURE_UPDATE = 2;
   protected static final int FLAG_DECORATION_UPDATE = 4;
   protected static final int FLAG_MAP_CREATION = 8;
   protected static final int FLAG_ALL = 14;

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
      if ((type & 8) != 0) {
         this.readMapCreation(buffer, helper, packet);
      }

      if ((type & 14) != 0) {
         packet.setScale(buffer.readUnsignedByte());
      }

      if ((type & 4) != 0) {
         this.writeMapDecorations(buffer, helper, packet);
      }

      if ((type & 2) != 0) {
         this.readTextureUpdate(buffer, helper, packet);
      }

   }

   protected void writeMapCreation(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getTrackedEntityIds().size());
      LongListIterator var4 = packet.getTrackedEntityIds().iterator();

      while(var4.hasNext()) {
         long trackedEntityId = (Long)var4.next();
         VarInts.writeLong(buffer, trackedEntityId);
      }

   }

   protected void readMapCreation(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
      LongList trackedEntityIds = packet.getTrackedEntityIds();
      int length = VarInts.readUnsignedInt(buffer);

      for(int i = 0; i < length; ++i) {
         trackedEntityIds.add(VarInts.readLong(buffer));
      }

   }

   protected void writeMapDecorations(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
      List<MapDecoration> decorations = packet.getDecorations();
      List<MapTrackedObject> trackedObjects = packet.getTrackedObjects();
      VarInts.writeUnsignedInt(buffer, trackedObjects.size());

      for(MapTrackedObject object : trackedObjects) {
         switch (object.getType()) {
            case BLOCK:
               buffer.writeIntLE(object.getType().ordinal());
               helper.writeBlockPosition(buffer, object.getPosition());
               break;
            case ENTITY:
               buffer.writeIntLE(object.getType().ordinal());
               VarInts.writeLong(buffer, object.getEntityId());
         }
      }

      VarInts.writeUnsignedInt(buffer, decorations.size());

      for(MapDecoration decoration : decorations) {
         buffer.writeByte(decoration.getImage());
         buffer.writeByte(decoration.getRotation());
         buffer.writeByte(decoration.getXOffset());
         buffer.writeByte(decoration.getYOffset());
         helper.writeString(buffer, decoration.getLabel());
         VarInts.writeUnsignedInt(buffer, decoration.getColor());
      }

   }

   protected void readMapDecorations(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
      List<MapTrackedObject> trackedObjects = packet.getTrackedObjects();
      int length = VarInts.readUnsignedInt(buffer);

      for(int i = 0; i < length; ++i) {
         MapTrackedObject.Type objectType = MapTrackedObject.Type.values()[buffer.readIntLE()];
         switch (objectType) {
            case BLOCK:
               trackedObjects.add(new MapTrackedObject(helper.readBlockPosition(buffer)));
               break;
            case ENTITY:
               trackedObjects.add(new MapTrackedObject(VarInts.readLong(buffer)));
         }
      }

      List<MapDecoration> decorations = packet.getDecorations();
      length = VarInts.readUnsignedInt(buffer);

      for(int i = 0; i < length; ++i) {
         int image = buffer.readUnsignedByte();
         int rotation = buffer.readUnsignedByte();
         int xOffset = buffer.readUnsignedByte();
         int yOffset = buffer.readUnsignedByte();
         String label = helper.readString(buffer);
         int color = VarInts.readUnsignedInt(buffer);
         decorations.add(new MapDecoration(image, rotation, xOffset, yOffset, label, color));
      }

   }

   protected void writeTextureUpdate(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
      VarInts.writeInt(buffer, packet.getWidth());
      VarInts.writeInt(buffer, packet.getHeight());
      VarInts.writeInt(buffer, packet.getXOffset());
      VarInts.writeInt(buffer, packet.getYOffset());
      int length = packet.getColors().length;
      VarInts.writeUnsignedInt(buffer, length);

      for(int color : packet.getColors()) {
         VarInts.writeUnsignedInt(buffer, color);
      }

   }

   protected void readTextureUpdate(ByteBuf buffer, BedrockCodecHelper helper, ClientboundMapItemDataPacket packet) {
      packet.setWidth(VarInts.readInt(buffer));
      packet.setHeight(VarInts.readInt(buffer));
      packet.setXOffset(VarInts.readInt(buffer));
      packet.setYOffset(VarInts.readInt(buffer));
      int length = VarInts.readUnsignedInt(buffer);
      int[] colors = new int[length];

      for(int i = 0; i < length; ++i) {
         colors[i] = VarInts.readUnsignedInt(buffer);
      }

      packet.setColors(colors);
   }

   protected ClientboundMapItemDataSerializer_v354() {
   }
}
