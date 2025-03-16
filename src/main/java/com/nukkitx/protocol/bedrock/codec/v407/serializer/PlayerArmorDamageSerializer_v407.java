package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.PlayerArmorDamageFlag;
import com.nukkitx.protocol.bedrock.packet.PlayerArmorDamagePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.Set;

public class PlayerArmorDamageSerializer_v407 implements BedrockPacketSerializer<PlayerArmorDamagePacket> {
   public static final PlayerArmorDamageSerializer_v407 INSTANCE = new PlayerArmorDamageSerializer_v407();
   private static final PlayerArmorDamageFlag[] FLAGS = PlayerArmorDamageFlag.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerArmorDamagePacket packet) {
      int flags = 0;

      for(PlayerArmorDamageFlag flag : packet.getFlags()) {
         flags |= 1 << flag.ordinal();
      }

      buffer.writeByte(flags);
      int[] damage = packet.getDamage();

      for(PlayerArmorDamageFlag flag : packet.getFlags()) {
         int value = damage[flag.ordinal()];
         VarInts.writeInt(buffer, value);
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerArmorDamagePacket packet) {
      int flagsVal = buffer.readUnsignedByte();
      Set<PlayerArmorDamageFlag> flags = packet.getFlags();
      int[] damage = packet.getDamage();

      for(int i = 0; i < 4; ++i) {
         if ((flagsVal & 1 << i) != 0) {
            flags.add(FLAGS[i]);
            damage[i] = VarInts.readInt(buffer);
         }
      }

   }

   protected PlayerArmorDamageSerializer_v407() {
   }
}
