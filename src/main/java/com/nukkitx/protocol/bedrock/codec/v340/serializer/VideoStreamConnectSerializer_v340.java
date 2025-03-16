package com.nukkitx.protocol.bedrock.codec.v340.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.VideoStreamConnectPacket;
import io.netty.buffer.ByteBuf;

public class VideoStreamConnectSerializer_v340 implements BedrockPacketSerializer<VideoStreamConnectPacket> {
   public static final VideoStreamConnectSerializer_v340 INSTANCE = new VideoStreamConnectSerializer_v340();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, VideoStreamConnectPacket packet) {
      helper.writeString(buffer, packet.getAddress());
      buffer.writeFloatLE(packet.getScreenshotFrequency());
      buffer.writeByte(packet.getAction().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, VideoStreamConnectPacket packet) {
      packet.setAddress(helper.readString(buffer));
      packet.setScreenshotFrequency(buffer.readFloatLE());
      packet.setAction(VideoStreamConnectPacket.Action.values()[buffer.readUnsignedByte()]);
   }

   protected VideoStreamConnectSerializer_v340() {
   }
}
