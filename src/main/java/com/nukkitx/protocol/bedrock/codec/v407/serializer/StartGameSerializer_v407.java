package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v388.serializer.StartGameSerializer_v388;
import com.nukkitx.protocol.bedrock.data.GamePublishSetting;
import com.nukkitx.protocol.bedrock.data.GameType;
import com.nukkitx.protocol.bedrock.data.PlayerPermission;
import com.nukkitx.protocol.bedrock.data.SpawnBiomeType;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.common.util.OptionalBoolean;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class StartGameSerializer_v407 extends StartGameSerializer_v388 {
   public static final StartGameSerializer_v407 INSTANCE = new StartGameSerializer_v407();
   private static final PlayerPermission[] PLAYER_PERMISSIONS = PlayerPermission.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeBoolean(packet.isInventoriesServerAuthoritative());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      super.deserialize(buffer, helper, packet);
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

   protected StartGameSerializer_v407() {
   }
}
