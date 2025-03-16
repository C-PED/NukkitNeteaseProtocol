package com.nukkitx.protocol.bedrock.codec.v361.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ClientCacheMissResponsePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;

public class ClientCacheMissResponseSerializer_v361 implements BedrockPacketSerializer<ClientCacheMissResponsePacket> {
   public static final ClientCacheMissResponseSerializer_v361 INSTANCE = new ClientCacheMissResponseSerializer_v361();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCacheMissResponsePacket packet) {
      Long2ObjectMap<ByteBuf> blobs = packet.getBlobs();
      VarInts.writeUnsignedInt(buffer, blobs.size());
      ObjectIterator var5 = blobs.long2ObjectEntrySet().iterator();

      while(var5.hasNext()) {
         Long2ObjectMap.Entry<ByteBuf> entry = (Long2ObjectMap.Entry)var5.next();
         buffer.writeLongLE(entry.getLongKey());
         helper.writeByteBuf(buffer, (ByteBuf)entry.getValue());
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientCacheMissResponsePacket packet) {
      Long2ObjectMap<ByteBuf> blobs = packet.getBlobs();
      int length = VarInts.readUnsignedInt(buffer);

      for(int i = 0; i < length; ++i) {
         long id = buffer.readLongLE();
         ByteBuf blob = helper.readByteBuf(buffer);
         blobs.put(id, blob);
      }

   }

   protected ClientCacheMissResponseSerializer_v361() {
   }
}
