package com.nukkitx.protocol.bedrock;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import org.cloudburstmc.netty.channel.raknet.RakDisconnectReason;

public final class BedrockDisconnectReasons {
   public static final String DISCONNECTED = "disconnect.disconnected";
   public static final String CLOSED = "disconnect.closed";
   public static final String REMOVED = "disconnect.removed";
   public static final String TIMEOUT = "disconnect.timeout";
   public static final String UNKNOWN = "disconnect.lost";
   private static final Map<RakDisconnectReason, String> FROM_RAKNET = generateRakNetMappings();

   private static Map<RakDisconnectReason, String> generateRakNetMappings() {
      EnumMap<RakDisconnectReason, String> map = new EnumMap(RakDisconnectReason.class);
      map.put(RakDisconnectReason.CLOSED_BY_REMOTE_PEER, "disconnect.closed");
      map.put(RakDisconnectReason.DISCONNECTED, "disconnect.disconnected");
      map.put(RakDisconnectReason.TIMED_OUT, "disconnect.timeout");
      map.put(RakDisconnectReason.BAD_PACKET, "disconnect.removed");
      return Collections.unmodifiableMap(map);
   }

   public static String getReason(RakDisconnectReason reason) {
      return (String)FROM_RAKNET.getOrDefault(reason, reason.name());
   }

   private BedrockDisconnectReasons() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
