package com.neteasemc.protocol.custom;

import java.util.function.Supplier;

public final class GeyerPacketDefinition<T extends GeyserBasePacket> {
   private final int id;
   private final Supplier<T> factory;
   private final GeyserPacketSerializer<T> serializer;

   public GeyerPacketDefinition(int id, Supplier<T> factory, GeyserPacketSerializer<T> serializer) {
      this.id = id;
      this.factory = factory;
      this.serializer = serializer;
   }

   public int getId() {
      return this.id;
   }

   public Supplier<T> getFactory() {
      return this.factory;
   }

   public GeyserPacketSerializer<T> getSerializer() {
      return this.serializer;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GeyerPacketDefinition)) {
         return false;
      } else {
         GeyerPacketDefinition<?> other = (GeyerPacketDefinition)o;
         if (this.getId() != other.getId()) {
            return false;
         } else {
            Object this$factory = this.getFactory();
            Object other$factory = other.getFactory();
            if (this$factory == null) {
               if (other$factory != null) {
                  return false;
               }
            } else if (!this$factory.equals(other$factory)) {
               return false;
            }

            Object this$serializer = this.getSerializer();
            Object other$serializer = other.getSerializer();
            if (this$serializer == null) {
               if (other$serializer != null) {
                  return false;
               }
            } else if (!this$serializer.equals(other$serializer)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getId();
      Object $factory = this.getFactory();
      result = result * 59 + ($factory == null ? 43 : $factory.hashCode());
      Object $serializer = this.getSerializer();
      result = result * 59 + ($serializer == null ? 43 : $serializer.hashCode());
      return result;
   }

   public String toString() {
      return "GeyerPacketDefinition(id=" + this.getId() + ", factory=" + this.getFactory() + ", serializer=" + this.getSerializer() + ")";
   }
}
