package com.nukkitx.protocol.bedrock.codec;

import com.nukkitx.protocol.bedrock.data.EncodingSettings;
import com.nukkitx.protocol.bedrock.data.ExperimentData;
import com.nukkitx.protocol.bedrock.data.PlayerAbilityHolder;
import com.nukkitx.protocol.bedrock.data.definitions.BlockDefinition;
import com.nukkitx.protocol.bedrock.data.definitions.ItemDefinition;
import com.nukkitx.protocol.bedrock.data.entity.EntityProperties;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.InventoryActionData;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.InventorySource;
import com.nukkitx.protocol.bedrock.data.skin.AnimationData;
import com.nukkitx.protocol.bedrock.data.skin.ImageData;
import com.nukkitx.protocol.bedrock.data.skin.SerializedSkin;
import com.nukkitx.protocol.bedrock.data.structure.StructureSettings;
import com.nukkitx.protocol.bedrock.packet.InventoryTransactionPacket;
import com.nukkitx.protocol.common.DefinitionRegistry;
import com.nukkitx.protocol.common.NamedDefinition;
import com.nukkitx.protocol.common.util.Preconditions;
import com.nukkitx.protocol.common.util.TriConsumer;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.ToLongFunction;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

public abstract class BaseBedrockCodecHelper implements BedrockCodecHelper {
   protected static final InternalLogger log = InternalLoggerFactory.getInstance(BaseBedrockCodecHelper.class);
   protected final EntityDataTypeMap entityData;
   protected final TypeMap<Class<?>> gameRuleType;
   protected DefinitionRegistry<ItemDefinition> itemDefinitions;
   protected DefinitionRegistry<BlockDefinition> blockDefinitions;
   protected EncodingSettings encodingSettings;

   protected static boolean isAir(ItemDefinition definition) {
      return definition == null || "minecraft:air".equals(definition.getIdentifier());
   }

   public byte[] readByteArray(ByteBuf buffer) {
      return this.readByteArray(buffer, this.encodingSettings.maxByteArraySize());
   }

   public byte[] readByteArray(ByteBuf buffer, int maxLength) {
      int length = VarInts.readUnsignedInt(buffer);
      Preconditions.checkArgument(buffer.isReadable(length), "Tried to read %s bytes but only has %s readable", length, buffer.readableBytes());
      Preconditions.checkArgument(maxLength <= 0 || length <= maxLength, "Tried to read %s bytes but maximum is %s", length, maxLength);
      byte[] bytes = new byte[length];
      buffer.readBytes(bytes);
      return bytes;
   }

   public void writeByteArray(ByteBuf buffer, byte[] bytes) {
      Preconditions.checkNotNull(bytes, "bytes");
      VarInts.writeUnsignedInt(buffer, bytes.length);
      buffer.writeBytes(bytes);
   }

   public ByteBuf readByteBuf(ByteBuf buffer) {
      int length = VarInts.readUnsignedInt(buffer);
      return buffer.readRetainedSlice(length);
   }

   public void writeByteBuf(ByteBuf buffer, ByteBuf toWrite) {
      Preconditions.checkNotNull(toWrite, "toWrite");
      VarInts.writeUnsignedInt(buffer, toWrite.readableBytes());
      buffer.writeBytes(toWrite, toWrite.readerIndex(), toWrite.writerIndex());
   }

   public String readString(ByteBuf buffer) {
      int length = VarInts.readUnsignedInt(buffer);
      Preconditions.checkArgument(this.encodingSettings.maxStringLength() <= 0 || length <= this.encodingSettings.maxStringLength(), "Tried to read %s bytes but maximum is %s", length, this.encodingSettings.maxStringLength());
      return (String)buffer.readCharSequence(length, StandardCharsets.UTF_8);
   }

   public void writeString(ByteBuf buffer, String string) {
      Preconditions.checkNotNull(string, "string");
      VarInts.writeUnsignedInt(buffer, ByteBufUtil.utf8Bytes(string));
      buffer.writeCharSequence(string, StandardCharsets.UTF_8);
   }

   public UUID readUuid(ByteBuf buffer) {
      return new UUID(buffer.readLongLE(), buffer.readLongLE());
   }

   public void writeUuid(ByteBuf buffer, UUID uuid) {
      Preconditions.checkNotNull(uuid, "uuid");
      buffer.writeLongLE(uuid.getMostSignificantBits());
      buffer.writeLongLE(uuid.getLeastSignificantBits());
   }

