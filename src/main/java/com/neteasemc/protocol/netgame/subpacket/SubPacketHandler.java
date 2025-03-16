package com.neteasemc.protocol.netgame.subpacket;

import com.nukkitx.protocol.common.PacketSignal;

public interface SubPacketHandler {
   default PacketSignal handle(MasterConnectSubPacket packet) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(ReplyStatusSubPacket replyStatusSubPacket) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(PingServerRequestSubPacket pingServerRequestSubPacket) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(PingServerResponseSubPacket pingServerResponseSubPacket) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(ProxyConnectSubPacket proxyConnectSubPacket) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(ProxyUserConnectSubPacket proxyUserConnectSubPacket) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(PlayerStatusSubPacket playerStatusSubPacket) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(MasterCommandSubPacket MasterCommandPacket) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(KickoutUserSubPacket kickoutUser) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(UserLogoutSubPacket logoutUser) {
      return PacketSignal.UNHANDLED;
   }
}
