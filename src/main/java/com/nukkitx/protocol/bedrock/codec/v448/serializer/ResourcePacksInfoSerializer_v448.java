package com.nukkitx.protocol.bedrock.codec.v448.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v422.serializer.ResourcePacksInfoSerializer_v422;
import com.nukkitx.protocol.bedrock.packet.ResourcePacksInfoPacket;
import io.netty.buffer.ByteBuf;

public class ResourcePacksInfoSerializer_v448 extends ResourcePacksInfoSerializer_v422 {
   public static final ResourcePacksInfoSerializer_v448 INSTANCE = new ResourcePacksInfoSerializer_v448();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
      buffer.writeBoolean(packet.isForcedToAccept());
      buffer.writeBoolean(packet.isScriptingEnabled());
      buffer.writeBoolean(packet.isForcingServerPacksEnabled());
      this.writePacks(buffer, packet.getBehaviorPackInfos(), helper, false);
      this.writePacks(buffer, packet.getResourcePackInfos(), helper, true);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
      packet.setForcedToAccept(buffer.readBoolean());
      packet.setScriptingEnabled(buffer.readBoolean());
      packet.setForcingServerPacksEnabled(buffer.readBoolean());
      this.readPacks(buffer, packet.getBehaviorPackInfos(), helper, false);
      this.readPacks(buffer, packet.getResourcePackInfos(), helper, true);
   }

   protected ResourcePacksInfoSerializer_v448() {
   }
}
