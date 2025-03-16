package com.nukkitx.protocol.bedrock.codec.v291;

import com.nukkitx.protocol.bedrock.codec.BaseBedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.data.GameRuleData;
import com.nukkitx.protocol.bedrock.data.command.CommandEnumConstraint;
import com.nukkitx.protocol.bedrock.data.command.CommandEnumData;
import com.nukkitx.protocol.bedrock.data.command.CommandOriginData;
import com.nukkitx.protocol.bedrock.data.command.CommandOriginType;
import com.nukkitx.protocol.bedrock.data.definitions.ItemDefinition;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataFormat;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataMap;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataType;
import com.nukkitx.protocol.bedrock.data.entity.EntityLinkData;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.transformer.EntityDataTransformer;
import com.nukkitx.protocol.common.util.Preconditions;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import com.nukkitx.protocol.common.util.stream.LittleEndianByteBufOutputStream;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

public class BedrockCodecHelper_v291 extends BaseBedrockCodecHelper {
   public BedrockCodecHelper_v291(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes) {
      super(entityData, gameRulesTypes);
   }

   public EntityLinkData readEntityLink(ByteBuf buffer) {
      long from = VarInts.readLong(buffer);
      long to = VarInts.readLong(buffer);
      int type = buffer.readUnsignedByte();
      boolean immediate = buffer.readBoolean();
      return new EntityLinkData(from, to, EntityLinkData.Type.values()[type], immediate);
   }

   public void writeEntityLink(ByteBuf buffer, EntityLinkData entityLink) {
      Preconditions.checkNotNull(entityLink, "entityLink");
      VarInts.writeLong(buffer, entityLink.getFrom());
      VarInts.writeLong(buffer, entityLink.getTo());
      buffer.writeByte(entityLink.getType().ordinal());
      buffer.writeBoolean(entityLink.isImmediate());
   }

   public ItemData readNetItem(ByteBuf buffer) {
      throw new UnsupportedOperationException();
   }

   public void writeNetItem(ByteBuf buffer, ItemData item) {
      throw new UnsupportedOperationException();
   }

   public ItemData readItem(ByteBuf buffer) {
      int runtimeId = VarInts.readInt(buffer);
      if (runtimeId == 0) {
         return ItemData.AIR;
      } else {
         ItemDefinition definition = this.itemDefinitions.getDefinition(runtimeId);
         int aux = VarInts.readInt(buffer);
         int damage = (short)(aux >> 8);
         if (damage == 32767) {
            damage = -1;
         }

         int count = aux & 255;
         short nbtSize = buffer.readShortLE();
         NbtMap compoundTag = null;
         if (nbtSize > 0) {
            try {
               NBTInputStream reader = NbtUtils.createReaderLE(new ByteBufInputStream(buffer.readSlice(nbtSize)), (long)this.encodingSettings.maxItemNBTSize());

               try {
                  Object tag = reader.readTag();
                  if (tag instanceof NbtMap) {
                     compoundTag = (NbtMap)tag;
                  }
               } catch (Throwable var13) {
                  if (reader != null) {
                     try {
                        reader.close();
                     } catch (Throwable var12) {
                        var13.addSuppressed(var12);
                     }
                  }

                  throw var13;
               }

               if (reader != null) {
                  reader.close();
               }
            } catch (IOException e) {
               throw new IllegalStateException("Unable to load NBT data", e);
            }
         }

         String[] canPlace = (String[])this.readArray(buffer, new String[0], this::readString);
         String[] canBreak = (String[])this.readArray(buffer, new String[0], this::readString);
         return ItemData.builder().definition(definition).damage(damage).count(count).tag(compoundTag).canPlace(canPlace).canBreak(canBreak).build();
      }
   }

