package com.nukkitx.protocol.bedrock.codec;

import com.nukkitx.protocol.bedrock.data.EncodingSettings;
import com.nukkitx.protocol.bedrock.data.ExperimentData;
import com.nukkitx.protocol.bedrock.data.GameRuleData;
import com.nukkitx.protocol.bedrock.data.PlayerAbilityHolder;
import com.nukkitx.protocol.bedrock.data.command.CommandEnumData;
import com.nukkitx.protocol.bedrock.data.command.CommandOriginData;
import com.nukkitx.protocol.bedrock.data.definitions.BlockDefinition;
import com.nukkitx.protocol.bedrock.data.definitions.ItemDefinition;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataMap;
import com.nukkitx.protocol.bedrock.data.entity.EntityLinkData;
import com.nukkitx.protocol.bedrock.data.entity.EntityProperties;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.InventoryActionData;
import com.nukkitx.protocol.bedrock.data.skin.SerializedSkin;
import com.nukkitx.protocol.bedrock.data.structure.StructureSettings;
import com.nukkitx.protocol.bedrock.packet.InventoryTransactionPacket;
import com.nukkitx.protocol.common.DefinitionRegistry;
import com.nukkitx.protocol.common.NamedDefinition;
import com.nukkitx.protocol.common.util.TriConsumer;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtType;

public interface BedrockCodecHelper {
   void setItemDefinitions(DefinitionRegistry<ItemDefinition> var1);

   void setBlockDefinitions(DefinitionRegistry<BlockDefinition> var1);

   void setCameraPresetDefinitions(DefinitionRegistry<NamedDefinition> var1);

   DefinitionRegistry<ItemDefinition> getItemDefinitions();

   DefinitionRegistry<BlockDefinition> getBlockDefinitions();

   DefinitionRegistry<NamedDefinition> getCameraPresetDefinitions();

   EncodingSettings getEncodingSettings();

   void setEncodingSettings(EncodingSettings var1);

   <T> void readArray(ByteBuf var1, Collection<T> var2, BiFunction<ByteBuf, BedrockCodecHelper, T> var3);

   default <T> void readArray(ByteBuf buffer, Collection<T> array, BiFunction<ByteBuf, BedrockCodecHelper, T> function, int maxLength) {
      this.readArray(buffer, array, VarInts::readUnsignedInt, function, maxLength);
   }

   <T> void readArray(ByteBuf var1, Collection<T> var2, ToLongFunction<ByteBuf> var3, BiFunction<ByteBuf, BedrockCodecHelper, T> var4);

   <T> void readArray(ByteBuf var1, Collection<T> var2, ToLongFunction<ByteBuf> var3, BiFunction<ByteBuf, BedrockCodecHelper, T> var4, int var5);

   default <T> void writeArray(ByteBuf buffer, Collection<T> array, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer) {
      this.writeArray(buffer, array, VarInts::writeUnsignedInt, consumer);
   }

   <T> void writeArray(ByteBuf var1, Collection<T> var2, ObjIntConsumer<ByteBuf> var3, TriConsumer<ByteBuf, BedrockCodecHelper, T> var4);

   <T> T[] readArray(ByteBuf var1, T[] var2, BiFunction<ByteBuf, BedrockCodecHelper, T> var3);

   <T> T[] readArray(ByteBuf var1, T[] var2, BiFunction<ByteBuf, BedrockCodecHelper, T> var3, int var4);

   <T> void writeArray(ByteBuf var1, T[] var2, TriConsumer<ByteBuf, BedrockCodecHelper, T> var3);

   <T> void readArray(ByteBuf var1, Collection<T> var2, Function<ByteBuf, T> var3);

   <T> void readArray(ByteBuf var1, Collection<T> var2, ToLongFunction<ByteBuf> var3, Function<ByteBuf, T> var4);

   <T> void readArray(ByteBuf var1, Collection<T> var2, ToLongFunction<ByteBuf> var3, Function<ByteBuf, T> var4, int var5);

   <T> void readArray(ByteBuf var1, Collection<T> var2, Function<ByteBuf, T> var3, int var4);

   <T> void writeArray(ByteBuf var1, Collection<T> var2, BiConsumer<ByteBuf, T> var3);

   <T> void writeArray(ByteBuf var1, Collection<T> var2, ObjIntConsumer<ByteBuf> var3, BiConsumer<ByteBuf, T> var4);

   <T> T[] readArray(ByteBuf var1, T[] var2, Function<ByteBuf, T> var3);

   <T> T[] readArray(ByteBuf var1, T[] var2, Function<ByteBuf, T> var3, int var4);

   <T> void writeArray(ByteBuf var1, T[] var2, BiConsumer<ByteBuf, T> var3);

