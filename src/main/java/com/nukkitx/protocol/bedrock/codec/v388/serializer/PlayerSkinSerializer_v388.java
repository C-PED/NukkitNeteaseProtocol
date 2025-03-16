package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PlayerSkinPacket;
import io.netty.buffer.ByteBuf;

public class PlayerSkinSerializer_v388 implements BedrockPacketSerializer<PlayerSkinPacket> {
   public static final PlayerSkinSerializer_v388 INSTANCE = new PlayerSkinSerializer_v388();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerSkinPacket packet) {
      helper.writeUuid(buffer, packet.getUuid());
      helper.writeSkin(buffer, packet.getSkin());
      helper.writeString(buffer, packet.getNewSkinName());
      helper.writeString(buffer, packet.getOldSkinName());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerSkinPacket packet) {
      packet.setUuid(helper.readUuid(buffer));
      packet.setSkin(helper.readSkin(buffer));
      packet.setNewSkinName(helper.readString(buffer));
      packet.setOldSkinName(helper.readString(buffer));
   }

   protected PlayerSkinSerializer_v388() {
   }
}
