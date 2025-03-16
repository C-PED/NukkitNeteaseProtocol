package com.nukkitx.protocol.bedrock.codec;

import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import java.util.function.Supplier;

public final class BedrockPacketDefinition<T extends BedrockPacket> {
   private final int id;
   private final Supplier<T> factory;
   private final BedrockPacketSerializer<T> serializer;
   private final PacketRecipient recipient;

   public BedrockPacketDefinition(int id, Supplier<T> factory, BedrockPacketSerializer<T> serializer, PacketRecipient recipient) {
      this.id = id;
      this.factory = factory;
      this.serializer = serializer;
      this.recipient = recipient;
   }

   public int getId() {
      return this.id;
   }

   public Supplier<T> getFactory() {
      return this.factory;
   }

   public BedrockPacketSerializer<T> getSerializer() {
      return this.serializer;
   }

   public PacketRecipient getRecipient() {
      return this.recipient;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BedrockPacketDefinition)) {
         return false;
      } else {
         BedrockPacketDefinition<?> other = (BedrockPacketDefinition)o;
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

            Object this$recipient = this.getRecipient();
            Object other$recipient = other.getRecipient();
            if (this$recipient == null) {
               if (other$recipient != null) {
                  return false;
               }
            } else if (!this$recipient.equals(other$recipient)) {
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
      Object $recipient = this.getRecipient();
      result = result * 59 + ($recipient == null ? 43 : $recipient.hashCode());
      return result;
   }

   public String toString() {
      return "BedrockPacketDefinition(id=" + this.getId() + ", factory=" + this.getFactory() + ", serializer=" + this.getSerializer() + ", recipient=" + this.getRecipient() + ")";
   }
}
