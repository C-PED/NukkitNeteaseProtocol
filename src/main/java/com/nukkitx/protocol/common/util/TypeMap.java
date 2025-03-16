package com.nukkitx.protocol.common.util;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectSortedMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import java.util.EnumSet;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.function.BiConsumer;

public final class TypeMap<T> {
   private static final InternalLogger log = InternalLoggerFactory.getInstance(TypeMap.class);
   private final String type;
   private final Object2IntMap<T> toId;
   private final Int2ObjectMap<T> toObject;

   private TypeMap(String type, Object2IntMap<T> toId, Int2ObjectMap<T> toObject) {
      this.type = type;
      this.toId = toId;
      this.toObject = toObject;
   }

   public int getId(T value) {
      Preconditions.checkNotNull(value, "value");
      int index = this.toId.getInt(value);
      Preconditions.checkArgument(index != -1, "No id found for %s", value);
      return index;
   }

   public int getIdUnsafe(T value) {
      Preconditions.checkNotNull(value, "value");
      return this.toId.getInt(value);
   }

   public T getType(int id) {
      T value = (T)this.toObject.get(id);
      Preconditions.checkNotNull(value, "type null for id %s", id);
      return value;
   }

   public T getTypeUnsafe(int id) {
      return (T)this.toObject.get(id);
   }

   public Builder<T> toBuilder() {
      Builder<T> builder = new Builder<>(type);
      this.toObject.forEach(builder::insert);
      return builder;
   }

   public void forEach(BiConsumer<Integer, T> consumer) {
      this.toObject.forEach(consumer);
   }

   public static <T> Builder<T> builder(Class<T> typeClass) {
      return new Builder<T>(typeClass.getSimpleName());
   }

   public static <T> Builder<T> builder(String type) {
      return new Builder<T>(type);
   }

   public static <T> TypeMap<T> empty(Class<T> typeClass) {
      return empty(typeClass.getSimpleName());
   }

   public static <T> TypeMap<T> empty(String type) {
      return new TypeMap<T>(type, Object2IntMaps.emptyMap(), Int2ObjectMaps.emptyMap());
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

   public String prettyPrint() {
      TreeMap<Integer, T> map = new TreeMap(this.toObject);
      StringJoiner joiner = new StringJoiner("\n");

      for(Map.Entry<Integer, T> entry : map.entrySet()) {
         joiner.add(entry.getKey() + " => " + entry.getValue());
      }

      return joiner.toString();
   }

   public static <T extends Enum<T>> TypeMap<T> fromEnum(Class<T> clazz) {
      return fromEnum(clazz, -1);
   }

   public static <T extends Enum<T>> TypeMap<T> fromEnum(Class<T> clazz, int maxIndex) {
      EnumSet<T> values = EnumSet.allOf(clazz);
      Builder<T> builder = builder(clazz);

      for(T value : values) {
         if (maxIndex != -1 && value.ordinal() > maxIndex) {
            break;
         }

         builder.insert(value.ordinal(), value);
      }

      return builder.build();
   }

   public static class Builder<T> {
      private final String type;
      private final Int2ObjectAVLTreeMap<Object> types;

      public Builder<T> insert(int index, T value) {
         Preconditions.checkNotNull(value, "value");
         Preconditions.checkArgument(this.types.get(index) == null, "Cannot insert into non-null value at index " + index);
         this.types.put(index, value);
         return this;
      }

      public Builder<T> shift(int startIndex, int amount) {
         Int2ObjectSortedMap<Object> shiftMap = this.types.tailMap(startIndex);
         Int2ObjectArrayMap<Object> tmp = new Int2ObjectArrayMap(shiftMap.size());
         ObjectBidirectionalIterator var5 = shiftMap.int2ObjectEntrySet().iterator();

         while(var5.hasNext()) {
            Int2ObjectMap.Entry<Object> entry = (Int2ObjectMap.Entry)var5.next();
            tmp.put(entry.getIntKey() + amount, entry.getValue());
            this.types.put(entry.getIntKey(), (Object)null);
         }

         this.types.putAll(tmp);
         return this;
      }

      public Builder<T> replace(int index, T value) {
         Preconditions.checkNotNull(value, "value");
         Preconditions.checkArgument(this.types.get(index) != null, "Cannot update null value");
         this.types.put(index, value);
         return this;
      }

      public Builder<T> update(int oldIndex, int newIndex, T value) {
         Preconditions.checkNotNull(value, "value");
         Preconditions.checkArgument(this.types.get(oldIndex) == value, "oldIndex value does not equal expected");
         this.types.remove(oldIndex);
         this.types.put(newIndex, value);
         return this;
      }

      public Builder<T> insert(int offset, TypeMap<? extends T> map) {
         Preconditions.checkNotNull(map, "map");
         map.toObject.forEach((index, value) -> {
            int newIndex = index + offset;
            Preconditions.checkNotNull(value, "value");
            this.types.put(newIndex, value);
         });
         return this;
      }

      public Builder<T> remove(int index) {
         this.types.remove(index);
         return this;
      }

      public TypeMap<T> build() {
         Object2IntMap<T> toId = new Object2IntOpenHashMap();
         toId.defaultReturnValue(-1);
         Int2ObjectMap<T> toObject = new Int2ObjectOpenHashMap();
         ObjectBidirectionalIterator var3 = this.types.int2ObjectEntrySet().iterator();

         while(var3.hasNext()) {
            Int2ObjectMap.Entry<Object> entry = (Int2ObjectMap.Entry)var3.next();
            Object type = entry.getValue();
            if (type != null) {
               toId.put((T) type, entry.getIntKey());
               toObject.put(entry.getIntKey(), (T) type);
            }
         }

         return new TypeMap<T>(this.type, toId, toObject);
      }

      private Builder(String type) {
         this.types = new Int2ObjectAVLTreeMap();
         this.type = type;
      }
   }
}
