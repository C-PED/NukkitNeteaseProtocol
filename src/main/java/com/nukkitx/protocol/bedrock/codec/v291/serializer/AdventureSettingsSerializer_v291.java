package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.AdventureSetting;
import com.nukkitx.protocol.bedrock.data.PlayerPermission;
import com.nukkitx.protocol.bedrock.data.command.CommandPermission;
import com.nukkitx.protocol.bedrock.packet.AdventureSettingsPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Set;

public class AdventureSettingsSerializer_v291 implements BedrockPacketSerializer<AdventureSettingsPacket> {
   public static final AdventureSettingsSerializer_v291 INSTANCE = new AdventureSettingsSerializer_v291();
   private static final CommandPermission[] COMMAND_PERMISSIONS = CommandPermission.values();
   private static final PlayerPermission[] PLAYER_PERMISSIONS = PlayerPermission.values();
   private static final AdventureSetting[] FLAGS_1;
   private static final AdventureSetting[] FLAGS_2;
   private static final Object2IntMap<AdventureSetting> FLAGS_TO_BIT_1;
   private static final Object2IntMap<AdventureSetting> FLAGS_TO_BIT_2;

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AdventureSettingsPacket packet) {
      int flags1 = 0;
      int flags2 = 0;

      for(AdventureSetting setting : packet.getSettings()) {
         if (FLAGS_TO_BIT_1.containsKey(setting)) {
            flags1 |= FLAGS_TO_BIT_1.getInt(setting);
         } else if (FLAGS_TO_BIT_2.containsKey(setting)) {
            flags2 |= FLAGS_TO_BIT_2.getInt(setting);
         }
      }

      VarInts.writeUnsignedInt(buffer, flags1);
      VarInts.writeUnsignedInt(buffer, packet.getCommandPermission().ordinal());
      VarInts.writeUnsignedInt(buffer, flags2);
      VarInts.writeUnsignedInt(buffer, packet.getPlayerPermission().ordinal());
      VarInts.writeUnsignedInt(buffer, 0);
      buffer.writeLongLE(packet.getUniqueEntityId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AdventureSettingsPacket packet) {
      int flags1 = VarInts.readUnsignedInt(buffer);
      packet.setCommandPermission(COMMAND_PERMISSIONS[VarInts.readUnsignedInt(buffer)]);
      int flags2 = VarInts.readUnsignedInt(buffer);
      packet.setPlayerPermission(PLAYER_PERMISSIONS[VarInts.readUnsignedInt(buffer)]);
      VarInts.readUnsignedInt(buffer);
      packet.setUniqueEntityId(buffer.readLongLE());
      Set<AdventureSetting> settings = packet.getSettings();
      readFlags(flags1, FLAGS_1, settings);
      readFlags(flags2, FLAGS_2, settings);
   }

   protected static void readFlags(int flags, AdventureSetting[] mappings, Set<AdventureSetting> settings) {
      for(int i = 0; i < mappings.length; ++i) {
         AdventureSetting setting = mappings[i];
         if (setting != null && (flags & 1 << i) != 0) {
            settings.add(setting);
         }
      }

   }

   protected AdventureSettingsSerializer_v291() {
   }

   static {
      FLAGS_1 = new AdventureSetting[]{AdventureSetting.WORLD_IMMUTABLE, AdventureSetting.NO_PVM, AdventureSetting.NO_MVP, null, AdventureSetting.SHOW_NAME_TAGS, AdventureSetting.AUTO_JUMP, AdventureSetting.MAY_FLY, AdventureSetting.NO_CLIP, AdventureSetting.WORLD_BUILDER, AdventureSetting.FLYING, AdventureSetting.MUTED};
      FLAGS_2 = new AdventureSetting[]{AdventureSetting.MINE, AdventureSetting.DOORS_AND_SWITCHES, AdventureSetting.OPEN_CONTAINERS, AdventureSetting.ATTACK_PLAYERS, AdventureSetting.ATTACK_MOBS, AdventureSetting.OPERATOR, null, AdventureSetting.TELEPORT, AdventureSetting.BUILD, AdventureSetting.DEFAULT_LEVEL_PERMISSIONS};
      FLAGS_TO_BIT_1 = new Object2IntOpenHashMap();
      FLAGS_TO_BIT_2 = new Object2IntOpenHashMap();
      FLAGS_TO_BIT_1.put(AdventureSetting.WORLD_IMMUTABLE, 1);
      FLAGS_TO_BIT_1.put(AdventureSetting.NO_PVM, 2);
      FLAGS_TO_BIT_1.put(AdventureSetting.NO_MVP, 4);
      FLAGS_TO_BIT_1.put(AdventureSetting.SHOW_NAME_TAGS, 16);
      FLAGS_TO_BIT_1.put(AdventureSetting.AUTO_JUMP, 32);
      FLAGS_TO_BIT_1.put(AdventureSetting.MAY_FLY, 64);
      FLAGS_TO_BIT_1.put(AdventureSetting.NO_CLIP, 128);
      FLAGS_TO_BIT_1.put(AdventureSetting.WORLD_BUILDER, 256);
      FLAGS_TO_BIT_1.put(AdventureSetting.FLYING, 512);
      FLAGS_TO_BIT_1.put(AdventureSetting.MUTED, 1024);
      FLAGS_TO_BIT_2.put(AdventureSetting.MINE, 1);
      FLAGS_TO_BIT_2.put(AdventureSetting.DOORS_AND_SWITCHES, 2);
      FLAGS_TO_BIT_2.put(AdventureSetting.OPEN_CONTAINERS, 4);
      FLAGS_TO_BIT_2.put(AdventureSetting.ATTACK_PLAYERS, 8);
      FLAGS_TO_BIT_2.put(AdventureSetting.ATTACK_MOBS, 16);
      FLAGS_TO_BIT_2.put(AdventureSetting.OPERATOR, 32);
      FLAGS_TO_BIT_2.put(AdventureSetting.TELEPORT, 128);
      FLAGS_TO_BIT_2.put(AdventureSetting.BUILD, 256);
      FLAGS_TO_BIT_2.put(AdventureSetting.DEFAULT_LEVEL_PERMISSIONS, 512);
   }
}
