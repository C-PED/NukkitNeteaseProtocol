package com.nukkitx.protocol.bedrock.codec;

import com.nukkitx.protocol.bedrock.data.entity.EntityDataFormat;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataType;
import com.nukkitx.protocol.bedrock.transformer.EntityDataTransformer;
import com.nukkitx.protocol.common.util.Preconditions;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.function.Consumer;

public class EntityDataTypeMap {
   private static final EntityDataFormat[] FORMATS = EntityDataFormat.values();
   private final Definition<?>[][][] idDefinitions;
   private final Map<EntityDataType<?>, Definition<?>> typeDefinitionMap;

   public Definition<?>[] fromId(int id, EntityDataFormat format) {
      if (id >= 0 && id < this.idDefinitions.length && format != null) {
         Definition<?>[][] definitions = this.idDefinitions[id];
         int formatId = format.ordinal();
         if (definitions != null && formatId < definitions.length) {
            return definitions[formatId];
         }
      }

      return null;
   }

   public <T> Definition<T> fromType(EntityDataType<T> type) {
      return (Definition)this.typeDefinitionMap.get(type);
   }

   public Builder toBuilder() {
      Builder builder = new Builder(copy(this.idDefinitions));
      this.typeDefinitionMap.forEach((type, def) -> builder.typeDefinitionMap.put(type, def.copy()));
      return builder;
   }

   public static Builder builder() {
      return new Builder(new Definition[64][][]);
   }

   public String prettyPrint() {
      TreeMap<Integer, EntityDataType<?>> map = new TreeMap();
      this.typeDefinitionMap.forEach((type, def) -> map.put(def.id, type));
      StringJoiner joiner = new StringJoiner("\n");

      for(Map.Entry<Integer, EntityDataType<?>> entry : map.entrySet()) {
         joiner.add(entry.getKey() + " => " + entry.getValue());
      }

      return joiner.toString();
   }

   private static int powerOfTwoCeiling(int value) {
      --value;
      value |= value >> 1;
      value |= value >> 2;
      value |= value >> 4;
      value |= value >> 8;
      value |= value >> 16;
      ++value;
      return value;
   }

   private static Definition<?>[][][] copy(Definition<?>[][][] array) {
      Definition<?>[][][] copy = new Definition[lastNonNullIndex(array) + 1][][];

      for(int i = 0; i < copy.length; ++i) {
         if (array[i] != null) {
            copy[i] = new Definition[FORMATS.length][];

            for(int i2 = 0; i2 < copy[i].length && i2 < array[i].length; ++i2) {
               if (array[i][i2] != null) {
                  int length = array[i][i2].length;
                  copy[i][i2] = new Definition[length];

                  for(int index = 0; index < copy[i][i2].length; ++index) {
                     copy[i][i2][index] = array[i][i2][index].copy();
                  }
               }
            }
         }
      }

      return copy;
   }

   private static <T> int lastNonNullIndex(T[] array) {
      int index;
      for(index = array.length - 1; array[index] == null && index > 0; --index) {
      }

      return index;
   }

   private EntityDataTypeMap(Definition<?>[][][] idDefinitions, Map<EntityDataType<?>, Definition<?>> typeDefinitionMap) {
      this.idDefinitions = idDefinitions;
      this.typeDefinitionMap = typeDefinitionMap;
   }

   public static class Definition<T> {
      int id;
      final EntityDataType<T> type;
      EntityDataTransformer<?, T> transformer;
      final EntityDataFormat format;

      private Definition<T> copy() {
         return new Definition<T>(this.id, this.type, this.transformer, this.format);
      }

      public int getId() {
         return this.id;
      }

      public EntityDataType<T> getType() {
         return this.type;
      }

      public EntityDataTransformer<?, T> getTransformer() {
         return this.transformer;
      }

      public EntityDataFormat getFormat() {
         return this.format;
      }

      public String toString() {
         return "EntityDataTypeMap.Definition(id=" + this.getId() + ", type=" + this.getType() + ", transformer=" + this.getTransformer() + ", format=" + this.getFormat() + ")";
      }

      private Definition(int id, EntityDataType<T> type, EntityDataTransformer<?, T> transformer, EntityDataFormat format) {
         this.id = id;
         this.type = type;
         this.transformer = transformer;
         this.format = format;
      }
   }

   public static class Builder {
      private Definition<?>[][][] types;
      private final Map<EntityDataType<?>, Definition<?>> typeDefinitionMap;

      private void ensureIndex(int index) {
         this.ensureCapacity(index + 1);
      }

      private void ensureCapacity(int size) {
         if (size > this.types.length) {
            int newSize = EntityDataTypeMap.powerOfTwoCeiling(size + 1);
            Definition<?>[][][] newTypes = new Definition[newSize][][];
            System.arraycopy(this.types, 0, newTypes, 0, this.types.length);
            this.types = newTypes;
         }

      }

