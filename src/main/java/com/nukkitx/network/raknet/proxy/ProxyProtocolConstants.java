package com.nukkitx.network.raknet.proxy;

public interface ProxyProtocolConstants {
   byte COMMAND_LOCAL_BYTE = 0;
   byte COMMAND_PROXY_BYTE = 1;
   byte VERSION_ONE_BYTE = 16;
   byte VERSION_TWO_BYTE = 32;
   byte TRANSPORT_UNSPEC_BYTE = 0;
   byte TRANSPORT_STREAM_BYTE = 1;
   byte TRANSPORT_DGRAM_BYTE = 2;
   byte AF_UNSPEC_BYTE = 0;
   byte AF_IPV4_BYTE = 16;
   byte AF_IPV6_BYTE = 32;
   byte AF_UNIX_BYTE = 48;
   byte TPAF_UNKNOWN_BYTE = 0;
   byte TPAF_TCP4_BYTE = 17;
   byte TPAF_TCP6_BYTE = 33;
   byte TPAF_UDP4_BYTE = 18;
   byte TPAF_UDP6_BYTE = 34;
   byte TPAF_UNIX_STREAM_BYTE = 49;
   byte TPAF_UNIX_DGRAM_BYTE = 50;
   byte[] BINARY_PREFIX = new byte[]{13, 10, 13, 10, 0, 13, 10, 81, 85, 73, 84, 10};
   byte[] TEXT_PREFIX = new byte[]{80, 82, 79, 88, 89};
   int V1_MAX_LENGTH = 108;
   int V2_MAX_LENGTH = 65551;
   int V2_MIN_LENGTH = 232;
   int V2_MAX_TLV = 65319;
   int BINARY_PREFIX_LENGTH = BINARY_PREFIX.length;
}
