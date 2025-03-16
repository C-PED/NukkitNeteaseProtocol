//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.neteasemc.protocol.custom;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.common.util.Zlib;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;
import javax.annotation.Nonnegative;
import org.lanternpowered.lmbda.LambdaFactory;
import org.lanternpowered.lmbda.MethodHandlesExtensions;

public final class GeyserBaseCode {
   private static final InternalLogger log = InternalLoggerFactory.getInstance(GeyserBaseCode.class);
   private final GeyerPacketDefinition<? extends GeyserBasePacket>[] packetsById;
   private final Map<Class<? extends GeyserBasePacket>, GeyerPacketDefinition<? extends GeyserBasePacket>> packetsByClass;
   private final Zlib zlib;
   private final int COMPRESSED_LEVEL;
   private final int PACKET_MAX_SIZE_BY_BUNGEECORD;

   public static Builder builder() {
      return new Builder();
   }

   public GeyserBasePacket tryDecode(ByteBuf compressed, int id) throws GeyserPacketSerializeException {
      GeyerPacketDefinition<? extends GeyserBasePacket> definition = this.getPacketDefinition(id);
      if (definition == null) {
         log.error("Geyser packet::tryDecode  definition == null");
         return null;
      } else {
         GeyserBasePacket packet = (GeyserBasePacket)definition.getFactory().get();
         GeyserPacketSerializer<GeyserBasePacket> serializer = (GeyserPacketSerializer<GeyserBasePacket>) definition.getSerializer();
         ByteBuf decompressed = ByteBufAllocator.DEFAULT.ioBuffer();

         try {
            this.zlib.inflate(compressed, 12582912);
            if (decompressed.isReadable()) {
               int packetLen = VarInts.readUnsignedInt(decompressed);
               ByteBuf packetBuf = decompressed.readSlice(packetLen);
               serializer.deserialize(packetBuf, packet);
            }

            if (decompressed.isReadable()) {
               log.error(packet.getClass().getSimpleName() + " still has " + decompressed.readableBytes() + " bytes to read!");
            }
         } catch (Exception var12) {
            throw new GeyserPacketSerializeException("Error whilst deserializing " + packet, var12);
         } finally {
            decompressed.release();
         }

         return packet;
      }
   }

   public boolean tryEncode(ByteBuf buf, GeyserBasePacket packet) throws GeyserPacketSerializeException {
      ByteBuf uncompressed = ByteBufAllocator.DEFAULT.ioBuffer();
      ByteBuf packetBuf = ByteBufAllocator.DEFAULT.ioBuffer();
      boolean res = true;

      try {
         GeyerPacketDefinition definition = this.getPacketDefinition(packet.getClass());
         VarInts.writeUnsignedInt(buf, definition.getId());
         GeyserPacketSerializer<GeyserBasePacket> serializer = definition.getSerializer();
         serializer.serialize(packetBuf, packet);
         VarInts.writeUnsignedInt(uncompressed, packetBuf.readableBytes());
         uncompressed.writeBytes(packetBuf);
         this.zlib.deflate(uncompressed, buf, 3);
         if (buf.readableBytes() >= 32767) {
            log.warn("geyser tring send large packet to spigot, it will kicked by Bungeecord!!!! :" + buf.readableBytes() + " packet id:" + definition.getId());
            res = false;
         }
      } catch (Exception var11) {
         throw new GeyserPacketSerializeException("Error whilst serializing " + packet, var11);
      } finally {
         uncompressed.release();
         packetBuf.release();
         ReferenceCountUtil.release(packet);
      }

      return res;
   }

   public static <T> T checkNotNull(T reference, String errorMessage) {
      if (reference == null) {
         throw new NullPointerException(errorMessage);
      } else {
         return reference;
      }
   }

   public static void checkArgument(boolean expression, String errorMessage) {
      if (!expression) {
         throw new IllegalArgumentException(errorMessage);
      }
   }

   public <T extends GeyserBasePacket> GeyerPacketDefinition<T> getPacketDefinition(Class<T> packet) {
      checkNotNull(packet, "packet");
      return (GeyerPacketDefinition)this.packetsByClass.get(packet);
   }