      public <T> Builder replace(EntityDataType<T> type, int index, EntityDataFormat format) {
         return this.replace(type, index, format, EntityDataTransformer.identity());
      }

      public <T> Builder replace(EntityDataType<T> type, int index, EntityDataFormat format, EntityDataTransformer<?, T> transformer) {
         Preconditions.checkArgument(index < this.types.length, "Index is out of bounds");
         Preconditions.checkArgument(this.types[index] != null, "No data types to replace at %s", index);
         this.iterateIndex(index, (definition) -> this.typeDefinitionMap.remove(definition.type));
         this.types[index] = null;
         return this.insert(type, index, format, transformer);
      }

      public <T> Builder insert(EntityDataType<T> type, int index, EntityDataFormat format) {
         return this.insert(type, index, format, EntityDataTransformer.identity());
      }

      public <T> Builder insert(EntityDataType<T> type, int index, EntityDataFormat format, EntityDataTransformer<?, T> transformer) {
         Preconditions.checkNotNull(type, "type");
         Preconditions.checkNotNull(transformer, "transformer");
         Preconditions.checkNotNull(format, "format");
         Preconditions.checkArgument(index >= 0, "index cannot be negative");
         Preconditions.checkArgument(!this.typeDefinitionMap.containsKey(type), "type already defined");
         this.ensureIndex(index + 1);
         if (this.types[index] == null) {
            this.types[index] = new Definition[EntityDataTypeMap.FORMATS.length][];
         }

         Definition<?>[][] formats = this.types[index];
         int formatId = format.ordinal();
         Definition<?>[] definitions = formats[formatId];
         if (definitions == null) {
            definitions = formats[formatId] = new Definition[1];
         } else {
            definitions = formats[formatId] = (Definition[])Arrays.copyOf(definitions, definitions.length + 1);
         }

         Definition<?> definition = definitions[definitions.length - 1] = new Definition(index, type, transformer, format);
         this.typeDefinitionMap.put(type, definition);
         return this;
      }

      public Builder shift(int startIndex, int delta) {
         return this.shift(startIndex, delta, this.types.length - startIndex);
      }

      public Builder shift(int startIndex, int delta, int length) {
         Preconditions.checkArgument(startIndex < this.types.length, "Start index is out of bounds (%s >= %s)", startIndex, this.types.length);
         int endIndex = startIndex + length;
         Preconditions.checkArgument(endIndex <= this.types.length, "Length exceeds array bounds");
         this.ensureCapacity(this.types.length + delta);
         System.arraycopy(this.types, startIndex, this.types, startIndex + delta, length);

         for(int i = 0; i < delta; ++i) {
            this.types[startIndex + i] = null;
         }

         for(int i = 0; i < length; ++i) {
            int index = startIndex + delta + i;
            Definition<?>[][] formats = this.types[index];
            if (formats != null) {
               this.iterateIndex(index, (definition) -> {
                  definition.id = index;
                  Definition<?> def = (Definition)this.typeDefinitionMap.get(definition.type);
                  if (def != null) {
                     def.id = index;
                  }

               });
            }
         }

         return this;
      }

      public Builder remove(int index) {
         Preconditions.checkElementIndex(index, this.types.length);
         Preconditions.checkArgument(this.types[index] != null, "Cannot remove null value");
         this.iterateIndex(index, (definition) -> this.typeDefinitionMap.remove(definition.type));
         this.types[index] = null;
         return this;
      }

      public <T> Builder update(EntityDataType<T> type, EntityDataTransformer<?, T> transformer) {
         Preconditions.checkNotNull(type, "type");
         Preconditions.checkNotNull(transformer, "transformer");
         Definition<T> definition = (Definition)this.typeDefinitionMap.get(type);
         Preconditions.checkArgument(definition != null, "type not defined");
         definition.transformer = transformer;
         this.iterateIndex(definition.getId(), (def) -> {
            if (def.getFormat() == definition.getFormat()) {
               ((Definition<T>) def).transformer = transformer;
            }

         });
         return this;
      }

      private void iterateIndex(int index, Consumer<Definition<?>> consumer) {
         Definition<?>[][] formats = this.types[index];
         if (formats != null) {
            for(Definition<?>[] format : formats) {
               if (format != null) {
                  for(Definition<?> definition : format) {
                     if (definition != null) {
                        consumer.accept(definition);
                     }
                  }
               }
            }

         }
      }

      public EntityDataTypeMap build() {
         return new EntityDataTypeMap(EntityDataTypeMap.copy(this.types), this.typeDefinitionMap);
      }

      private Builder(Definition<?>[][][] types) {
         this.typeDefinitionMap = new IdentityHashMap();
         this.types = types;
      }
   }
}
