package com.nukkitx.protocol.bedrock.data.command;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class ChainedSubCommandData {
   private final String name;
   private final List<Value> values = new ObjectArrayList();

   public ChainedSubCommandData(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public List<Value> getValues() {
      return this.values;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ChainedSubCommandData)) {
         return false;
      } else {
         ChainedSubCommandData other = (ChainedSubCommandData)o;
         if (!other.canEqual(this)) {
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

            Object this$values = this.getValues();
            Object other$values = other.getValues();
            if (this$values == null) {
               if (other$values != null) {
                  return false;
               }
            } else if (!this$values.equals(other$values)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ChainedSubCommandData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $values = this.getValues();
      result = result * 59 + ($values == null ? 43 : $values.hashCode());
      return result;
   }

   public String toString() {
      return "ChainedSubCommandData(name=" + this.getName() + ", values=" + this.getValues() + ")";
   }

   public static class Value {
      private final String first;
      private final String second;

      public Value(String first, String second) {
         this.first = first;
         this.second = second;
      }

      public String getFirst() {
         return this.first;
      }

      public String getSecond() {
         return this.second;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof Value)) {
            return false;
         } else {
            Value other = (Value)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$first = this.getFirst();
               Object other$first = other.getFirst();
               if (this$first == null) {
                  if (other$first != null) {
                     return false;
                  }
               } else if (!this$first.equals(other$first)) {
                  return false;
               }

               Object this$second = this.getSecond();
               Object other$second = other.getSecond();
               if (this$second == null) {
                  if (other$second != null) {
                     return false;
                  }
               } else if (!this$second.equals(other$second)) {
                  return false;
               }

               return true;
            }
         }
      }

      protected boolean canEqual(Object other) {
         return other instanceof Value;
      }

      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $first = this.getFirst();
         result = result * 59 + ($first == null ? 43 : $first.hashCode());
         Object $second = this.getSecond();
         result = result * 59 + ($second == null ? 43 : $second.hashCode());
         return result;
      }

      public String toString() {
         return "ChainedSubCommandData.Value(first=" + this.getFirst() + ", second=" + this.getSecond() + ")";
      }
   }
}
