package com.nukkitx.protocol.bedrock.codec.v419;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v407.BedrockCodecHelper_v407;
import com.nukkitx.protocol.bedrock.data.ExperimentData;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.bedrock.data.skin.AnimatedTextureType;
import com.nukkitx.protocol.bedrock.data.skin.AnimationData;
import com.nukkitx.protocol.bedrock.data.skin.AnimationExpressionType;
import com.nukkitx.protocol.bedrock.data.skin.ImageData;
import com.nukkitx.protocol.common.util.TypeMap;
import io.netty.buffer.ByteBuf;
import java.util.List;

public class BedrockCodecHelper_v419 extends BedrockCodecHelper_v407 {
   protected static final AnimationExpressionType[] EXPRESSION_TYPES = AnimationExpressionType.values();

   public BedrockCodecHelper_v419(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerSlotType> containerSlotTypes) {
      super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes);
   }

   public void readExperiments(ByteBuf buffer, List<ExperimentData> experiments) {
      int count = buffer.readIntLE();

      for(int i = 0; i < count; ++i) {
         experiments.add(new ExperimentData(this.readString(buffer), buffer.readBoolean()));
      }

   }

   public void writeExperiments(ByteBuf buffer, List<ExperimentData> experiments) {
      buffer.writeIntLE(experiments.size());

      for(ExperimentData experiment : experiments) {
         this.writeString(buffer, experiment.getName());
         buffer.writeBoolean(experiment.isEnabled());
      }

   }

   public AnimationData readAnimationData(ByteBuf buffer) {
      ImageData image = this.readImage(buffer, 65536);
      AnimatedTextureType textureType = TEXTURE_TYPES[buffer.readIntLE()];
      float frames = buffer.readFloatLE();
      AnimationExpressionType expressionType = EXPRESSION_TYPES[buffer.readIntLE()];
      return new AnimationData(image, textureType, frames, expressionType);
   }

   public void writeAnimationData(ByteBuf buffer, AnimationData animation) {
      super.writeAnimationData(buffer, animation);
      buffer.writeIntLE(animation.getExpressionType().ordinal());
   }
}
