package com.neteasemc.protocol.netgame.v486.serializer;

import com.neteasemc.protocol.netgame.NetworkGamePacket;
import com.neteasemc.protocol.netgame.subpacket.KickoutUserSubPacket;
import com.neteasemc.protocol.netgame.subpacket.MasterCommandSubPacket;
import com.neteasemc.protocol.netgame.subpacket.MasterConnectSubPacket;
import com.neteasemc.protocol.netgame.subpacket.PingServerRequestSubPacket;
import com.neteasemc.protocol.netgame.subpacket.ReplyStatusSubPacket;
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

public class NetworkGameSerializer_v486 implements BedrockPacketSerializer<NetworkGamePacket> {
   public static final NetworkGameSerializer_v486 INSTANCE = new NetworkGameSerializer_v486();
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
      packet.getSubPacket().doWrite(buffer, helper);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkGamePacket packet) {
      int id = buffer.readUnsignedByte();
      Supplier<? extends SubPacket> factory = (Supplier)subPackets.get(id);
      SubPacket subPacket = (SubPacket)factory.get();
      subPacket.doRead(buffer, helper);
      packet.setSubPacket(subPacket);
   }

   static {
      register(MasterConnectSubPacket.class);
      register(KickoutUserSubPacket.class);
      register(ReplyStatusSubPacket.class);
      register(MasterCommandSubPacket.class);
      register(PingServerRequestSubPacket.class);
      register(PingServerRequestSubPacket.class);
   }
}
