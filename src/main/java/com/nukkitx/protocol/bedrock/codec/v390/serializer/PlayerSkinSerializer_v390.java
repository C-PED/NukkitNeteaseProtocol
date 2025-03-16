package com.nukkitx.protocol.bedrock.codec.v390.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v388.serializer.PlayerSkinSerializer_v388;
import com.nukkitx.protocol.bedrock.packet.PlayerSkinPacket;
import io.netty.buffer.ByteBuf;

public class PlayerSkinSerializer_v390 extends PlayerSkinSerializer_v388 {
   public static final PlayerSkinSerializer_v390 INSTANCE = new PlayerSkinSerializer_v390();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerSkinPacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeBoolean(packet.isTrustedSkin());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerSkinPacket packet) {
      super.deserialize(buffer, helper, packet);
      if (buffer.isReadable()) {
         packet.setTrustedSkin(buffer.readBoolean());
      }

   }

   protected PlayerSkinSerializer_v390() {
   }
}