   public Vector3f readVector3f(ByteBuf buffer) {
      float x = buffer.readFloatLE();
      float y = buffer.readFloatLE();
      float z = buffer.readFloatLE();
      return Vector3f.from(x, y, z);
   }

   public void writeVector3f(ByteBuf buffer, Vector3f vector3f) {
      Preconditions.checkNotNull(vector3f, "vector3f");
      buffer.writeFloatLE(vector3f.getX());
      buffer.writeFloatLE(vector3f.getY());
      buffer.writeFloatLE(vector3f.getZ());
   }

   public Vector2f readVector2f(ByteBuf buffer) {
      float x = buffer.readFloatLE();
      float y = buffer.readFloatLE();
      return Vector2f.from(x, y);
   }

   public void writeVector2f(ByteBuf buffer, Vector2f vector2f) {
      Preconditions.checkNotNull(vector2f, "vector2f");
      buffer.writeFloatLE(vector2f.getX());
      buffer.writeFloatLE(vector2f.getY());
   }

   public Vector3i readVector3i(ByteBuf buffer) {
      int x = VarInts.readInt(buffer);
      int y = VarInts.readInt(buffer);
      int z = VarInts.readInt(buffer);
      return Vector3i.from(x, y, z);
   }

   public void writeVector3i(ByteBuf buffer, Vector3i vector3i) {
      Preconditions.checkNotNull(vector3i, "vector3i");
      VarInts.writeInt(buffer, vector3i.getX());
      VarInts.writeInt(buffer, vector3i.getY());
      VarInts.writeInt(buffer, vector3i.getZ());
   }

   public float readByteAngle(ByteBuf buffer) {
      return (float)buffer.readByte() * 1.40625F;
   }

   public void writeByteAngle(ByteBuf buffer, float angle) {
      buffer.writeByte((byte)((int)(angle / 1.40625F)));
   }

   public Vector3i readBlockPosition(ByteBuf buffer) {
      int x = VarInts.readInt(buffer);
      int y = VarInts.readUnsignedInt(buffer);
      int z = VarInts.readInt(buffer);
      return Vector3i.from(x, y, z);
   }

   public void writeBlockPosition(ByteBuf buffer, Vector3i blockPosition) {
      Preconditions.checkNotNull(blockPosition, "blockPosition");
      VarInts.writeInt(buffer, blockPosition.getX());
      VarInts.writeUnsignedInt(buffer, blockPosition.getY());
      VarInts.writeInt(buffer, blockPosition.getZ());
   }

   public <T> void readArray(ByteBuf buffer, Collection<T> array, BiFunction<ByteBuf, BedrockCodecHelper, T> function) {
      this.readArray(buffer, array, function, this.encodingSettings.maxListSize());
   }

   public <T> void readArray(ByteBuf buffer, Collection<T> array, ToLongFunction<ByteBuf> lengthReader, BiFunction<ByteBuf, BedrockCodecHelper, T> function) {
      this.readArray(buffer, array, lengthReader, function, this.encodingSettings.maxListSize());
   }

   public <T> void readArray(ByteBuf buffer, Collection<T> array, ToLongFunction<ByteBuf> lengthReader, BiFunction<ByteBuf, BedrockCodecHelper, T> function, int maxLength) {
      long length = lengthReader.applyAsLong(buffer);
      Preconditions.checkArgument(maxLength <= 0 || length <= (long)maxLength, "Tried to read %s bytes but maximum is %s", length, maxLength);

      for(int i = 0; (long)i < length; ++i) {
         array.add(function.apply(buffer, this));
      }

   }

   public <T> void writeArray(ByteBuf buffer, Collection<T> array, ObjIntConsumer<ByteBuf> lengthWriter, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer) {
      lengthWriter.accept(buffer, array.size());

      for(T val : array) {
         consumer.accept(buffer, this, val);
      }

   }

   public <T> T[] readArray(ByteBuf buffer, T[] array, BiFunction<ByteBuf, BedrockCodecHelper, T> function) {
      return (T[])this.readArray(buffer, array, function, this.encodingSettings.maxListSize());
   }

