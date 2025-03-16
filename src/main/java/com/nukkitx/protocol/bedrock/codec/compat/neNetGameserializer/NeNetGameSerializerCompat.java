package com.nukkitx.protocol.bedrock.codec.compat.neNetGameserializer;

import com.neteasemc.protocol.netgame.NetworkGamePacket;
import com.neteasemc.protocol.netgame.subpacket.PingServerRequestSubPacket;
import com.neteasemc.protocol.netgame.subpacket.PingServerResponseSubPacket;
import com.neteasemc.protocol.netgame.subpacket.PlayerStatusSubPacket;
import com.neteasemc.protocol.netgame.subpacket.ProxyConnectSubPacket;
import com.neteasemc.protocol.netgame.subpacket.ProxyUserConnectSubPacket;
import com.neteasemc.protocol.netgame.subpacket.SubPacket;
import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import io.netty.buffer.ByteBuf;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.lanternpowered.lmbda.LambdaFactory;
import org.lanternpowered.lmbda.MethodHandlesExtensions;

public class NeNetGameSerializerCompat implements BedrockPacketSerializer<NetworkGamePacket> {
   public static final NeNetGameSerializerCompat INSTANCE = new NeNetGameSerializerCompat();
   private static final Map<Integer, Supplier<? extends SubPacket>> subPackets = new HashMap();

   private static void register(Class<? extends SubPacket> packetClass) {
      Supplier<? extends SubPacket> factory;
      try {
         MethodHandles.Lookup lookup = MethodHandlesExtensions.privateLookupIn(packetClass, MethodHandles.lookup());
         MethodHandle handle = lookup.findConstructor(packetClass, MethodType.methodType(Void.TYPE));
         factory = LambdaFactory.createSupplier(handle);
      } catch (IllegalAccessException | NoSuchMethodException e) {
         throw new IllegalArgumentException("Unable to find suitable constructor for packet factory", e);
      }

      SubPacket subPacket = (SubPacket)factory.get();
      subPackets.put(subPacket.getNetgamePacketId(), factory);
   }

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkGamePacket packet) {
      buffer.writeByte(packet.getSubPacket().getNetgamePacketId());
      packet.getSubPacket().serialize(buffer, helper);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkGamePacket packet) {
      int id = buffer.readUnsignedByte();
      Supplier<? extends SubPacket> factory = (Supplier)subPackets.get(id);
      if (factory == null) {
         throw new IllegalArgumentException("Unable to find suitable constructor for packet:" + id);
      } else {
         SubPacket subPacket = (SubPacket)factory.get();
         subPacket.deserialize(buffer, helper);
         packet.setSubPacket(subPacket);
      }
   }

   private NeNetGameSerializerCompat() {
   }

   static {
      register(PingServerRequestSubPacket.class);
      register(PingServerResponseSubPacket.class);
      register(ProxyConnectSubPacket.class);
      register(ProxyUserConnectSubPacket.class);
      register(PlayerStatusSubPacket.class);
   }
}
