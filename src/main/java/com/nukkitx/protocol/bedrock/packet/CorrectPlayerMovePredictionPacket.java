package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.PredictionType;
import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;

public class CorrectPlayerMovePredictionPacket implements BedrockPacket {
   private Vector3f position;
   private Vector3f delta;
   private boolean onGround;
   private long tick;
   private PredictionType predictionType;
   private Vector2f vehicleRotation;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CORRECT_PLAYER_MOVE_PREDICTION;
   }

   public CorrectPlayerMovePredictionPacket() {
      this.predictionType = PredictionType.PLAYER;
   }

   public Vector3f getPosition() {
      return this.position;
   }

   public Vector3f getDelta() {
      return this.delta;
   }

   public boolean isOnGround() {
      return this.onGround;
   }

   public long getTick() {
      return this.tick;
   }

   public PredictionType getPredictionType() {
      return this.predictionType;
   }

   public Vector2f getVehicleRotation() {
      return this.vehicleRotation;
   }

   public void setPosition(Vector3f position) {
      this.position = position;
   }

   public void setDelta(Vector3f delta) {
      this.delta = delta;
   }

   public void setOnGround(boolean onGround) {
      this.onGround = onGround;
   }

   public void setTick(long tick) {
      this.tick = tick;
   }

   public void setPredictionType(PredictionType predictionType) {
      this.predictionType = predictionType;
   }

   public void setVehicleRotation(Vector2f vehicleRotation) {
      this.vehicleRotation = vehicleRotation;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CorrectPlayerMovePredictionPacket)) {
         return false;
      } else {
         CorrectPlayerMovePredictionPacket other = (CorrectPlayerMovePredictionPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.onGround != other.onGround) {
            return false;
         } else if (this.tick != other.tick) {
            return false;
         } else {
            Object this$position = this.position;
            Object other$position = other.position;
            if (this$position == null) {
               if (other$position != null) {
                  return false;
               }
            } else if (!this$position.equals(other$position)) {
               return false;
            }

            Object this$delta = this.delta;
            Object other$delta = other.delta;
            if (this$delta == null) {
               if (other$delta != null) {
                  return false;
               }
            } else if (!this$delta.equals(other$delta)) {
               return false;
            }

            Object this$predictionType = this.predictionType;
            Object other$predictionType = other.predictionType;
            if (this$predictionType == null) {
               if (other$predictionType != null) {
                  return false;
               }
            } else if (!this$predictionType.equals(other$predictionType)) {
               return false;
            }

            Object this$vehicleRotation = this.vehicleRotation;
            Object other$vehicleRotation = other.vehicleRotation;
            if (this$vehicleRotation == null) {
               if (other$vehicleRotation != null) {
                  return false;
               }
            } else if (!this$vehicleRotation.equals(other$vehicleRotation)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CorrectPlayerMovePredictionPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.onGround ? 79 : 97);
      long $tick = this.tick;
      result = result * 59 + (int)($tick >>> 32 ^ $tick);
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      Object $delta = this.delta;
      result = result * 59 + ($delta == null ? 43 : $delta.hashCode());
      Object $predictionType = this.predictionType;
      result = result * 59 + ($predictionType == null ? 43 : $predictionType.hashCode());
      Object $vehicleRotation = this.vehicleRotation;
      result = result * 59 + ($vehicleRotation == null ? 43 : $vehicleRotation.hashCode());
      return result;
   }

   public String toString() {
      return "CorrectPlayerMovePredictionPacket(position=" + this.position + ", delta=" + this.delta + ", onGround=" + this.onGround + ", tick=" + this.tick + ", predictionType=" + this.predictionType + ", vehicleRotation=" + this.vehicleRotation + ")";
   }
}