   public <T> T[] readArray(ByteBuf buffer, T[] array, BiFunction<ByteBuf, BedrockCodecHelper, T> function, int maxLength) {
      ObjectArrayList<T> list = new ObjectArrayList();
      this.readArray(buffer, list, function, maxLength);
      return (T[])list.toArray(array);
   }

   public <T> void writeArray(ByteBuf buffer, T[] array, TriConsumer<ByteBuf, BedrockCodecHelper, T> consumer) {
      VarInts.writeUnsignedInt(buffer, array.length);

      for(T val : array) {
         consumer.accept(buffer, this, val);
      }

   }

   public <T> void readArray(ByteBuf buffer, Collection<T> array, Function<ByteBuf, T> function) {
      this.readArray(buffer, array, function, this.encodingSettings.maxListSize());
   }

   public <T> void readArray(ByteBuf buffer, Collection<T> array, Function<ByteBuf, T> function, int maxLength) {
      this.readArray(buffer, array, VarInts::readUnsignedInt, function, maxLength);
   }

   public <T> void readArray(ByteBuf buffer, Collection<T> array, ToLongFunction<ByteBuf> lengthReader, Function<ByteBuf, T> function) {
      this.readArray(buffer, array, lengthReader, function, this.encodingSettings.maxListSize());
   }

   public <T> void readArray(ByteBuf buffer, Collection<T> array, ToLongFunction<ByteBuf> lengthReader, Function<ByteBuf, T> function, int maxLength) {
      long length = lengthReader.applyAsLong(buffer);
      Preconditions.checkArgument(maxLength <= 0 || length <= (long)maxLength, "Tried to read %s bytes but maximum is %s", length, maxLength);

      for(int i = 0; (long)i < length; ++i) {
         array.add(function.apply(buffer));
      }

   }

   public <T> void writeArray(ByteBuf buffer, Collection<T> array, BiConsumer<ByteBuf, T> biConsumer) {
      this.writeArray(buffer, array, VarInts::writeUnsignedInt, biConsumer);
   }

   public <T> void writeArray(ByteBuf buffer, Collection<T> array, ObjIntConsumer<ByteBuf> lengthWriter, BiConsumer<ByteBuf, T> consumer) {
      lengthWriter.accept(buffer, array.size());

      for(T val : array) {
         consumer.accept(buffer, val);
      }

   }

   public <T> T[] readArray(ByteBuf buffer, T[] array, Function<ByteBuf, T> function) {
      return (T[])this.readArray(buffer, array, function, this.encodingSettings.maxListSize());
   }

   public <T> T[] readArray(ByteBuf buffer, T[] array, Function<ByteBuf, T> function, int maxLength) {
      ObjectArrayList<T> list = new ObjectArrayList();
      this.readArray(buffer, list, function, maxLength);
      return (T[])list.toArray(array);
   }

   public <T> void writeArray(ByteBuf buffer, T[] array, BiConsumer<ByteBuf, T> biConsumer) {
      VarInts.writeUnsignedInt(buffer, array.length);

      for(T val : array) {
         biConsumer.accept(buffer, val);
      }

   }

   public <T> T readTag(ByteBuf buffer, Class<T> expected) {
      return (T)this.readTag(buffer, expected, (long)this.encodingSettings.maxNetworkNBTSize());
   }

