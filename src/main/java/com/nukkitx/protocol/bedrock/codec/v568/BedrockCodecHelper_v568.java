package com.nukkitx.protocol.bedrock.codec.v568;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v557.BedrockCodecHelper_v557;
import com.nukkitx.protocol.bedrock.data.Ability;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.bedrock.data.skin.AnimationData;
import com.nukkitx.protocol.bedrock.data.skin.ImageData;
import com.nukkitx.protocol.bedrock.data.skin.PersonaPieceData;
import com.nukkitx.protocol.bedrock.data.skin.PersonaPieceTintData;
import com.nukkitx.protocol.bedrock.data.skin.SerializedSkin;
import com.nukkitx.protocol.common.util.TypeMap;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class BedrockCodecHelper_v568 extends BedrockCodecHelper_v557 {
   public BedrockCodecHelper_v568(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerSlotType> containerSlotTypes, TypeMap<Ability> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
      super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
   }

   public SerializedSkin readSkin(ByteBuf buffer) {
      String skinId = this.readString(buffer);
      String playFabId = this.readString(buffer);
      String skinResourcePatch = this.readString(buffer);
      ImageData skinData = this.readImage(buffer, 262144);
      List<AnimationData> animations = new ObjectArrayList();
      this.readArray(buffer, animations, ByteBuf::readIntLE, (b, h) -> this.readAnimationData(b));
      ImageData capeData = this.readImage(buffer, 8192);
      String geometryData = this.readString(buffer);
      String geometryDataEngineVersion = this.readString(buffer);
      String animationData = this.readString(buffer);
      String capeId = this.readString(buffer);
      String fullSkinId = this.readString(buffer);
      String armSize = this.readString(buffer);
      String skinColor = this.readString(buffer);
      List<PersonaPieceData> personaPieces = new ObjectArrayList();
      this.readArray(buffer, personaPieces, ByteBuf::readIntLE, (buf, h) -> {
         String pieceId = this.readString(buf);
         String pieceType = this.readString(buf);
         String packId = this.readString(buf);
         boolean isDefault = buf.readBoolean();
         String productId = this.readString(buf);
         return new PersonaPieceData(pieceId, pieceType, packId, isDefault, productId);
      });
      List<PersonaPieceTintData> tintColors = new ObjectArrayList();
      this.readArray(buffer, tintColors, ByteBuf::readIntLE, (buf, h) -> {
         String pieceType = this.readString(buf);
         List<String> colors = new ObjectArrayList();
         int colorsLength = buf.readIntLE();

         for(int i2 = 0; i2 < colorsLength; ++i2) {
            colors.add(this.readString(buf));
         }

         return new PersonaPieceTintData(pieceType, colors);
      });
      boolean premium = buffer.readBoolean();
      boolean persona = buffer.readBoolean();
      boolean capeOnClassic = buffer.readBoolean();
      boolean primaryUser = buffer.readBoolean();
      boolean overridingPlayerAppearance = buffer.readBoolean();
      return SerializedSkin.of(skinId, playFabId, skinResourcePatch, skinData, animations, capeData, geometryData, geometryDataEngineVersion, animationData, premium, persona, capeOnClassic, primaryUser, capeId, fullSkinId, armSize, skinColor, personaPieces, tintColors, overridingPlayerAppearance);
   }

   public void writeSkin(ByteBuf buffer, SerializedSkin skin) {
      super.writeSkin(buffer, skin);
      buffer.writeBoolean(skin.isOverridingPlayerAppearance());
   }
}
