package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.skin.ImageData;
import com.nukkitx.protocol.bedrock.data.skin.SerializedSkin;
import com.nukkitx.protocol.bedrock.packet.PlayerSkinPacket;
import io.netty.buffer.ByteBuf;

public class PlayerSkinSerializer_v291 implements BedrockPacketSerializer<PlayerSkinPacket> {
   public static final PlayerSkinSerializer_v291 INSTANCE = new PlayerSkinSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerSkinPacket packet) {
      helper.writeUuid(buffer, packet.getUuid());
      SerializedSkin skin = packet.getSkin();
      helper.writeString(buffer, skin.getSkinId());
      helper.writeString(buffer, packet.getNewSkinName());
      helper.writeString(buffer, packet.getOldSkinName());
      skin.getSkinData().checkLegacySkinSize();
      helper.writeByteArray(buffer, skin.getSkinData().getImage());
      skin.getCapeData().checkLegacyCapeSize();
      helper.writeByteArray(buffer, skin.getCapeData().getImage());
      helper.writeString(buffer, skin.getGeometryName());
      helper.writeString(buffer, skin.getGeometryData());
      buffer.writeBoolean(skin.isPremium());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerSkinPacket packet) {
      packet.setUuid(helper.readUuid(buffer));
      String skinId = helper.readString(buffer);
      packet.setNewSkinName(helper.readString(buffer));
      packet.setOldSkinName(helper.readString(buffer));
      ImageData skinData = ImageData.of(helper.readByteArray(buffer, 262144));
      ImageData capeData = ImageData.of(64, 32, helper.readByteArray(buffer, 8192));
      String geometryName = helper.readString(buffer);
      String geometryData = helper.readString(buffer);
      boolean premium = buffer.readBoolean();
      packet.setSkin(SerializedSkin.of(skinId, "", skinData, capeData, geometryName, geometryData, premium));
   }

   protected PlayerSkinSerializer_v291() {
   }
}
