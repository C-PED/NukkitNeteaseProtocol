package com.nukkitx.protocol.bedrock.codec.v589.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v388.serializer.EmoteSerializer_v388;
import com.nukkitx.protocol.bedrock.packet.EmotePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class EmoteSerializer_v589 extends EmoteSerializer_v388 {
   public static final EmoteSerializer_v589 INSTANCE = new EmoteSerializer_v589();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EmotePacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      helper.writeString(buffer, packet.getEmoteId());
      helper.writeString(buffer, packet.getXuid());
      helper.writeString(buffer, packet.getPlatformId());
      this.writeFlags(buffer, helper, packet.getFlags());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EmotePacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setEmoteId(helper.readString(buffer));
      packet.setXuid(helper.readString(buffer));
      packet.setPlatformId(helper.readString(buffer));
      this.readFlags(buffer, helper, packet.getFlags());
   }

   protected EmoteSerializer_v589() {
   }
}
