package com.nukkitx.protocol.bedrock.codec.v361.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.VideoStreamConnectPacket;
import io.netty.buffer.ByteBuf;

public class VideoStreamConnectSerializer_v361 implements BedrockPacketSerializer<VideoStreamConnectPacket> {
   public static final VideoStreamConnectSerializer_v361 INSTANCE = new VideoStreamConnectSerializer_v361();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, VideoStreamConnectPacket packet) {
      helper.writeString(buffer, packet.getAddress());
      buffer.writeFloatLE(packet.getScreenshotFrequency());
      buffer.writeByte(packet.getAction().ordinal());
      buffer.writeIntLE(packet.getWidth());
      buffer.writeIntLE(packet.getHeight());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, VideoStreamConnectPacket packet) {
      packet.setAddress(helper.readString(buffer));
      packet.setScreenshotFrequency(buffer.readFloatLE());
      packet.setAction(VideoStreamConnectPacket.Action.values()[buffer.readUnsignedByte()]);
      packet.setWidth(buffer.readIntLE());
      packet.setHeight(buffer.readIntLE());
   }

   protected VideoStreamConnectSerializer_v361() {
   }
}
