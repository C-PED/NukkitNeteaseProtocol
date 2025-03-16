package com.nukkitx.protocol.bedrock.codec;

import com.nukkitx.protocol.bedrock.BedrockSession;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.UnknownPacket;
import com.nukkitx.protocol.common.util.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.lanternpowered.lmbda.LambdaFactory;
import org.lanternpowered.lmbda.MethodHandlesExtensions;

public final class BedrockCodec {
   private static final InternalLogger log = InternalLoggerFactory.getInstance(BedrockCodec.class);
   private final int protocolVersion;
   private final String minecraftVersion;
   private final BedrockPacketDefinition<? extends BedrockPacket>[] packetsById;
   private final Map<Class<? extends BedrockPacket>, BedrockPacketDefinition<? extends BedrockPacket>> packetsByClass;
   private final Supplier<BedrockCodecHelper> helperFactory;
   private final BedrockCodecHelper helper;
   private final int raknetProtocolVersion;

   public static Builder builder() {
      return new Builder();
   }

   public BedrockPacket tryDecode(BedrockCodecHelper helper, ByteBuf buf, int id, PacketRecipient recipient) throws PacketSerializeException {
      return this.tryDecode(helper, buf, id, recipient,null);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public BedrockPacket tryDecode(BedrockCodecHelper helper, ByteBuf buf, int id, PacketRecipient recipient, BedrockSession session) throws PacketSerializeException {
      BedrockPacketDefinition<? extends BedrockPacket> definition = this.getPacketDefinition(id);
      if (definition != null && recipient != null && definition.getRecipient() != PacketRecipient.BOTH && definition.getRecipient() != recipient) {
      }

      BedrockPacket packet;
      BedrockPacketSerializer<BedrockPacket> serializer;
      if (definition == null) {
         UnknownPacket unknownPacket = new UnknownPacket();
         unknownPacket.setPacketId(id);
         packet = unknownPacket;
         serializer = (BedrockPacketSerializer) unknownPacket;
      } else {
         packet = definition.getFactory().get();
         serializer = (BedrockPacketSerializer) definition.getSerializer();
      }

      try {
         serializer.deserialize(buf, helper, packet);
      } catch (Exception e) {
         throw new PacketSerializeException("Error whilst deserializing " + packet, e);
      }

      if (log.isDebugEnabled() && buf.isReadable()) {
         log.debug(packet.getClass().getSimpleName() + " still has " + buf.readableBytes() + " bytes to read!");
      }

      return packet;
   }

   @SuppressWarnings("unchecked")
   public <T extends BedrockPacket> void tryEncode(BedrockCodecHelper helper, ByteBuf buf, T packet, BedrockSession session) throws PacketSerializeException {
      try {
         BedrockPacketSerializer<T> serializer;
         if (packet instanceof UnknownPacket) {
            serializer = (BedrockPacketSerializer)packet;
         } else {
            BedrockPacketDefinition<T> definition = this.getPacketDefinition(packet.getClass());
            serializer = definition.getSerializer();
         }

         serializer.serialize(buf, helper, packet, session);
      } catch (Exception e) {
         throw new PacketSerializeException("Error whilst serializing " + packet, e);
      }
   }

   @SuppressWarnings("unchecked")
   public <T extends BedrockPacket> BedrockPacketDefinition<T> getPacketDefinition(Class<? extends BedrockPacket> packet) {
      Preconditions.checkNotNull(packet, "packet");
      return (BedrockPacketDefinition)this.packetsByClass.get(packet);
   }

   public BedrockPacketDefinition<? extends BedrockPacket> getPacketDefinition(int id) {
      return id >= 0 && id < this.packetsById.length ? this.packetsById[id] : null;
   }

   public int getId(BedrockPacket packet) {
      if (packet instanceof UnknownPacket) {
         return ((UnknownPacket)packet).getPacketId();
      } else {
         Class<? extends BedrockPacket> clazz = packet.getClass();
         BedrockPacketDefinition<?> definition = this.getPacketDefinition(clazz);
         if (definition == null) {
            throw new IllegalArgumentException("Packet ID for " + clazz.getName() + " does not exist.");
         } else {
            return definition.getId();
         }
      }
   }

   public BedrockCodecHelper createHelper() {
      return (BedrockCodecHelper)this.helperFactory.get();
   }

   public Builder toBuilder() {
      Builder builder = new Builder();
      builder.packets.putAll(this.packetsByClass);
      builder.protocolVersion = this.protocolVersion;
      builder.raknetProtocolVersion = this.raknetProtocolVersion;
      builder.minecraftVersion = this.minecraftVersion;
      builder.helperFactory = this.helperFactory;
      return builder;
   }

   private BedrockCodec(int protocolVersion, String minecraftVersion, BedrockPacketDefinition<? extends BedrockPacket>[] packetsById, Map<Class<? extends BedrockPacket>, BedrockPacketDefinition<? extends BedrockPacket>> packetsByClass, Supplier<BedrockCodecHelper> helperFactory, BedrockCodecHelper helper, int raknetProtocolVersion) {
      this.protocolVersion = protocolVersion;
      this.minecraftVersion = minecraftVersion;
      this.packetsById = packetsById;
      this.packetsByClass = packetsByClass;
      this.helperFactory = helperFactory;
      this.helper = helper;
      this.raknetProtocolVersion = raknetProtocolVersion;
   }

   public int getProtocolVersion() {
      return this.protocolVersion;
   }

   public String getMinecraftVersion() {
      return this.minecraftVersion;
   }

   public BedrockCodecHelper getHelper() {
      return this.helper;
   }

   public int getRaknetProtocolVersion() {
      return this.raknetProtocolVersion;
   }

   public static class Builder {
      private final Map<Class<? extends BedrockPacket>, BedrockPacketDefinition<? extends BedrockPacket>> packets;
      private int protocolVersion;
      private int raknetProtocolVersion;
      private String minecraftVersion;
      private Supplier<BedrockCodecHelper> helperFactory;

      public <T extends BedrockPacket> Builder registerPacket(Class<T> packetClass, BedrockPacketSerializer<T> serializer, @NonNegative int id, PacketRecipient recipient) {
         Preconditions.checkArgument(id >= 0, "id cannot be negative");
         Preconditions.checkArgument(!this.packets.containsKey(packetClass), "Packet class already registered");

         Supplier<T> factory;
         try {
            MethodHandles.Lookup lookup = MethodHandlesExtensions.privateLookupIn(packetClass, MethodHandles.lookup());
            MethodHandle handle = lookup.findConstructor(packetClass, MethodType.methodType(Void.TYPE));
            factory = LambdaFactory.createSupplier(handle);
         } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new IllegalArgumentException("Unable to find suitable constructor for packet factory", e);
         }

         BedrockPacketDefinition<T> info = new BedrockPacketDefinition<T>(id, factory, serializer, recipient);
         this.packets.put(packetClass, info);
         return this;
      }

      public <T extends BedrockPacket> Builder registerPacket(Supplier<T> factory, BedrockPacketSerializer<T> serializer, @NonNegative int id, PacketRecipient recipient) {
         Class<? extends BedrockPacket> packetClass = ((BedrockPacket)factory.get()).getClass();
         Preconditions.checkArgument(id >= 0, "id cannot be negative");
         Preconditions.checkArgument(!this.packets.containsKey(packetClass), "Packet class already registered");
         BedrockPacketDefinition<T> info = new BedrockPacketDefinition<T>(id, factory, serializer, recipient);
         this.packets.put(packetClass, info);
         return this;
      }

      public <T extends BedrockPacket> Builder updateSerializer(Class<T> packetClass, BedrockPacketSerializer<T> serializer) {
         BedrockPacketDefinition<T> info = (BedrockPacketDefinition)this.packets.get(packetClass);
         Preconditions.checkArgument(info != null, "Packet does not exist");
         BedrockPacketDefinition<T> updatedInfo = new BedrockPacketDefinition<T>(info.getId(), info.getFactory(), serializer, info.getRecipient());
         this.packets.replace(packetClass, info, updatedInfo);
         return this;
      }

      public Builder retainPackets(Class<? extends BedrockPacket>... packets) {
         this.packets.keySet().retainAll(Arrays.asList(packets));
         return this;
      }

      public Builder deregisterPacket(Class<? extends BedrockPacket> packetClass) {
         Preconditions.checkNotNull(packetClass, "packetClass");
         BedrockPacketDefinition<? extends BedrockPacket> info = (BedrockPacketDefinition)this.packets.remove(packetClass);
         return this;
      }

      public Builder protocolVersion(@NonNegative int protocolVersion) {
         Preconditions.checkArgument(protocolVersion >= 0, "protocolVersion cannot be negative");
         this.protocolVersion = protocolVersion;
         return this;
      }

      public Builder raknetProtocolVersion(@NonNegative int version) {
         Preconditions.checkArgument(version >= 0, "raknetProtocolVersion cannot be negative");
         this.raknetProtocolVersion = version;
         return this;
      }

      public Builder minecraftVersion(@NonNull String minecraftVersion) {
         Preconditions.checkNotNull(minecraftVersion, "minecraftVersion");
         Preconditions.checkArgument(!minecraftVersion.isEmpty() && minecraftVersion.split("\\.").length > 2, "Invalid minecraftVersion");
         this.minecraftVersion = minecraftVersion;
         return this;
      }

      public Builder helper(@NonNull Supplier<BedrockCodecHelper> helperFactory) {
         Preconditions.checkNotNull(helperFactory, "helperFactory");
         this.helperFactory = helperFactory;
         return this;
      }

      public BedrockCodecHelper createHelper() {
         return (BedrockCodecHelper)this.helperFactory.get();
      }

      public BedrockCodec build() {
         Preconditions.checkArgument(this.protocolVersion >= 0, "No protocol version defined");
         Preconditions.checkNotNull(this.minecraftVersion, "No Minecraft version defined");
         Preconditions.checkNotNull(this.helperFactory, "helperFactory cannot be null");
         int largestId = -1;

         for(BedrockPacketDefinition<? extends BedrockPacket> info : this.packets.values()) {
            if (info.getId() > largestId) {
               largestId = info.getId();
            }
         }

         Preconditions.checkArgument(largestId > -1, "Must have at least one packet registered");
         BedrockPacketDefinition<? extends BedrockPacket>[] packetsById = new BedrockPacketDefinition[largestId + 1];

         for(BedrockPacketDefinition<? extends BedrockPacket> info : this.packets.values()) {
            packetsById[info.getId()] = info;
         }

         return new BedrockCodec(this.protocolVersion, this.minecraftVersion, packetsById, this.packets, this.helperFactory, (BedrockCodecHelper)this.helperFactory.get(), this.raknetProtocolVersion);
      }

      private Builder() {
         this.packets = new IdentityHashMap();
         this.protocolVersion = -1;
         this.raknetProtocolVersion = 10;
         this.minecraftVersion = null;
      }
   }
}
