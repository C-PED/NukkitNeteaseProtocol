package com.nukkitx.protocol.bedrock.codec.compat;

import com.nukkitx.protocol.bedrock.codec.BaseBedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.data.GameRuleData;
import com.nukkitx.protocol.bedrock.data.PlayerAbilityHolder;
import com.nukkitx.protocol.bedrock.data.command.CommandEnumData;
import com.nukkitx.protocol.bedrock.data.command.CommandOriginData;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataMap;
import com.nukkitx.protocol.bedrock.data.entity.EntityLinkData;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.data.skin.SerializedSkin;
import com.nukkitx.protocol.bedrock.data.structure.StructureSettings;
import com.nukkitx.protocol.common.util.TypeMap;
import io.netty.buffer.ByteBuf;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class NoopBedrockCodecHelper extends BaseBedrockCodecHelper {
   public static final NoopBedrockCodecHelper INSTANCE = new NoopBedrockCodecHelper();

   private NoopBedrockCodecHelper() {
      super(EntityDataTypeMap.builder().build(), TypeMap.empty("GameRule"));
   }

   public EntityLinkData readEntityLink(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   public void writeEntityLink(ByteBuf buffer, EntityLinkData link) {
      throw new UnsupportedOperationException();
   }

   public ItemData readNetItem(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   public void writeNetItem(ByteBuf buffer, ItemData item) {
      throw new UnsupportedOperationException();
   }

   public ItemData readItem(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   public void writeItem(ByteBuf buffer, ItemData item) {
      throw new UnsupportedOperationException();
   }

   public ItemData readItemInstance(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   public void writeItemInstance(ByteBuf buffer, ItemData item) {
      throw new UnsupportedOperationException();
   }

   public CommandOriginData readCommandOrigin(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   public void writeCommandOrigin(ByteBuf buffer, CommandOriginData commandOrigin) {
      throw new UnsupportedOperationException();
   }

   public GameRuleData<?> readGameRule(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   public void writeGameRule(ByteBuf buffer, GameRuleData<?> gameRule) {
      throw new UnsupportedOperationException();
   }

   public void readEntityData(ByteBuf buffer, EntityDataMap entityData) {
      throw new UnsupportedOperationException();
   }

   public void writeEntityData(ByteBuf buffer, EntityDataMap entityData) {
      throw new UnsupportedOperationException();
   }

   public CommandEnumData readCommandEnum(ByteBuf buffer, boolean soft) {
      throw new UnsupportedOperationException();
   }

   public void writeCommandEnum(ByteBuf buffer, CommandEnumData commandEnum) {
      throw new UnsupportedOperationException();
   }

   public StructureSettings readStructureSettings(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   public void writeStructureSettings(ByteBuf buffer, StructureSettings settings) {
      throw new UnsupportedOperationException();
   }

   public SerializedSkin readSkin(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   public void writeSkin(ByteBuf buffer, SerializedSkin skin) {
      throw new UnsupportedOperationException();
   }

   public <O> O readOptional(ByteBuf buffer, O emptyValue, Function<ByteBuf, O> function) {
      throw new UnsupportedOperationException();
   }

   public <T> void writeOptional(ByteBuf buffer, Predicate<T> isPresent, T object, BiConsumer<ByteBuf, T> consumer) {
      throw new UnsupportedOperationException();
   }

   public <T> void writeOptionalNull(ByteBuf buffer, T object, BiConsumer<ByteBuf, T> consumer) {
      throw new UnsupportedOperationException();
   }

   public void writePlayerAbilities(ByteBuf buffer, PlayerAbilityHolder abilityHolder) {
      throw new UnsupportedOperationException();
   }

   public void readPlayerAbilities(ByteBuf buffer, PlayerAbilityHolder abilityHolder) {
      throw new UnsupportedOperationException();
   }
}
