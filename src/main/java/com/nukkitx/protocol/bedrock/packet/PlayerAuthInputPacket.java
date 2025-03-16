package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.ClientPlayMode;
import com.nukkitx.protocol.bedrock.data.InputInteractionModel;
import com.nukkitx.protocol.bedrock.data.InputMode;
import com.nukkitx.protocol.bedrock.data.PlayerAuthInputData;
import com.nukkitx.protocol.bedrock.data.PlayerBlockActionData;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.ItemUseTransaction;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;

public class PlayerAuthInputPacket implements BedrockPacket {
   private Vector3f rotation;
   private Vector3f position;
   private Vector2f motion;
   private final Set<PlayerAuthInputData> inputData = EnumSet.noneOf(PlayerAuthInputData.class);
   private InputMode inputMode;
   private ClientPlayMode playMode;
   private Vector3f vrGazeDirection;
   private long tick;
   private Vector3f delta;
   private boolean cameraDeparted;
   private ItemUseTransaction itemUseTransaction;
   private ItemStackRequest itemStackRequest;
   private final List<PlayerBlockActionData> playerActions = new ObjectArrayList();
   private InputInteractionModel inputInteractionModel;
   private Vector2f analogMoveVector;
   private long predictedVehicle;
   private Vector2f vehicleRotation;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PLAYER_AUTH_INPUT;
   }

   public Vector3f getRotation() {
      return this.rotation;
   }

   public Vector3f getPosition() {
      return this.position;
   }

   public Vector2f getMotion() {
      return this.motion;
   }

   public Set<PlayerAuthInputData> getInputData() {
      return this.inputData;
   }

   public InputMode getInputMode() {
      return this.inputMode;
   }

   public ClientPlayMode getPlayMode() {
      return this.playMode;
   }

   public Vector3f getVrGazeDirection() {
      return this.vrGazeDirection;
   }

   public long getTick() {
      return this.tick;
   }

   public Vector3f getDelta() {
      return this.delta;
   }

   public boolean isCameraDeparted() {
      return this.cameraDeparted;
   }

   public ItemUseTransaction getItemUseTransaction() {
      return this.itemUseTransaction;
   }

   public ItemStackRequest getItemStackRequest() {
      return this.itemStackRequest;
   }

   public List<PlayerBlockActionData> getPlayerActions() {
      return this.playerActions;
   }

   public InputInteractionModel getInputInteractionModel() {
      return this.inputInteractionModel;
   }

   public Vector2f getAnalogMoveVector() {
      return this.analogMoveVector;
   }

   public long getPredictedVehicle() {
      return this.predictedVehicle;
   }

   public Vector2f getVehicleRotation() {
      return this.vehicleRotation;
   }

   public void setRotation(Vector3f rotation) {
      this.rotation = rotation;
   }

   public void setPosition(Vector3f position) {
      this.position = position;
   }

   public void setMotion(Vector2f motion) {
      this.motion = motion;
   }

   public void setInputMode(InputMode inputMode) {
      this.inputMode = inputMode;
   }

   public void setPlayMode(ClientPlayMode playMode) {
      this.playMode = playMode;
   }

   public void setVrGazeDirection(Vector3f vrGazeDirection) {
      this.vrGazeDirection = vrGazeDirection;
   }

   public void setTick(long tick) {
      this.tick = tick;
   }

   public void setDelta(Vector3f delta) {
      this.delta = delta;
   }

   public void setCameraDeparted(boolean cameraDeparted) {
      this.cameraDeparted = cameraDeparted;
   }

   public void setItemUseTransaction(ItemUseTransaction itemUseTransaction) {
      this.itemUseTransaction = itemUseTransaction;
   }

   public void setItemStackRequest(ItemStackRequest itemStackRequest) {
      this.itemStackRequest = itemStackRequest;
   }

   public void setInputInteractionModel(InputInteractionModel inputInteractionModel) {
      this.inputInteractionModel = inputInteractionModel;
   }

   public void setAnalogMoveVector(Vector2f analogMoveVector) {
      this.analogMoveVector = analogMoveVector;
   }

   public void setPredictedVehicle(long predictedVehicle) {
      this.predictedVehicle = predictedVehicle;
   }

   public void setVehicleRotation(Vector2f vehicleRotation) {
      this.vehicleRotation = vehicleRotation;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PlayerAuthInputPacket)) {
         return false;
      } else {
         PlayerAuthInputPacket other = (PlayerAuthInputPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.tick != other.tick) {
            return false;
         } else if (this.cameraDeparted != other.cameraDeparted) {
            return false;
         } else if (this.predictedVehicle != other.predictedVehicle) {
            return false;
         } else {
            Object this$rotation = this.rotation;
            Object other$rotation = other.rotation;
            if (this$rotation == null) {
               if (other$rotation != null) {
                  return false;
               }
            } else if (!this$rotation.equals(other$rotation)) {
               return false;
            }

            Object this$position = this.position;
            Object other$position = other.position;
            if (this$position == null) {
               if (other$position != null) {
                  return false;
               }
            } else if (!this$position.equals(other$position)) {
               return false;
            }

            Object this$motion = this.motion;
            Object other$motion = other.motion;
            if (this$motion == null) {
               if (other$motion != null) {
                  return false;
               }
            } else if (!this$motion.equals(other$motion)) {
               return false;
            }

            Object this$inputData = this.inputData;
            Object other$inputData = other.inputData;
            if (this$inputData == null) {
               if (other$inputData != null) {
                  return false;
               }
            } else if (!this$inputData.equals(other$inputData)) {
               return false;
            }

            Object this$inputMode = this.inputMode;
            Object other$inputMode = other.inputMode;
            if (this$inputMode == null) {
               if (other$inputMode != null) {
                  return false;
               }
            } else if (!this$inputMode.equals(other$inputMode)) {
               return false;
            }

            Object this$playMode = this.playMode;
            Object other$playMode = other.playMode;
            if (this$playMode == null) {
               if (other$playMode != null) {
                  return false;
               }
            } else if (!this$playMode.equals(other$playMode)) {
               return false;
            }

            Object this$vrGazeDirection = this.vrGazeDirection;
            Object other$vrGazeDirection = other.vrGazeDirection;
            if (this$vrGazeDirection == null) {
               if (other$vrGazeDirection != null) {
                  return false;
               }
            } else if (!this$vrGazeDirection.equals(other$vrGazeDirection)) {
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

            Object this$itemUseTransaction = this.itemUseTransaction;
            Object other$itemUseTransaction = other.itemUseTransaction;
            if (this$itemUseTransaction == null) {
               if (other$itemUseTransaction != null) {
                  return false;
               }
            } else if (!this$itemUseTransaction.equals(other$itemUseTransaction)) {
               return false;
            }

            Object this$itemStackRequest = this.itemStackRequest;
            Object other$itemStackRequest = other.itemStackRequest;
            if (this$itemStackRequest == null) {
               if (other$itemStackRequest != null) {
                  return false;
               }
            } else if (!this$itemStackRequest.equals(other$itemStackRequest)) {
               return false;
            }

            Object this$playerActions = this.playerActions;
            Object other$playerActions = other.playerActions;
            if (this$playerActions == null) {
               if (other$playerActions != null) {
                  return false;
               }
            } else if (!this$playerActions.equals(other$playerActions)) {
               return false;
            }

            Object this$inputInteractionModel = this.inputInteractionModel;
            Object other$inputInteractionModel = other.inputInteractionModel;
            if (this$inputInteractionModel == null) {
               if (other$inputInteractionModel != null) {
                  return false;
               }
            } else if (!this$inputInteractionModel.equals(other$inputInteractionModel)) {
               return false;
            }

            Object this$analogMoveVector = this.analogMoveVector;
            Object other$analogMoveVector = other.analogMoveVector;
            if (this$analogMoveVector == null) {
               if (other$analogMoveVector != null) {
                  return false;
               }
            } else if (!this$analogMoveVector.equals(other$analogMoveVector)) {
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
      return other instanceof PlayerAuthInputPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $tick = this.tick;
      result = result * 59 + (int)($tick >>> 32 ^ $tick);
      result = result * 59 + (this.cameraDeparted ? 79 : 97);
      long $predictedVehicle = this.predictedVehicle;
      result = result * 59 + (int)($predictedVehicle >>> 32 ^ $predictedVehicle);
      Object $rotation = this.rotation;
      result = result * 59 + ($rotation == null ? 43 : $rotation.hashCode());
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      Object $motion = this.motion;
      result = result * 59 + ($motion == null ? 43 : $motion.hashCode());
      Object $inputData = this.inputData;
      result = result * 59 + ($inputData == null ? 43 : $inputData.hashCode());
      Object $inputMode = this.inputMode;
      result = result * 59 + ($inputMode == null ? 43 : $inputMode.hashCode());
      Object $playMode = this.playMode;
      result = result * 59 + ($playMode == null ? 43 : $playMode.hashCode());
      Object $vrGazeDirection = this.vrGazeDirection;
      result = result * 59 + ($vrGazeDirection == null ? 43 : $vrGazeDirection.hashCode());
      Object $delta = this.delta;
      result = result * 59 + ($delta == null ? 43 : $delta.hashCode());
      Object $itemUseTransaction = this.itemUseTransaction;
      result = result * 59 + ($itemUseTransaction == null ? 43 : $itemUseTransaction.hashCode());
      Object $itemStackRequest = this.itemStackRequest;
      result = result * 59 + ($itemStackRequest == null ? 43 : $itemStackRequest.hashCode());
      Object $playerActions = this.playerActions;
      result = result * 59 + ($playerActions == null ? 43 : $playerActions.hashCode());
      Object $inputInteractionModel = this.inputInteractionModel;
      result = result * 59 + ($inputInteractionModel == null ? 43 : $inputInteractionModel.hashCode());
      Object $analogMoveVector = this.analogMoveVector;
      result = result * 59 + ($analogMoveVector == null ? 43 : $analogMoveVector.hashCode());
      Object $vehicleRotation = this.vehicleRotation;
      result = result * 59 + ($vehicleRotation == null ? 43 : $vehicleRotation.hashCode());
      return result;
   }

   public String toString() {
      return "PlayerAuthInputPacket(rotation=" + this.rotation + ", position=" + this.position + ", motion=" + this.motion + ", inputData=" + this.inputData + ", inputMode=" + this.inputMode + ", playMode=" + this.playMode + ", vrGazeDirection=" + this.vrGazeDirection + ", tick=" + this.tick + ", delta=" + this.delta + ", cameraDeparted=" + this.cameraDeparted + ", itemUseTransaction=" + this.itemUseTransaction + ", itemStackRequest=" + this.itemStackRequest + ", playerActions=" + this.playerActions + ", inputInteractionModel=" + this.inputInteractionModel + ", analogMoveVector=" + this.analogMoveVector + ", predictedVehicle=" + this.predictedVehicle + ", vehicleRotation=" + this.vehicleRotation + ")";
   }
}