   EntityLinkData readEntityLink(ByteBuf var1);

   void writeEntityLink(ByteBuf var1, EntityLinkData var2);

   ItemData readNetItem(ByteBuf var1);

   void writeNetItem(ByteBuf var1, ItemData var2);

   ItemData readItem(ByteBuf var1);

   void writeItem(ByteBuf var1, ItemData var2);

   ItemData readItemInstance(ByteBuf var1);

   void writeItemInstance(ByteBuf var1, ItemData var2);

   CommandOriginData readCommandOrigin(ByteBuf var1);

   void writeCommandOrigin(ByteBuf var1, CommandOriginData var2);

   GameRuleData<?> readGameRule(ByteBuf var1);

   void writeGameRule(ByteBuf var1, GameRuleData<?> var2);

   void readEntityData(ByteBuf var1, EntityDataMap var2);

   void writeEntityData(ByteBuf var1, EntityDataMap var2);

   CommandEnumData readCommandEnum(ByteBuf var1, boolean var2);

   void writeCommandEnum(ByteBuf var1, CommandEnumData var2);

   StructureSettings readStructureSettings(ByteBuf var1);

   void writeStructureSettings(ByteBuf var1, StructureSettings var2);

   SerializedSkin readSkin(ByteBuf var1);

   void writeSkin(ByteBuf var1, SerializedSkin var2);

   byte[] readByteArray(ByteBuf var1);

   byte[] readByteArray(ByteBuf var1, int var2);

   void writeByteArray(ByteBuf var1, byte[] var2);

   ByteBuf readByteBuf(ByteBuf var1);

   void writeByteBuf(ByteBuf var1, ByteBuf var2);

   String readString(ByteBuf var1);

   void writeString(ByteBuf var1, String var2);

   UUID readUuid(ByteBuf var1);

   void writeUuid(ByteBuf var1, UUID var2);

   Vector3f readVector3f(ByteBuf var1);

   void writeVector3f(ByteBuf var1, Vector3f var2);

   Vector2f readVector2f(ByteBuf var1);

   void writeVector2f(ByteBuf var1, Vector2f var2);

   Vector3i readVector3i(ByteBuf var1);

   void writeVector3i(ByteBuf var1, Vector3i var2);

   float readByteAngle(ByteBuf var1);

   void writeByteAngle(ByteBuf var1, float var2);

   Vector3i readBlockPosition(ByteBuf var1);

   void writeBlockPosition(ByteBuf var1, Vector3i var2);

   <T> T readTag(ByteBuf var1, Class<T> var2);

   <T> T readTag(ByteBuf var1, Class<T> var2, long var3);

   void writeTag(ByteBuf var1, Object var2);

   <T> T readTagLE(ByteBuf var1, Class<T> var2);

   <T> T readTagLE(ByteBuf var1, Class<T> var2, long var3);

   void writeTagLE(ByteBuf var1, Object var2);

   <T> T readTagValue(ByteBuf var1, NbtType<T> var2);

   <T> T readTagValue(ByteBuf var1, NbtType<T> var2, long var3);

   void writeTagValue(ByteBuf var1, Object var2);

   void readItemUse(ByteBuf var1, InventoryTransactionPacket var2);

   void writeItemUse(ByteBuf var1, InventoryTransactionPacket var2);

   boolean readInventoryActions(ByteBuf var1, List<InventoryActionData> var2);

   void writeInventoryActions(ByteBuf var1, List<InventoryActionData> var2, boolean var3);

   void readExperiments(ByteBuf var1, List<ExperimentData> var2);

   void writeExperiments(ByteBuf var1, List<ExperimentData> var2);

   ItemStackRequest readItemStackRequest(ByteBuf var1);

   void writeItemStackRequest(ByteBuf var1, ItemStackRequest var2);

   <O> O readOptional(ByteBuf var1, O var2, Function<ByteBuf, O> var3);

   <T> void writeOptional(ByteBuf var1, Predicate<T> var2, T var3, BiConsumer<ByteBuf, T> var4);

   <T> void writeOptionalNull(ByteBuf var1, T var2, BiConsumer<ByteBuf, T> var3);

   void readEntityProperties(ByteBuf var1, EntityProperties var2);

   void writeEntityProperties(ByteBuf var1, EntityProperties var2);

   ItemDescriptorWithCount readIngredient(ByteBuf var1);

   void writeIngredient(ByteBuf var1, ItemDescriptorWithCount var2);

   void writeContainerSlotType(ByteBuf var1, ContainerSlotType var2);

   ContainerSlotType readContainerSlotType(ByteBuf var1);

   void writePlayerAbilities(ByteBuf var1, PlayerAbilityHolder var2);

   void readPlayerAbilities(ByteBuf var1, PlayerAbilityHolder var2);
}
