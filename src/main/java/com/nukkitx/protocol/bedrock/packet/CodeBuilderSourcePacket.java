package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.CodeBuilderCategoryType;
import com.nukkitx.protocol.bedrock.data.CodeBuilderOperationType;
import com.nukkitx.protocol.common.PacketSignal;

public class CodeBuilderSourcePacket implements BedrockPacket {
   private CodeBuilderOperationType operation;
   private CodeBuilderCategoryType category;
   private String value;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CODE_BUILDER_SOURCE;
   }

   public CodeBuilderOperationType getOperation() {
      return this.operation;
   }

   public CodeBuilderCategoryType getCategory() {
      return this.category;
   }

   public String getValue() {
      return this.value;
   }

   public void setOperation(CodeBuilderOperationType operation) {
      this.operation = operation;
   }

   public void setCategory(CodeBuilderCategoryType category) {
      this.category = category;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CodeBuilderSourcePacket)) {
         return false;
      } else {
         CodeBuilderSourcePacket other = (CodeBuilderSourcePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$operation = this.operation;
            Object other$operation = other.operation;
            if (this$operation == null) {
               if (other$operation != null) {
                  return false;
               }
            } else if (!this$operation.equals(other$operation)) {
               return false;
            }

            Object this$category = this.category;
            Object other$category = other.category;
            if (this$category == null) {
               if (other$category != null) {
                  return false;
               }
            } else if (!this$category.equals(other$category)) {
               return false;
            }

            Object this$value = this.value;
            Object other$value = other.value;
            if (this$value == null) {
               if (other$value != null) {
                  return false;
               }
            } else if (!this$value.equals(other$value)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CodeBuilderSourcePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $operation = this.operation;
      result = result * 59 + ($operation == null ? 43 : $operation.hashCode());
      Object $category = this.category;
      result = result * 59 + ($category == null ? 43 : $category.hashCode());
      Object $value = this.value;
      result = result * 59 + ($value == null ? 43 : $value.hashCode());
      return result;
   }

   public String toString() {
      return "CodeBuilderSourcePacket(operation=" + this.operation + ", category=" + this.category + ", value=" + this.value + ")";
   }
}
