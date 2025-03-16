package com.nukkitx.protocol.bedrock.data.inventory.crafting;

public final class PotionMixData {
   private final int inputId;
   private final int inputMeta;
   private final int reagentId;
   private final int reagentMeta;
   private final int outputId;
   private final int outputMeta;

   public PotionMixData(int inputId, int inputMeta, int reagentId, int reagentMeta, int outputId, int outputMeta) {
      this.inputId = inputId;
      this.inputMeta = inputMeta;
      this.reagentId = reagentId;
      this.reagentMeta = reagentMeta;
      this.outputId = outputId;
      this.outputMeta = outputMeta;
   }

   public int getInputId() {
      return this.inputId;
   }

   public int getInputMeta() {
      return this.inputMeta;
   }

   public int getReagentId() {
      return this.reagentId;
   }

   public int getReagentMeta() {
      return this.reagentMeta;
   }

   public int getOutputId() {
      return this.outputId;
   }

   public int getOutputMeta() {
      return this.outputMeta;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PotionMixData)) {
         return false;
      } else {
         PotionMixData other = (PotionMixData)o;
         if (this.getInputId() != other.getInputId()) {
            return false;
         } else if (this.getInputMeta() != other.getInputMeta()) {
            return false;
         } else if (this.getReagentId() != other.getReagentId()) {
            return false;
         } else if (this.getReagentMeta() != other.getReagentMeta()) {
            return false;
         } else if (this.getOutputId() != other.getOutputId()) {
            return false;
         } else {
            return this.getOutputMeta() == other.getOutputMeta();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getInputId();
      result = result * 59 + this.getInputMeta();
      result = result * 59 + this.getReagentId();
      result = result * 59 + this.getReagentMeta();
      result = result * 59 + this.getOutputId();
      result = result * 59 + this.getOutputMeta();
      return result;
   }

   public String toString() {
      return "PotionMixData(inputId=" + this.getInputId() + ", inputMeta=" + this.getInputMeta() + ", reagentId=" + this.getReagentId() + ", reagentMeta=" + this.getReagentMeta() + ", outputId=" + this.getOutputId() + ", outputMeta=" + this.getOutputMeta() + ")";
   }
}
