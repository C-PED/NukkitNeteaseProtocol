package com.nukkitx.protocol.bedrock.codec.v428;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v422.BedrockCodecHelper_v422;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.MineBlockAction;
import com.nukkitx.protocol.bedrock.data.skin.AnimationData;
import com.nukkitx.protocol.bedrock.data.skin.ImageData;
import com.nukkitx.protocol.bedrock.data.skin.PersonaPieceData;
import com.nukkitx.protocol.bedrock.data.skin.PersonaPieceTintData;
import com.nukkitx.protocol.bedrock.data.skin.SerializedSkin;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.Objects;

public class BedrockCodecHelper_v428 extends BedrockCodecHelper_v422 {
   public BedrockCodecHelper_v428(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerSlotType> containerSlotTypes) {
      super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes);
   }

   protected ItemStackRequestAction readRequestActionData(ByteBuf byteBuf, ItemStackRequestActionType type) {
      ItemStackRequestAction action;
      if (type == ItemStackRequestActionType.MINE_BLOCK) {
         action = new MineBlockAction(VarInts.readInt(byteBuf), VarInts.readInt(byteBuf), VarInts.readInt(byteBuf));
      } else {
         action = super.readRequestActionData(byteBuf, type);
      }

      return action;
   }

   protected void writeRequestActionData(ByteBuf byteBuf, ItemStackRequestAction action) {
      if (action.getType() == ItemStackRequestActionType.MINE_BLOCK) {
         VarInts.writeInt(byteBuf, ((MineBlockAction)action).getHotbarSlot());
         VarInts.writeInt(byteBuf, ((MineBlockAction)action).getPredictedDurability());
         VarInts.writeInt(byteBuf, ((MineBlockAction)action).getStackNetworkId());
      } else {
         super.writeRequestActionData(byteBuf, action);
      }

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
      String animationData = this.readString(buffer);
      boolean premium = buffer.readBoolean();
      boolean persona = buffer.readBoolean();
      boolean capeOnClassic = buffer.readBoolean();
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
      return SerializedSkin.of(skinId, playFabId, skinResourcePatch, skinData, animations, capeData, geometryData, animationData, premium, persona, capeOnClassic, capeId, fullSkinId, armSize, skinColor, personaPieces, tintColors);
   }

   public void writeSkin(ByteBuf buffer, SerializedSkin skin) {
      Objects.requireNonNull(skin, "Skin is null");
      this.writeString(buffer, skin.getSkinId());
      this.writeString(buffer, skin.getPlayFabId());
      this.writeString(buffer, skin.getSkinResourcePatch());
      this.writeImage(buffer, skin.getSkinData());
      List<AnimationData> animations = skin.getAnimations();
      buffer.writeIntLE(animations.size());

      for(AnimationData animation : animations) {
         this.writeAnimationData(buffer, animation);
      }

      this.writeImage(buffer, skin.getCapeData());
      this.writeString(buffer, skin.getGeometryData());
      this.writeString(buffer, skin.getAnimationData());
      buffer.writeBoolean(skin.isPremium());
      buffer.writeBoolean(skin.isPersona());
      buffer.writeBoolean(skin.isCapeOnClassic());
      this.writeString(buffer, skin.getCapeId());
      this.writeString(buffer, skin.getFullSkinId());
      this.writeString(buffer, skin.getArmSize());
      this.writeString(buffer, skin.getSkinColor());
      List<PersonaPieceData> pieces = skin.getPersonaPieces();
      buffer.writeIntLE(pieces.size());

      for(PersonaPieceData piece : pieces) {
         this.writeString(buffer, piece.getId());
         this.writeString(buffer, piece.getType());
         this.writeString(buffer, piece.getPackId());
         buffer.writeBoolean(piece.isDefault());
         this.writeString(buffer, piece.getProductId());
      }

      List<PersonaPieceTintData> tints = skin.getTintColors();
      buffer.writeIntLE(tints.size());

      for(PersonaPieceTintData tint : tints) {
         this.writeString(buffer, tint.getType());
         List<String> colors = tint.getColors();
         buffer.writeIntLE(colors.size());

         for(String color : colors) {
            this.writeString(buffer, color);
         }
      }

   }
}
