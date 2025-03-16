package com.nukkitx.protocol.bedrock.codec.v332.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.StartGameSerializer_v291;
import com.nukkitx.protocol.bedrock.data.GamePublishSetting;
import com.nukkitx.protocol.bedrock.data.GameType;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class StartGameSerializer_v332 extends StartGameSerializer_v291 {
   public static final StartGameSerializer_v332 INSTANCE = new StartGameSerializer_v332();

   protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      this.writeSeed(buffer, packet.getSeed());
      VarInts.writeInt(buffer, packet.getDimensionId());
      VarInts.writeInt(buffer, packet.getGeneratorId());
      VarInts.writeInt(buffer, packet.getLevelGameType().ordinal());
      VarInts.writeInt(buffer, packet.getDifficulty());
      helper.writeBlockPosition(buffer, packet.getDefaultSpawn());
      buffer.writeBoolean(packet.isAchievementsDisabled());
      VarInts.writeInt(buffer, packet.getDayCycleStopTime());
      buffer.writeBoolean(packet.getEduEditionOffers() != 0);
      buffer.writeBoolean(packet.isEduFeaturesEnabled());
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
   }

   protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      packet.setSeed(this.readSeed(buffer));
      packet.setDimensionId(VarInts.readInt(buffer));
      packet.setGeneratorId(VarInts.readInt(buffer));
      packet.setLevelGameType(GameType.values()[VarInts.readInt(buffer)]);
      packet.setDifficulty(VarInts.readInt(buffer));
      packet.setDefaultSpawn(helper.readBlockPosition(buffer));
      packet.setAchievementsDisabled(buffer.readBoolean());
      packet.setDayCycleStopTime(VarInts.readInt(buffer));
      packet.setEduEditionOffers(buffer.readBoolean() ? 1 : 0);
      packet.setEduFeaturesEnabled(buffer.readBoolean());
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
   }

   protected StartGameSerializer_v332() {
   }
}