   public GeyerPacketDefinition<? extends GeyserBasePacket> getPacketDefinition(int id) {
      return id < this.packetsById.length ? this.packetsById[id] : null;
   }

   public int getId(GeyserBasePacket packet) {
      return this.getId(packet.getClass());
   }

   public int getId(Class<? extends GeyserBasePacket> clazz) {
      GeyerPacketDefinition<?> definition = this.getPacketDefinition(clazz);
      if (definition == null) {
         throw new IllegalArgumentException("Packet ID for " + clazz.getName() + " does not exist.");
      } else {
         return definition.getId();
      }
   }

   public Builder toBuilder() {
      Builder builder = new Builder();
      builder.packets.putAll(this.packetsByClass);
      return builder;
   }

   private GeyserBaseCode(GeyerPacketDefinition<? extends GeyserBasePacket>[] packetsById, Map<Class<? extends GeyserBasePacket>, GeyerPacketDefinition<? extends GeyserBasePacket>> packetsByClass) {
      this.zlib = Zlib.DEFAULT;
      this.COMPRESSED_LEVEL = 3;
      this.PACKET_MAX_SIZE_BY_BUNGEECORD = 32767;
      this.packetsById = packetsById;
      this.packetsByClass = packetsByClass;
   }

   public GeyerPacketDefinition<? extends GeyserBasePacket>[] getPacketsById() {
      return this.packetsById;
   }

   public Map<Class<? extends GeyserBasePacket>, GeyerPacketDefinition<? extends GeyserBasePacket>> getPacketsByClass() {
      return this.packetsByClass;
   }

   public static class Builder {
      private final Map<Class<? extends GeyserBasePacket>, GeyerPacketDefinition<? extends GeyserBasePacket>> packets;

      public <T extends GeyserBasePacket> Builder registerPacket(Class<T> packetClass, GeyserPacketSerializer<T> serializer, @Nonnegative int id) {
         GeyserBaseCode.checkArgument(id >= 0, "id cannot be negative");
         GeyserBaseCode.checkArgument(!this.packets.containsKey(packetClass), "Packet class already registered");

         Supplier factory;
         try {
            MethodHandles.Lookup lookup = MethodHandlesExtensions.privateLookupIn(packetClass, MethodHandles.lookup());
            MethodHandle handle = lookup.findConstructor(packetClass, MethodType.methodType(Void.TYPE));
            factory = LambdaFactory.createSupplier(handle);
         } catch (IllegalAccessException | NoSuchMethodException var7) {
            throw new IllegalArgumentException("Unable to find suitable constructor for packet factory", var7);
         }

         GeyerPacketDefinition<T> info = new GeyerPacketDefinition(id, factory, serializer);
         this.packets.put(packetClass, info);
         return this;
      }

      public void deregisterPacket(Class<? extends GeyserBasePacket> packetClass) {
         GeyserBaseCode.checkNotNull(packetClass, "packetClass");
         GeyerPacketDefinition<? extends GeyserBasePacket> info = (GeyerPacketDefinition)this.packets.remove(packetClass);
      }

      public GeyserBaseCode build() {
         int largestId = -1;
         Iterator var2 = this.packets.values().iterator();

         while(var2.hasNext()) {
            GeyerPacketDefinition<? extends GeyserBasePacket> info = (GeyerPacketDefinition)var2.next();
            if (info.getId() > largestId) {
               largestId = info.getId();
            }
         }

         GeyserBaseCode.checkArgument(largestId > -1, "Must have at least one packet registered");
         GeyerPacketDefinition<? extends GeyserBasePacket>[] packetsById = new GeyerPacketDefinition[largestId + 1];

         GeyerPacketDefinition info;
         for(Iterator var6 = this.packets.values().iterator(); var6.hasNext(); packetsById[info.getId()] = info) {
            info = (GeyerPacketDefinition)var6.next();
         }

         return new GeyserBaseCode(packetsById, this.packets);
      }

      private Builder() {
         this.packets = new IdentityHashMap();
      }
   }
}
