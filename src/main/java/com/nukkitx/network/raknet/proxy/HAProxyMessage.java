package com.nukkitx.network.raknet.proxy;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import io.netty.util.NetUtil;
import io.netty.util.internal.StringUtil;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public final class HAProxyMessage {
   private final HAProxyProtocolVersion protocolVersion;
   private final HAProxyCommand command;
   private final HAProxyProxiedProtocol proxiedProtocol;
   private final String sourceAddress;
   private final String destinationAddress;
   private final int sourcePort;
   private final int destinationPort;

   private HAProxyMessage(HAProxyProtocolVersion protocolVersion, HAProxyCommand command, HAProxyProxiedProtocol proxiedProtocol, String sourceAddress, String destinationAddress, String sourcePort, String destinationPort) {
      this(protocolVersion, command, proxiedProtocol, sourceAddress, destinationAddress, portStringToInt(sourcePort), portStringToInt(destinationPort));
   }

   public HAProxyMessage(HAProxyProtocolVersion protocolVersion, HAProxyCommand command, HAProxyProxiedProtocol proxiedProtocol, String sourceAddress, String destinationAddress, int sourcePort, int destinationPort) {
      ObjectUtil.checkNotNull(protocolVersion, "protocolVersion");
      ObjectUtil.checkNotNull(proxiedProtocol, "proxiedProtocol");
      HAProxyProxiedProtocol.AddressFamily addrFamily = proxiedProtocol.addressFamily();
      checkAddress(sourceAddress, addrFamily);
      checkAddress(destinationAddress, addrFamily);
      checkPort(sourcePort, addrFamily);
      checkPort(destinationPort, addrFamily);
      this.protocolVersion = protocolVersion;
      this.command = command;
      this.proxiedProtocol = proxiedProtocol;
      this.sourceAddress = sourceAddress;
      this.destinationAddress = destinationAddress;
      this.sourcePort = sourcePort;
      this.destinationPort = destinationPort;
   }

   static HAProxyMessage decodeHeader(ByteBuf header) {
      ObjectUtil.checkNotNull(header, "header");
      if (header.readableBytes() < 16) {
         throw new HAProxyProtocolException("incomplete header: " + header.readableBytes() + " bytes (expected: 16+ bytes)");
      } else {
         header.skipBytes(12);
         byte verCmdByte = header.readByte();

         HAProxyProtocolVersion ver;
         try {
            ver = HAProxyProtocolVersion.valueOf(verCmdByte);
         } catch (IllegalArgumentException e) {
            throw new HAProxyProtocolException(e);
         }

         if (ver != HAProxyProtocolVersion.V2) {
            throw new HAProxyProtocolException("version 1 unsupported: 0x" + Integer.toHexString(verCmdByte));
         } else {
            HAProxyCommand cmd;
            try {
               cmd = HAProxyCommand.valueOf(verCmdByte);
            } catch (IllegalArgumentException e) {
               throw new HAProxyProtocolException(e);
            }

            if (cmd == HAProxyCommand.LOCAL) {
               return unknownMsg(HAProxyProtocolVersion.V2, HAProxyCommand.LOCAL);
            } else {
               HAProxyProxiedProtocol protAndFam;
               try {
                  protAndFam = HAProxyProxiedProtocol.valueOf(header.readByte());
               } catch (IllegalArgumentException e) {
                  throw new HAProxyProtocolException(e);
               }

               if (protAndFam == HAProxyProxiedProtocol.UNKNOWN) {
                  return unknownMsg(HAProxyProtocolVersion.V2, HAProxyCommand.PROXY);
               } else {
                  int addressInfoLen = header.readUnsignedShort();
                  int srcPort = 0;
                  int dstPort = 0;
                  HAProxyProxiedProtocol.AddressFamily addressFamily = protAndFam.addressFamily();
                  String srcAddress;
                  String dstAddress;
                  if (addressFamily == HAProxyProxiedProtocol.AddressFamily.AF_UNIX) {
                     if (addressInfoLen < 216 || header.readableBytes() < 216) {
                        throw new HAProxyProtocolException("incomplete UNIX socket address information: " + Math.min(addressInfoLen, header.readableBytes()) + " bytes (expected: 216+ bytes)");
                     }

                     int startIdx = header.readerIndex();
                     int addressEnd = header.forEachByte(startIdx, 108, ByteProcessor.FIND_NUL);
                     int addressLen;
                     if (addressEnd == -1) {
                        addressLen = 108;
                     } else {
                        addressLen = addressEnd - startIdx;
                     }

                     srcAddress = header.toString(startIdx, addressLen, CharsetUtil.US_ASCII);
                     startIdx += 108;
                     addressEnd = header.forEachByte(startIdx, 108, ByteProcessor.FIND_NUL);
                     if (addressEnd == -1) {
                        addressLen = 108;
                     } else {
                        addressLen = addressEnd - startIdx;
                     }

                     dstAddress = header.toString(startIdx, addressLen, CharsetUtil.US_ASCII);
                     header.readerIndex(startIdx + 108);
                  } else {
                     int addressLen;
                     if (addressFamily == HAProxyProxiedProtocol.AddressFamily.AF_IPv4) {
                        if (addressInfoLen < 12 || header.readableBytes() < 12) {
                           throw new HAProxyProtocolException("incomplete IPv4 address information: " + Math.min(addressInfoLen, header.readableBytes()) + " bytes (expected: 12+ bytes)");
                        }

                        addressLen = 4;
                     } else {
                        if (addressFamily != HAProxyProxiedProtocol.AddressFamily.AF_IPv6) {
                           throw new HAProxyProtocolException("unable to parse address information (unknown address family: " + addressFamily + ')');
                        }

                        if (addressInfoLen < 36 || header.readableBytes() < 36) {
                           throw new HAProxyProtocolException("incomplete IPv6 address information: " + Math.min(addressInfoLen, header.readableBytes()) + " bytes (expected: 36+ bytes)");
                        }

                        addressLen = 16;
                     }

                     srcAddress = ipBytesToString(header, addressLen);
                     dstAddress = ipBytesToString(header, addressLen);
                     srcPort = header.readUnsignedShort();
                     dstPort = header.readUnsignedShort();
                  }

                  while(skipNextTLV(header)) {
                  }

                  return new HAProxyMessage(ver, cmd, protAndFam, srcAddress, dstAddress, srcPort, dstPort);
               }
            }
         }
      }
   }

   private static boolean skipNextTLV(ByteBuf header) {
      if (header.readableBytes() < 4) {
         return false;
      } else {
         header.skipBytes(1);
         header.skipBytes(header.readUnsignedShort());
         return true;
      }
   }

   static HAProxyMessage decodeHeader(String header) {
      if (header == null) {
         throw new HAProxyProtocolException("header");
      } else {
         String[] parts = header.split(" ");
         int numParts = parts.length;
         if (numParts < 2) {
            throw new HAProxyProtocolException("invalid header: " + header + " (expected: 'PROXY' and proxied protocol values)");
         } else if (!"PROXY".equals(parts[0])) {
            throw new HAProxyProtocolException("unknown identifier: " + parts[0]);
         } else {
            HAProxyProxiedProtocol protAndFam;
            try {
               protAndFam = HAProxyProxiedProtocol.valueOf(parts[1]);
            } catch (IllegalArgumentException e) {
               throw new HAProxyProtocolException(e);
            }

            if (protAndFam != HAProxyProxiedProtocol.TCP4 && protAndFam != HAProxyProxiedProtocol.TCP6 && protAndFam != HAProxyProxiedProtocol.UNKNOWN) {
               throw new HAProxyProtocolException("unsupported v1 proxied protocol: " + parts[1]);
            } else if (protAndFam == HAProxyProxiedProtocol.UNKNOWN) {
               return unknownMsg(HAProxyProtocolVersion.V1, HAProxyCommand.PROXY);
            } else if (numParts != 6) {
               throw new HAProxyProtocolException("invalid TCP4/6 header: " + header + " (expected: 6 parts)");
            } else {
               try {
                  return new HAProxyMessage(HAProxyProtocolVersion.V1, HAProxyCommand.PROXY, protAndFam, parts[2], parts[3], parts[4], parts[5]);
               } catch (RuntimeException e) {
                  throw new HAProxyProtocolException("invalid HAProxy message", e);
               }
            }
         }
      }
   }

   private static HAProxyMessage unknownMsg(HAProxyProtocolVersion version, HAProxyCommand command) {
      return new HAProxyMessage(version, command, HAProxyProxiedProtocol.UNKNOWN, (String)null, (String)null, 0, 0);
   }

   private static String ipBytesToString(ByteBuf header, int addressLen) {
      StringBuilder sb = new StringBuilder();
      int ipv4Len = 4;
      int ipv6Len = 8;
      if (addressLen == 4) {
         for(int i = 0; i < 4; ++i) {
            sb.append(header.readByte() & 255);
            sb.append('.');
         }
      } else {
         for(int i = 0; i < 8; ++i) {
            sb.append(Integer.toHexString(header.readUnsignedShort()));
            sb.append(':');
         }
      }

      sb.setLength(sb.length() - 1);
      return sb.toString();
   }

   private static int portStringToInt(String value) {
      int port;
      try {
         port = Integer.parseInt(value);
      } catch (NumberFormatException e) {
         throw new IllegalArgumentException("invalid port: " + value, e);
      }

      if (port > 0 && port <= 65535) {
         return port;
      } else {
         throw new IllegalArgumentException("invalid port: " + value + " (expected: 1 ~ 65535)");
      }
   }

   private static void checkAddress(String address, HAProxyProxiedProtocol.AddressFamily addrFamily) {
      ObjectUtil.checkNotNull(addrFamily, "addrFamily");
      switch (addrFamily) {
         case AF_UNSPEC:
            if (address != null) {
               throw new IllegalArgumentException("unable to validate an AF_UNSPEC address: " + address);
            }

            return;
         case AF_UNIX:
            ObjectUtil.checkNotNull(address, "address");
            if (address.getBytes(CharsetUtil.US_ASCII).length > 108) {
               throw new IllegalArgumentException("invalid AF_UNIX address: " + address);
            }

            return;
         default:
            ObjectUtil.checkNotNull(address, "address");
            switch (addrFamily) {
               case AF_IPv4:
                  if (!NetUtil.isValidIpV4Address(address)) {
                     throw new IllegalArgumentException("invalid IPv4 address: " + address);
                  }
                  break;
               case AF_IPv6:
                  if (!NetUtil.isValidIpV6Address(address)) {
                     throw new IllegalArgumentException("invalid IPv6 address: " + address);
                  }
                  break;
               default:
                  throw new IllegalArgumentException("unexpected addrFamily: " + addrFamily);
            }

      }
   }

   private static void checkPort(int port, HAProxyProxiedProtocol.AddressFamily addrFamily) {
      switch (addrFamily) {
         case AF_UNSPEC:
         case AF_UNIX:
            if (port != 0) {
               throw new IllegalArgumentException("port cannot be specified with addrFamily: " + addrFamily);
            }
            break;
         case AF_IPv4:
         case AF_IPv6:
            if (port < 0 || port > 65535) {
               throw new IllegalArgumentException("invalid port: " + port + " (expected: 0 ~ 65535)");
            }
            break;
         default:
            throw new IllegalArgumentException("unexpected addrFamily: " + addrFamily);
      }

   }

   public HAProxyProtocolVersion protocolVersion() {
      return this.protocolVersion;
   }

   public HAProxyCommand command() {
      return this.command;
   }

   public HAProxyProxiedProtocol proxiedProtocol() {
      return this.proxiedProtocol;
   }

   public String sourceAddress() {
      return this.sourceAddress;
   }

   public String destinationAddress() {
      return this.destinationAddress;
   }

   public int sourcePort() {
      return this.sourcePort;
   }

   public int destinationPort() {
      return this.destinationPort;
   }

   public String toString() {
      return StringUtil.simpleClassName(this) + "(protocolVersion: " + this.protocolVersion + ", command: " + this.command + ", proxiedProtocol: " + this.proxiedProtocol + ", sourceAddress: " + this.sourceAddress + ", destinationAddress: " + this.destinationAddress + ", sourcePort: " + this.sourcePort + ", destinationPort: " + this.destinationPort + ")";
   }

   public InetSocketAddress sourceInetSocketAddress() {
      HAProxyProxiedProtocol.AddressFamily af = this.proxiedProtocol.addressFamily();
      if (af != HAProxyProxiedProtocol.AddressFamily.AF_IPv4 && af != HAProxyProxiedProtocol.AddressFamily.AF_IPv6) {
         throw new IllegalStateException("Unsupported address family: " + this.proxiedProtocol.addressFamily());
      } else {
         InetAddress addr;
         try {
            addr = Inet4Address.getByName(this.sourceAddress);
         } catch (UnknownHostException e) {
            throw new RuntimeException(e);
         }

         return new InetSocketAddress(addr, this.sourcePort);
      }
   }
}