   public void writeItem(ByteBuf buffer, ItemData item) {
      Preconditions.checkNotNull(item, "item");
      ItemDefinition definition = item.getDefinition();
      if (isAir(definition)) {
         buffer.writeByte(0);
      } else {
         VarInts.writeInt(buffer, definition.getRuntimeId());
         int damage = item.getDamage();
         if (damage == -1) {
            damage = 32767;
         }

         VarInts.writeInt(buffer, damage << 8 | item.getCount() & 255);
         int sizeIndex = buffer.writerIndex();
         buffer.writeShortLE(0);
         if (item.getTag() != null) {
            int afterSizeIndex = buffer.writerIndex();

            try {
               NBTOutputStream stream = new NBTOutputStream(new LittleEndianByteBufOutputStream(buffer));

               try {
                  stream.writeTag(item.getTag());
               } catch (Throwable var11) {
                  try {
                     stream.close();
                  } catch (Throwable var10) {
                     var11.addSuppressed(var10);
                  }

                  throw var11;
               }

               stream.close();
            } catch (IOException e) {
               throw new IllegalStateException("Unable to save NBT data", e);
            }

            buffer.setShortLE(sizeIndex, buffer.writerIndex() - afterSizeIndex);
         }

         this.writeArray(buffer, item.getCanPlace(), this::writeString);
         this.writeArray(buffer, item.getCanBreak(), this::writeString);
      }
   }

   public ItemData readItemInstance(ByteBuf buffer) {
      return this.readItem(buffer);
   }

   public void writeItemInstance(ByteBuf buffer, ItemData item) {
      this.writeItem(buffer, item);
   }

   public CommandOriginData readCommandOrigin(ByteBuf buffer) {
      CommandOriginType origin = CommandOriginType.values()[VarInts.readUnsignedInt(buffer)];
      UUID uuid = this.readUuid(buffer);
      String requestId = this.readString(buffer);
      long varLong = -1L;
      if (origin == CommandOriginType.DEV_CONSOLE || origin == CommandOriginType.TEST) {
         varLong = VarInts.readLong(buffer);
      }

      return new CommandOriginData(origin, uuid, requestId, varLong);
   }

   public void writeCommandOrigin(ByteBuf buffer, CommandOriginData originData) {
      Preconditions.checkNotNull(originData, "commandOriginData");
      VarInts.writeUnsignedInt(buffer, originData.getOrigin().ordinal());
      this.writeUuid(buffer, originData.getUuid());
      this.writeString(buffer, originData.getRequestId());
      if (originData.getOrigin() == CommandOriginType.DEV_CONSOLE || originData.getOrigin() == CommandOriginType.TEST) {
         VarInts.writeLong(buffer, originData.getEvent());
      }

   }

   public GameRuleData<?> readGameRule(ByteBuf buffer) {
      String name = this.readString(buffer);
      int type = VarInts.readUnsignedInt(buffer);
      switch (type) {
         case 1:
            return new GameRuleData(name, buffer.readBoolean());
         case 2:
            return new GameRuleData(name, VarInts.readUnsignedInt(buffer));
         case 3:
            return new GameRuleData(name, buffer.readFloatLE());
         default:
            throw new IllegalStateException("Invalid gamerule type received");
      }
   }

   public void writeGameRule(ByteBuf buffer, GameRuleData<?> gameRule) {
      Preconditions.checkNotNull(gameRule, "gameRule");
      Object value = gameRule.getValue();
      int type = this.gameRuleType.getId(value.getClass());
      this.writeString(buffer, gameRule.getName());
      VarInts.writeUnsignedInt(buffer, type);
      switch (type) {
         case 1:
            buffer.writeBoolean((Boolean)value);
            break;
         case 2:
            VarInts.writeUnsignedInt(buffer, (Integer)value);
            break;
         case 3:
            buffer.writeFloatLE((Float)value);
      }

   }

   public void readEntityData(ByteBuf buffer, EntityDataMap entityDataMap) {
      Preconditions.checkNotNull(entityDataMap, "entityDataDictionary");
      int length = VarInts.readUnsignedInt(buffer);
      Preconditions.checkArgument(length <= this.encodingSettings.maxListSize(), "Entity data size is too big: {}", length);

      for(int i = 0; i < length; ++i) {
         int id = VarInts.readUnsignedInt(buffer);
         EntityDataFormat format = EntityDataFormat.values()[VarInts.readUnsignedInt(buffer)];
         Object value;
         switch (format) {
            case BYTE:
               value = buffer.readByte();
               break;
            case SHORT:
               value = buffer.readShortLE();
               break;
            case INT:
               value = VarInts.readInt(buffer);
               break;
            case FLOAT:
               value = buffer.readFloatLE();
               break;
            case STRING:
               value = this.readString(buffer);
               break;
            case NBT:
               value = this.readItem(buffer).getTag();
               break;
            case VECTOR3I:
               value = this.readVector3i(buffer);
               break;
            case LONG:
               value = VarInts.readLong(buffer);
               break;
            case VECTOR3F:
               value = this.readVector3f(buffer);
               break;
            default:
               throw new UnsupportedOperationException("Unknown entity data type received");
         }

         EntityDataTypeMap.Definition<?>[] definitions = this.entityData.fromId(id, format);
         if (definitions != null) {
            for(EntityDataTypeMap.Definition<?> definition : definitions) {
               EntityDataTransformer<Object, ?> transformer = (EntityDataTransformer<Object, ?>) definition.getTransformer();
               Object transformedValue = transformer.deserialize(this, entityDataMap, value);
               if (transformedValue != null) {
                  entityDataMap.put(definition.getType(), transformer.deserialize(this, entityDataMap, value));
               }
            }
         } else {
            log.debug("Unknown entity data: {} type {} value {}", new Object[]{id, format, value});
         }
      }

   }

