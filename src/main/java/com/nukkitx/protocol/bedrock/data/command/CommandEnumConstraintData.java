package com.nukkitx.protocol.bedrock.data.command;

import java.util.Arrays;

public final class CommandEnumConstraintData {
   private final String option;
   private final CommandEnumData enumData;
   private final CommandEnumConstraintType[] constraints;

   public CommandEnumConstraintData(String option, CommandEnumData enumData, CommandEnumConstraintType[] constraints) {
      this.option = option;
      this.enumData = enumData;
      this.constraints = constraints;
   }

   public String getOption() {
      return this.option;
   }

   public CommandEnumData getEnumData() {
      return this.enumData;
   }

   public CommandEnumConstraintType[] getConstraints() {
      return this.constraints;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CommandEnumConstraintData)) {
         return false;
      } else {
         CommandEnumConstraintData other = (CommandEnumConstraintData)o;
         Object this$option = this.getOption();
         Object other$option = other.getOption();
         if (this$option == null) {
            if (other$option != null) {
               return false;
            }
         } else if (!this$option.equals(other$option)) {
            return false;
         }

         Object this$enumData = this.getEnumData();
         Object other$enumData = other.getEnumData();
         if (this$enumData == null) {
            if (other$enumData != null) {
               return false;
            }
         } else if (!this$enumData.equals(other$enumData)) {
            return false;
         }

         if (!Arrays.deepEquals(this.getConstraints(), other.getConstraints())) {
            return false;
         } else {
            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $option = this.getOption();
      result = result * 59 + ($option == null ? 43 : $option.hashCode());
      Object $enumData = this.getEnumData();
      result = result * 59 + ($enumData == null ? 43 : $enumData.hashCode());
      result = result * 59 + Arrays.deepHashCode(this.getConstraints());
      return result;
   }

   public String toString() {
      return "CommandEnumConstraintData(option=" + this.getOption() + ", enumData=" + this.getEnumData() + ", constraints=" + Arrays.deepToString(this.getConstraints()) + ")";
   }
}
