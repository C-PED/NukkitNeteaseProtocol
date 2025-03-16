package com.nukkitx.protocol.bedrock.codec.v589.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v582.serializer.StartGameSerializer_v582;
import com.nukkitx.protocol.bedrock.data.NetworkPermissions;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import io.netty.buffer.ByteBuf;

public class StartGameSerializer_v589 extends StartGameSerializer_v582 {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      super.serialize(buffer, helper, packet);
      this.writeNetworkPermissions(buffer, helper, packet.getNetworkPermissions());
      buffer.writeBoolean(packet.isSpigot());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setNetworkPermissions(this.readNetworkPermissions(buffer, helper));
   }

   protected NetworkPermissions readNetworkPermissions(ByteBuf buffer, BedrockCodecHelper helper) {
      boolean serverAuthSound = buffer.readBoolean();
      return new NetworkPermissions(serverAuthSound);
   }

   protected void writeNetworkPermissions(ByteBuf buffer, BedrockCodecHelper helper, NetworkPermissions permissions) {
      buffer.writeBoolean(permissions.isServerAuthSounds());
   }
}