   public void writeEntityData(ByteBuf buffer, EntityDataMap entityDataMap) {
      Preconditions.checkNotNull(entityDataMap, "entityDataDictionary");
      VarInts.writeUnsignedInt(buffer, entityDataMap.size());

      for(Map.Entry<EntityDataType<?>, Object> entry : entityDataMap.entrySet()) {
         EntityDataTypeMap.Definition<?> definition = this.entityData.fromType((EntityDataType)entry.getKey());
         VarInts.writeUnsignedInt(buffer, definition.getId());
         VarInts.writeUnsignedInt(buffer, definition.getFormat().ordinal());

         try {
            Object value = ((EntityDataTransformer<?, Object>) definition.getTransformer())
                    .serialize(this, entityDataMap, entry.getValue());
            switch (definition.getFormat()) {
               case BYTE:
                  buffer.writeByte((Byte)value);
                  break;
               case SHORT:
                  buffer.writeShortLE((Short)value);
                  break;
               case INT:
                  VarInts.writeInt(buffer, (Integer)value);
                  break;
               case FLOAT:
                  buffer.writeFloatLE((Float)value);
                  break;
               case STRING:
                  this.writeString(buffer, (String)value);
                  break;
               case NBT:
                  this.writeItem(buffer, ItemData.builder().definition(ItemDefinition.LEGACY_FIREWORK).damage(0).count(1).tag((NbtMap)value).build());
                  break;
               case VECTOR3I:
                  this.writeVector3i(buffer, (Vector3i)value);
                  break;
               case LONG:
                  VarInts.writeLong(buffer, (Long)value);
                  break;
               case VECTOR3F:
                  this.writeVector3f(buffer, (Vector3f)value);
                  break;
               default:
                  throw new UnsupportedOperationException("Unknown entity data type " + definition.getFormat());
            }
         } catch (Exception e) {
            throw new IllegalArgumentException("Failed to encode EntityData " + definition.getId() + " of " + definition.getType().getTypeName(), e);
         }
      }

   }

   public CommandEnumData readCommandEnum(ByteBuf buffer, boolean soft) {
      String name = this.readString(buffer);
      int count = VarInts.readUnsignedInt(buffer);
      LinkedHashMap<String, Set<CommandEnumConstraint>> values = new LinkedHashMap();

      for(int i = 0; i < count; ++i) {
         values.put(this.readString(buffer), Collections.emptySet());
      }

      return new CommandEnumData(name, values, soft);
   }

   public void writeCommandEnum(ByteBuf buffer, CommandEnumData enumData) {
      Preconditions.checkNotNull(enumData, "enumData");
      this.writeString(buffer, enumData.getName());
      Set<String> values = enumData.getValues().keySet();
      VarInts.writeUnsignedInt(buffer, values.size());

      for(String value : values) {
         this.writeString(buffer, value);
      }

   }

   public <O> O readOptional(ByteBuf buffer, O emptyValue, Function<ByteBuf, O> function) {
      return (O)(buffer.readBoolean() ? function.apply(buffer) : emptyValue);
   }

   public <T> void writeOptional(ByteBuf buffer, Predicate<T> isPresent, T object, BiConsumer<ByteBuf, T> consumer) {
      Preconditions.checkNotNull(consumer, "read consumer");
      boolean exists = isPresent.test(object);
      buffer.writeBoolean(exists);
      if (exists) {
         consumer.accept(buffer, object);
      }

   }

   public <T> void writeOptionalNull(ByteBuf buffer, T object, BiConsumer<ByteBuf, T> consumer) {
      this.writeOptional(buffer, Objects::nonNull, object, consumer);
   }
}
