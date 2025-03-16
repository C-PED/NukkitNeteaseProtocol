package com.neteasemc.protocol.custom.packet.customItemPacket;

import com.neteasemc.protocol.custom.GeyserPacketSerializer;
import com.nukkitx.network.VarInts;
import com.nukkitx.network.util.Preconditions;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CustomItemPacketSerializer implements GeyserPacketSerializer<CustomItemPacket> {
   public static final CustomItemPacketSerializer INSTANCE = new CustomItemPacketSerializer();

   private void writeString(ByteBuf buffer, String string) {
      Preconditions.checkNotNull(buffer, "buffer");
      Preconditions.checkNotNull(string, "string");
      this.writeByteArray(buffer, string.getBytes(StandardCharsets.UTF_8));
   }

   public void writeByteArray(ByteBuf buffer, byte[] bytes) {
      Preconditions.checkNotNull(buffer, "buffer");
      Preconditions.checkNotNull(bytes, "bytes");
      VarInts.writeUnsignedInt(buffer, bytes.length);
      buffer.writeBytes(bytes);
   }

   public String readString(ByteBuf buffer) {
      Preconditions.checkNotNull(buffer, "buffer");
      return new String(this.readByteArray(buffer), StandardCharsets.UTF_8);
   }

   public byte[] readByteArray(ByteBuf buffer) {
      Preconditions.checkNotNull(buffer, "buffer");
      int length = VarInts.readUnsignedInt(buffer);
      Preconditions.checkArgument(buffer.isReadable(length), "Tried to read %s bytes but only has %s readable", length, buffer.readableBytes());
      byte[] bytes = new byte[length];
      buffer.readBytes(bytes);
      return bytes;
   }

   public void serialize(ByteBuf buffer, CustomItemPacket packet) {
      Map<String, String> items = packet.getItems();
      int itemSize = items.size();
      VarInts.writeUnsignedInt(buffer, itemSize);

      for(String key : items.keySet()) {
         this.writeString(buffer, key);
         this.writeString(buffer, (String)items.get(key));
      }

   }

   public void deserialize(ByteBuf buffer, CustomItemPacket packet) {
      int itemSize = VarInts.readUnsignedInt(buffer);
      Map<String, String> items = new HashMap();

      for(int i = 0; i < itemSize; ++i) {
         String key = this.readString(buffer);
         String value = this.readString(buffer);
         items.put(key, value);
      }

      packet.setItems(items);
   }
}
