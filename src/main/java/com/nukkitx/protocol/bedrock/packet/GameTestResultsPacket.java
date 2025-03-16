package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class GameTestResultsPacket implements BedrockPacket {
   private boolean successful;
   private String error;
   private String testName;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.GAME_TEST_RESULTS;
   }

   public boolean isSuccessful() {
      return this.successful;
   }

   public String getError() {
      return this.error;
   }

   public String getTestName() {
      return this.testName;
   }

   public void setSuccessful(boolean successful) {
      this.successful = successful;
   }

   public void setError(String error) {
      this.error = error;
   }

   public void setTestName(String testName) {
      this.testName = testName;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GameTestResultsPacket)) {
         return false;
      } else {
         GameTestResultsPacket other = (GameTestResultsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.successful != other.successful) {
            return false;
         } else {
            Object this$error = this.error;
            Object other$error = other.error;
            if (this$error == null) {
               if (other$error != null) {
                  return false;
               }
            } else if (!this$error.equals(other$error)) {
               return false;
            }

            Object this$testName = this.testName;
            Object other$testName = other.testName;
            if (this$testName == null) {
               if (other$testName != null) {
                  return false;
               }
            } else if (!this$testName.equals(other$testName)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof GameTestResultsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.successful ? 79 : 97);
      Object $error = this.error;
      result = result * 59 + ($error == null ? 43 : $error.hashCode());
      Object $testName = this.testName;
      result = result * 59 + ($testName == null ? 43 : $testName.hashCode());
      return result;
   }

   public String toString() {
      return "GameTestResultsPacket(successful=" + this.successful + ", error=" + this.error + ", testName=" + this.testName + ")";
   }
}
