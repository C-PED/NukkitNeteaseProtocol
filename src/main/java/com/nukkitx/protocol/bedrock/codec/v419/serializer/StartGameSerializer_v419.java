package com.nukkitx.protocol.bedrock.codec.v419.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.AuthoritativeMovementMode;
import com.nukkitx.protocol.bedrock.data.BlockPropertyData;
import com.nukkitx.protocol.bedrock.data.GamePublishSetting;
import com.nukkitx.protocol.bedrock.data.GameType;
import com.nukkitx.protocol.bedrock.data.PlayerPermission;
import com.nukkitx.protocol.bedrock.data.SpawnBiomeType;
import com.nukkitx.protocol.bedrock.data.definitions.SimpleItemDefinition;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.common.util.OptionalBoolean;
import com.nukkitx.protocol.common.util.TriConsumer;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import org.cloudburstmc.nbt.NbtMap;

public class StartGameSerializer_v419 implements BedrockPacketSerializer<StartGamePacket> {
   public static final StartGameSerializer_v419 INSTANCE = new StartGameSerializer_v419();
   protected static final PlayerPermission[] PLAYER_PERMISSIONS = PlayerPermission.values();
   protected static final AuthoritativeMovementMode[] MOVEMENT_MODES = AuthoritativeMovementMode.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      VarInts.writeLong(buffer, packet.getUniqueEntityId());
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      VarInts.writeInt(buffer, packet.getPlayerGameType().ordinal());
      helper.writeVector3f(buffer, packet.getPlayerPosition());
      helper.writeVector2f(buffer, packet.getRotation());
      this.writeLevelSettings(buffer, helper, packet);
      helper.writeString(buffer, packet.getLevelId());
      helper.writeString(buffer, packet.getLevelName());
      helper.writeString(buffer, packet.getPremiumWorldTemplateId());
      buffer.writeBoolean(packet.isTrial());
      VarInts.writeInt(buffer, packet.getAuthoritativeMovementMode().ordinal());
      buffer.writeLongLE(packet.getCurrentTick());
      VarInts.writeInt(buffer, packet.getEnchantmentSeed());
      helper.writeArray(buffer, packet.getBlockProperties(), (buf, packetHelper, block) -> {
         packetHelper.writeString(buf, block.getName());
         packetHelper.writeTag(buf, block.getProperties());
      });
      helper.writeArray(buffer, packet.getItemDefinitions(), (buf, packetHelper, entry) -> {
         packetHelper.writeString(buf, entry.getIdentifier());
         buf.writeShortLE(entry.getRuntimeId());
         buf.writeBoolean(entry.isComponentBased());
      });
      helper.writeString(buffer, packet.getMultiplayerCorrelationId());
      buffer.writeBoolean(packet.isInventoriesServerAuthoritative());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      packet.setUniqueEntityId(VarInts.readLong(buffer));
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setPlayerGameType(GameType.from(VarInts.readInt(buffer)));
      packet.setPlayerPosition(helper.readVector3f(buffer));
      packet.setRotation(helper.readVector2f(buffer));
      this.readLevelSettings(buffer, helper, packet);
      packet.setLevelId(helper.readString(buffer));
      packet.setLevelName(helper.readString(buffer));
      packet.setPremiumWorldTemplateId(helper.readString(buffer));
      packet.setTrial(buffer.readBoolean());
      packet.setAuthoritativeMovementMode(MOVEMENT_MODES[VarInts.readInt(buffer)]);
      packet.setCurrentTick(buffer.readLongLE());
      packet.setEnchantmentSeed(VarInts.readInt(buffer));
      helper.readArray(buffer, packet.getBlockProperties(), (buf, packetHelper) -> {
         String name = packetHelper.readString(buf);
         NbtMap properties = packetHelper.readTag(buf, NbtMap.class);
         return new BlockPropertyData(name, properties);
      });
      helper.readArray(buffer, packet.getItemDefinitions(), (buf, packetHelper) -> {
         String identifier = packetHelper.readString(buf);
         short id = buf.readShortLE();
         boolean componentBased = buf.readBoolean();
         return new SimpleItemDefinition(identifier, id, componentBased);
      });
      packet.setMultiplayerCorrelationId(helper.readString(buffer));
      packet.setInventoriesServerAuthoritative(buffer.readBoolean());
   }

   protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      this.writeSeed(buffer, packet.getSeed());
      buffer.writeShortLE(packet.getSpawnBiomeType().ordinal());
      helper.writeString(buffer, packet.getCustomBiomeName());
      VarInts.writeInt(buffer, packet.getDimensionId());
      VarInts.writeInt(buffer, packet.getGeneratorId());
      VarInts.writeInt(buffer, packet.getLevelGameType().ordinal());
      VarInts.writeInt(buffer, packet.getDifficulty());
      helper.writeBlockPosition(buffer, packet.getDefaultSpawn());
      buffer.writeBoolean(packet.isAchievementsDisabled());
      VarInts.writeInt(buffer, packet.getDayCycleStopTime());
      VarInts.writeInt(buffer, packet.getEduEditionOffers());
      buffer.writeBoolean(packet.isEduFeaturesEnabled());
      helper.writeString(buffer, packet.getEducationProductionId());
      buffer.writeFloatLE(packet.getRainLevel());
      buffer.writeFloatLE(packet.getLightningLevel());
      buffer.writeBoolean(packet.isPlatformLockedContentConfirmed());
      buffer.writeBoolean(packet.isMultiplayerGame());
      buffer.writeBoolean(packet.isBroadcastingToLan());
      VarInts.writeInt(buffer, packet.getXblBroadcastMode().ordinal());
      VarInts.writeInt(buffer, packet.getPlatformBroadcastMode().ordinal());
      buffer.writeBoolean(packet.isCommandsEnabled());
      buffer.writeBoolean(packet.isTexturePacksRequired());
      List var10002 = packet.getGamerules();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeGameRule);
      helper.writeExperiments(buffer, packet.getExperiments());
      buffer.writeBoolean(packet.isExperimentsPreviouslyToggled());
      buffer.writeBoolean(packet.isBonusChestEnabled());
      buffer.writeBoolean(packet.isStartingWithMap());
      VarInts.writeInt(buffer, packet.getDefaultPlayerPermission().ordinal());
      buffer.writeIntLE(packet.getServerChunkTickRange());
      buffer.writeBoolean(packet.isBehaviorPackLocked());
      buffer.writeBoolean(packet.isResourcePackLocked());
      buffer.writeBoolean(packet.isFromLockedWorldTemplate());
      buffer.writeBoolean(packet.isUsingMsaGamertagsOnly());
      buffer.writeBoolean(packet.isFromWorldTemplate());
      buffer.writeBoolean(packet.isWorldTemplateOptionLocked());
      buffer.writeBoolean(packet.isOnlySpawningV1Villagers());
      helper.writeString(buffer, packet.getVanillaVersion());
      buffer.writeIntLE(packet.getLimitedWorldWidth());
      buffer.writeIntLE(packet.getLimitedWorldHeight());
      buffer.writeBoolean(packet.isNetherType());
      helper.writeOptional(buffer, OptionalBoolean::isPresent, packet.getForceExperimentalGameplay(), (buf, optional) -> buf.writeBoolean(optional.getAsBoolean()));
   }

   protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      packet.setSeed(this.readSeed(buffer));
      packet.setSpawnBiomeType(SpawnBiomeType.byId(buffer.readShortLE()));
      packet.setCustomBiomeName(helper.readString(buffer));
      packet.setDimensionId(VarInts.readInt(buffer));
      packet.setGeneratorId(VarInts.readInt(buffer));
      packet.setLevelGameType(GameType.from(VarInts.readInt(buffer)));
      packet.setDifficulty(VarInts.readInt(buffer));
      packet.setDefaultSpawn(helper.readBlockPosition(buffer));
      packet.setAchievementsDisabled(buffer.readBoolean());
      packet.setDayCycleStopTime(VarInts.readInt(buffer));
      packet.setEduEditionOffers(VarInts.readInt(buffer));
      packet.setEduFeaturesEnabled(buffer.readBoolean());
      packet.setEducationProductionId(helper.readString(buffer));
      packet.setRainLevel(buffer.readFloatLE());
      packet.setLightningLevel(buffer.readFloatLE());
      packet.setPlatformLockedContentConfirmed(buffer.readBoolean());
      packet.setMultiplayerGame(buffer.readBoolean());
      packet.setBroadcastingToLan(buffer.readBoolean());
      packet.setXblBroadcastMode(GamePublishSetting.byId(VarInts.readInt(buffer)));
      packet.setPlatformBroadcastMode(GamePublishSetting.byId(VarInts.readInt(buffer)));
      packet.setCommandsEnabled(buffer.readBoolean());
      packet.setTexturePacksRequired(buffer.readBoolean());
      List var10002 = packet.getGamerules();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, var10002, helper::readGameRule);
      helper.readExperiments(buffer, packet.getExperiments());
      packet.setExperimentsPreviouslyToggled(buffer.readBoolean());
      packet.setBonusChestEnabled(buffer.readBoolean());
      packet.setStartingWithMap(buffer.readBoolean());
      packet.setDefaultPlayerPermission(PLAYER_PERMISSIONS[VarInts.readInt(buffer)]);
      packet.setServerChunkTickRange(buffer.readIntLE());
      packet.setBehaviorPackLocked(buffer.readBoolean());
      packet.setResourcePackLocked(buffer.readBoolean());
      packet.setFromLockedWorldTemplate(buffer.readBoolean());
      packet.setUsingMsaGamertagsOnly(buffer.readBoolean());
      packet.setFromWorldTemplate(buffer.readBoolean());
      packet.setWorldTemplateOptionLocked(buffer.readBoolean());
      packet.setOnlySpawningV1Villagers(buffer.readBoolean());
      packet.setVanillaVersion(helper.readString(buffer));
      packet.setLimitedWorldWidth(buffer.readIntLE());
      packet.setLimitedWorldHeight(buffer.readIntLE());
      packet.setNetherType(buffer.readBoolean());
      packet.setForceExperimentalGameplay((OptionalBoolean)helper.readOptional(buffer, OptionalBoolean.empty(), (buf) -> OptionalBoolean.of(buf.readBoolean())));
   }

   protected long readSeed(ByteBuf buffer) {
      return (long)VarInts.readInt(buffer);
   }

   protected void writeSeed(ByteBuf buffer, long seed) {
      VarInts.writeInt(buffer, (int)seed);
   }

   protected StartGameSerializer_v419() {
   }
}
