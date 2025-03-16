package com.nukkitx.protocol.common;

import com.nukkitx.protocol.common.util.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SimpleDefinitionRegistry<D extends Definition> implements DefinitionRegistry<D> {
   private final Int2ObjectMap<D> runtimeMap;

   private SimpleDefinitionRegistry(Int2ObjectMap<D> runtimeMap, Map<String, D> identifierMap) {
      this.runtimeMap = runtimeMap;
   }

   public static <D extends Definition> Builder<D> builder() {
      return new Builder<D>();
   }

   public D getDefinition(int runtimeId) {
      return (D)(this.runtimeMap.get(runtimeId));
   }

   public boolean isRegistered(D definition) {
      return this.runtimeMap.get(definition.getRuntimeId()) == definition;
   }

   public Builder<D> toBuilder() {
      return (new Builder<D>()).addAll(this.runtimeMap.values());
   }

   public static class Builder<D extends Definition> {
      private final Int2ObjectMap<D> runtimeMap = new Int2ObjectOpenHashMap();
      private final Map<String, D> identifierMap = new HashMap();

      public Builder<D> addAll(Collection<? extends D> definitions) {
         for(D definition : definitions) {
            this.add(definition);
         }

         return this;
      }

      public Builder<D> add(D definition) {
         Preconditions.checkNotNull(definition, "definition");
         Preconditions.checkArgument(!this.runtimeMap.containsKey(definition.getRuntimeId()), "Runtime ID is already registered: " + definition.getRuntimeId());
         this.runtimeMap.put(definition.getRuntimeId(), definition);
         return this;
      }

      public Builder<D> remove(D definition) {
         Preconditions.checkNotNull(definition, "definition");
         Preconditions.checkArgument(this.runtimeMap.containsKey(definition.getRuntimeId()), "Runtime ID is not registered: " + definition.getRuntimeId());
         this.runtimeMap.remove(definition.getRuntimeId());
         return this;
      }

      public SimpleDefinitionRegistry<D> build() {
         return new SimpleDefinitionRegistry<D>(this.runtimeMap, this.identifierMap);
      }
   }
}
