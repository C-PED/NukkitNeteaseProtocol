package com.nukkitx.protocol.bedrock.data;

public final class GameRuleData<T> {
   private final String name;
   private final boolean editable;
   private final T value;

   public GameRuleData(String name, T value) {
      this.name = name;
      this.value = value;
      this.editable = false;
   }

   public String toString() {
      return this.name + '=' + this.value;
   }

   public String getName() {
      return this.name;
   }

   public boolean isEditable() {
      return this.editable;
   }

   public T getValue() {
      return this.value;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GameRuleData)) {
         return false;
      } else {
         GameRuleData<?> other = (GameRuleData)o;
         if (this.isEditable() != other.isEditable()) {
            return false;
         } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$value = this.getValue();
            Object other$value = other.getValue();
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

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.isEditable() ? 79 : 97);
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $value = this.getValue();
      result = result * 59 + ($value == null ? 43 : $value.hashCode());
      return result;
   }

   public GameRuleData(String name, boolean editable, T value) {
      this.name = name;
      this.editable = editable;
      this.value = value;
   }
}
