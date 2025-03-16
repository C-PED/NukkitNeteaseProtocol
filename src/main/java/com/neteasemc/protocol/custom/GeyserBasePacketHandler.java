package com.neteasemc.protocol.custom;

import com.neteasemc.protocol.custom.packet.customItemPacket.CustomItemPacket;
import com.neteasemc.protocol.custom.packet.formPacket.FormPacket;
import com.neteasemc.protocol.custom.packet.geyserTransferPacket.GeyserTransferPacket;
import com.neteasemc.protocol.custom.packet.neteaseJsonPacket.NeteaseJsonPacket;
import com.neteasemc.protocol.custom.packet.playerInfoPacket.PlayerInfoPacket;
import com.neteasemc.protocol.custom.packet.setEntityDataPacket.SetEntityDataPacket;
import com.neteasemc.protocol.custom.packet.skinConfirmPacket.SkinConfirmPacket;
import com.neteasemc.protocol.custom.packet.spigotInfoPacket.SpigotInfoPacket;
import com.nukkitx.protocol.common.PacketSignal;
import org.bukkit.entity.Player;

public interface GeyserBasePacketHandler {
   default PacketSignal handle(SkinConfirmPacket packet) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(SkinConfirmPacket packet, Player player) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(CustomItemPacket packet) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(CustomItemPacket packet, Player player) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(PlayerInfoPacket packet) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(PlayerInfoPacket packet, Player player) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(SpigotInfoPacket packet) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(SpigotInfoPacket packet, Player player) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(FormPacket packet) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(FormPacket packet, Player player) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(SetEntityDataPacket packet) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(SetEntityDataPacket packet, Player player) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(NeteaseJsonPacket packet) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(NeteaseJsonPacket packet, Player player) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(GeyserTransferPacket packet) {
      return PacketSignal.UNHANDLED;
   }

   default PacketSignal handle(GeyserTransferPacket packet, Player player) {
      return PacketSignal.UNHANDLED;
   }
}
