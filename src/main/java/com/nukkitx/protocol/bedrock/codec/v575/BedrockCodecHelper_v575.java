package com.nukkitx.protocol.bedrock.codec.v575;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v568.BedrockCodecHelper_v568;
import com.nukkitx.protocol.bedrock.data.Ability;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ComplexAliasDescriptor;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptor;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorType;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.common.DefinitionRegistry;
import com.nukkitx.protocol.common.NamedDefinition;
import com.nukkitx.protocol.common.util.TypeMap;
import io.netty.buffer.ByteBuf;

public class BedrockCodecHelper_v575 extends BedrockCodecHelper_v568 {
   protected DefinitionRegistry<NamedDefinition> cameraPresetDefinitions;

   public BedrockCodecHelper_v575(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerSlotType> containerSlotTypes, TypeMap<Ability> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
      super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
   }

   protected ItemDescriptor readItemDescriptor(ByteBuf buffer, ItemDescriptorType type) {
      if (type == ItemDescriptorType.COMPLEX_ALIAS) {
         String name = this.readString(buffer);
         return new ComplexAliasDescriptor(name);
      } else {
         return super.readItemDescriptor(buffer, type);
      }
   }

   protected void writeItemDescriptor(ByteBuf buffer, ItemDescriptor descriptor) {
      if (descriptor.getType() == ItemDescriptorType.COMPLEX_ALIAS) {
         this.writeString(buffer, ((ComplexAliasDescriptor)descriptor).getName());
      } else {
         super.writeItemDescriptor(buffer, descriptor);
      }

   }

   public DefinitionRegistry<NamedDefinition> getCameraPresetDefinitions() {
      return this.cameraPresetDefinitions;
   }

   public void setCameraPresetDefinitions(DefinitionRegistry<NamedDefinition> cameraPresetDefinitions) {
      this.cameraPresetDefinitions = cameraPresetDefinitions;
   }
}
