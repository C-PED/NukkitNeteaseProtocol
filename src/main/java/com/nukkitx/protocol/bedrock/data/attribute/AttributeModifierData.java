package com.nukkitx.protocol.bedrock.data.attribute;

public final class AttributeModifierData {
   private final String id;
   private final String name;
   private final float amount;
   private final AttributeOperation operation;
   private final int operand;
   private final boolean serializable;

   public AttributeModifierData(String id, String name, float amount, AttributeOperation operation, int operand, boolean serializable) {
      this.id = id;
      this.name = name;
      this.amount = amount;
      this.operation = operation;
      this.operand = operand;
      this.serializable = serializable;
   }

   public String getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public float getAmount() {
      return this.amount;
   }

   public AttributeOperation getOperation() {
      return this.operation;
   }

   public int getOperand() {
      return this.operand;
   }

   public boolean isSerializable() {
      return this.serializable;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AttributeModifierData)) {
         return false;
      } else {
         AttributeModifierData other = (AttributeModifierData)o;
         if (Float.compare(this.getAmount(), other.getAmount()) != 0) {
            return false;
         } else if (this.getOperand() != other.getOperand()) {
            return false;
         } else if (this.isSerializable() != other.isSerializable()) {
            return false;
         } else {
            Object this$id = this.getId();
            Object other$id = other.getId();
            if (this$id == null) {
               if (other$id != null) {
                  return false;
               }
            } else if (!this$id.equals(other$id)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$operation = this.getOperation();
            Object other$operation = other.getOperation();
            if (this$operation == null) {
               if (other$operation != null) {
                  return false;
               }
            } else if (!this$operation.equals(other$operation)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getAmount());
      result = result * 59 + this.getOperand();
      result = result * 59 + (this.isSerializable() ? 79 : 97);
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $operation = this.getOperation();
      result = result * 59 + ($operation == null ? 43 : $operation.hashCode());
      return result;
   }

   public String toString() {
      return "AttributeModifierData(id=" + this.getId() + ", name=" + this.getName() + ", amount=" + this.getAmount() + ", operation=" + this.getOperation() + ", operand=" + this.getOperand() + ", serializable=" + this.isSerializable() + ")";
   }
}
