package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ResourcePackClientResponsePacket;
import io.netty.buffer.ByteBuf;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.ToLongFunction;

public class ResourcePackClientResponseSerializer_v291 implements BedrockPacketSerializer<ResourcePackClientResponsePacket> {
   public static final ResourcePackClientResponseSerializer_v291 INSTANCE = new ResourcePackClientResponseSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackClientResponsePacket packet) {
      buffer.writeByte(packet.getStatus().ordinal());

      writeArrayShortLE(buffer, packet.getPackIds(), helper::writeString);
   }

   @Override
   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackClientResponsePacket packet) {
      ResourcePackClientResponsePacket.Status status = ResourcePackClientResponsePacket.Status.values()[buffer.readUnsignedByte()];
      packet.setStatus(status);

      readArrayShortLE(buffer, packet.getPackIds(), helper::readString);
   }

   protected <T> void readArrayShortLE(ByteBuf buffer, Collection<T> collection, Function<ByteBuf, T> function) {
      int length = buffer.readUnsignedShortLE();
      for (int i = 0; i < length; i++) {
         collection.add(function.apply(buffer));
      }
   }

   protected <T> void writeArrayShortLE(ByteBuf buffer, Collection<T> collection, BiConsumer<ByteBuf, T> consumer) {
      buffer.writeShortLE(collection.size());
      for (T t : collection) {
         consumer.accept(buffer, t);
      }
   }

   protected ResourcePackClientResponseSerializer_v291() {
   }
}