   public <T> T readTag(ByteBuf buffer, Class<T> expected, long maxReadSize) {
      try {
         NBTInputStream reader = NbtUtils.createNetworkReader(new ByteBufInputStream(buffer), maxReadSize);

         Object var7;
         try {
            Object tag = reader.readTag();
            Preconditions.checkArgument(expected.isInstance(tag), "Expected tag of %s type but received %s", expected, tag.getClass());
            var7 = tag;
         } catch (Throwable var9) {
            if (reader != null) {
               try {
                  reader.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }
            }

            throw var9;
         }

         if (reader != null) {
            reader.close();
         }

         return (T)var7;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public void writeTag(ByteBuf buffer, Object tag) {
      try {
         NBTOutputStream writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(buffer));

         try {
            writer.writeTag(tag);
         } catch (Throwable var7) {
            if (writer != null) {
               try {
                  writer.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (writer != null) {
            writer.close();
         }

      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public <T> T readTagLE(ByteBuf buffer, Class<T> expected) {
      return (T)this.readTagLE(buffer, expected, (long)this.encodingSettings.maxNetworkNBTSize());
   }

   public <T> T readTagLE(ByteBuf buffer, Class<T> expected, long maxReadSize) {
      try {
         NBTInputStream reader = NbtUtils.createReaderLE(new ByteBufInputStream(buffer), maxReadSize);

         Object var7;
         try {
            Object tag = reader.readTag();
            Preconditions.checkArgument(expected.isInstance(tag), "Expected tag of %s type but received %s", expected, tag.getClass());
            var7 = reader.readTag();
         } catch (Throwable var9) {
            if (reader != null) {
               try {
                  reader.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }
            }

            throw var9;
         }

         if (reader != null) {
            reader.close();
         }

         return (T)var7;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public void writeTagLE(ByteBuf buffer, Object tag) {
      try {
         NBTOutputStream writer = NbtUtils.createWriterLE(new ByteBufOutputStream(buffer));

         try {
            writer.writeTag(tag);
         } catch (Throwable var7) {
            if (writer != null) {
               try {
                  writer.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (writer != null) {
            writer.close();
         }

      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public <T> T readTagValue(ByteBuf buffer, NbtType<T> type) {
      return (T)this.readTagValue(buffer, type, (long)this.encodingSettings.maxNetworkNBTSize());
   }

   public <T> T readTagValue(ByteBuf buffer, NbtType<T> type, long maxReadSize) {
      try {
         NBTInputStream reader = NbtUtils.createNetworkReader(new ByteBufInputStream(buffer), maxReadSize);

         Object var6;
         try {
            var6 = reader.readValue(type);
         } catch (Throwable var9) {
            if (reader != null) {
               try {
                  reader.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }
            }

            throw var9;
         }

         if (reader != null) {
            reader.close();
         }

         return (T)var6;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public void writeTagValue(ByteBuf buffer, Object tag) {
      try {
         NBTOutputStream writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(buffer));

         try {
            writer.writeValue(tag);
         } catch (Throwable var7) {
            if (writer != null) {
               try {
                  writer.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (writer != null) {
            writer.close();
         }

      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public void readItemUse(ByteBuf buffer, InventoryTransactionPacket packet) {
      packet.setActionType(VarInts.readUnsignedInt(buffer));
      packet.setBlockPosition(this.readBlockPosition(buffer));
      packet.setBlockFace(VarInts.readInt(buffer));
      packet.setHotbarSlot(VarInts.readInt(buffer));
      packet.setItemInHand(this.readItem(buffer));
      packet.setPlayerPosition(this.readVector3f(buffer));
      packet.setClickPosition(this.readVector3f(buffer));
   }

   public void writeItemUse(ByteBuf buffer, InventoryTransactionPacket packet) {
      VarInts.writeUnsignedInt(buffer, packet.getActionType());
      this.writeBlockPosition(buffer, packet.getBlockPosition());
      VarInts.writeInt(buffer, packet.getBlockFace());
      VarInts.writeInt(buffer, packet.getHotbarSlot());
      this.writeItem(buffer, packet.getItemInHand());
      this.writeVector3f(buffer, packet.getPlayerPosition());
      this.writeVector3f(buffer, packet.getClickPosition());
   }

   @Override
   public boolean readInventoryActions(ByteBuf buffer, List<InventoryActionData> actions) {
      this.readArray(buffer, actions, (buf, helper) -> {
         InventorySource source = this.readSource(buf);
         int slot = VarInts.readUnsignedInt(buf);
         ItemData fromItem = helper.readItem(buf);
         ItemData toItem = helper.readItem(buf);

         return new InventoryActionData(source, slot, fromItem, toItem);
      });
      return false;
   }

   @Override
   public void writeInventoryActions(ByteBuf buffer, List<InventoryActionData> actions, boolean hasNetworkIds) {
      this.writeArray(buffer, actions, (buf, helper, action) -> {
         this.writeSource(buf, action.getSource());
         VarInts.writeUnsignedInt(buf, action.getSlot());
         helper.writeItem(buf, action.getFromItem());
         helper.writeItem(buf, action.getToItem());
      });
   }

   protected InventorySource readSource(ByteBuf buffer) {
      InventorySource.Type type = InventorySource.Type.byId(VarInts.readUnsignedInt(buffer));
      int containerId;
      switch (type) {
         case CONTAINER:
            containerId = VarInts.readInt(buffer);
            return InventorySource.fromContainerWindowId(containerId);
         case GLOBAL:
            return InventorySource.fromGlobalInventory();
         case WORLD_INTERACTION:
            InventorySource.Flag flag = InventorySource.Flag.values()[VarInts.readUnsignedInt(buffer)];
            return InventorySource.fromWorldInteraction(flag);
         case CREATIVE:
            return InventorySource.fromCreativeInventory();
         case NON_IMPLEMENTED_TODO:
            containerId = VarInts.readInt(buffer);
            return InventorySource.fromNonImplementedTodo(containerId);
         default:
            return InventorySource.fromInvalid();
      }
   }

   protected void writeSource(ByteBuf buffer, InventorySource inventorySource) {
      Objects.requireNonNull(inventorySource, "InventorySource was null");
      VarInts.writeUnsignedInt(buffer, inventorySource.getType().id());
      switch (inventorySource.getType()) {
         case CONTAINER:
         case NON_IMPLEMENTED_TODO:
         case UNTRACKED_INTERACTION_UI:
            VarInts.writeInt(buffer, inventorySource.getContainerId());
         case GLOBAL:
         case CREATIVE:
         default:
            break;
         case WORLD_INTERACTION:
            VarInts.writeUnsignedInt(buffer, inventorySource.getFlag().ordinal());
      }

   }

   public void readExperiments(ByteBuf buffer, List<ExperimentData> experiments) {
      throw new UnsupportedOperationException();
   }

   public void writeExperiments(ByteBuf buffer, List<ExperimentData> experiments) {
      throw new UnsupportedOperationException();
   }

   public ItemStackRequest readItemStackRequest(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   public void writeItemStackRequest(ByteBuf buffer, ItemStackRequest request) {
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

   public AnimationData readAnimationData(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   protected void writeAnimationData(ByteBuf buffer, AnimationData animation) {
      throw new UnsupportedOperationException();
   }

   protected ImageData readImage(ByteBuf buffer) {
      return this.readImage(buffer, 262144);
   }

   protected ImageData readImage(ByteBuf buffer, int maxSize) {
      throw new UnsupportedOperationException();
   }

   protected void writeImage(ByteBuf buffer, ImageData image) {
      throw new UnsupportedOperationException();
   }

   public void readEntityProperties(ByteBuf buffer, EntityProperties properties) {
      throw new UnsupportedOperationException();
   }

   public void writeEntityProperties(ByteBuf buffer, EntityProperties properties) {
      throw new UnsupportedOperationException();
   }

   public ItemDescriptorWithCount readIngredient(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   public void writeIngredient(ByteBuf buffer, ItemDescriptorWithCount ingredient) {
      throw new UnsupportedOperationException();
   }

   public ContainerSlotType readContainerSlotType(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   public void writeContainerSlotType(ByteBuf buffer, ContainerSlotType slotType) {
      throw new UnsupportedOperationException();
   }

   public void writePlayerAbilities(ByteBuf buffer, PlayerAbilityHolder abilityHolder) {
      throw new UnsupportedOperationException();
   }

   public void readPlayerAbilities(ByteBuf buffer, PlayerAbilityHolder abilityHolder) {
      throw new UnsupportedOperationException();
   }

   public DefinitionRegistry<NamedDefinition> getCameraPresetDefinitions() {
      throw new UnsupportedOperationException();
   }

   public void setCameraPresetDefinitions(DefinitionRegistry<NamedDefinition> registry) {
      throw new UnsupportedOperationException();
   }

   protected BaseBedrockCodecHelper(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRuleType) {
      this.encodingSettings = EncodingSettings.DEFAULT;
      this.entityData = entityData;
      this.gameRuleType = gameRuleType;
   }

   public DefinitionRegistry<ItemDefinition> getItemDefinitions() {
      return this.itemDefinitions;
   }

   public void setItemDefinitions(DefinitionRegistry<ItemDefinition> itemDefinitions) {
      this.itemDefinitions = itemDefinitions;
   }

   public DefinitionRegistry<BlockDefinition> getBlockDefinitions() {
      return this.blockDefinitions;
   }

   public void setBlockDefinitions(DefinitionRegistry<BlockDefinition> blockDefinitions) {
      this.blockDefinitions = blockDefinitions;
   }

   public EncodingSettings getEncodingSettings() {
      return this.encodingSettings;
   }

   public void setEncodingSettings(EncodingSettings encodingSettings) {
      this.encodingSettings = encodingSettings;
   }
}
