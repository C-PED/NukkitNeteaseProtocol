package com.nukkitx.protocol.bedrock.codec.v544.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.MapInfoRequestSerializer_v291;
import com.nukkitx.protocol.bedrock.data.map.MapPixel;
import com.nukkitx.protocol.bedrock.packet.MapInfoRequestPacket;
import io.netty.buffer.ByteBuf;

public class MapInfoRequestSerializer_v544 extends MapInfoRequestSerializer_v291 {
   @Override
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MapInfoRequestPacket packet) {
      super.serialize(buffer, helper, packet);

      helper.writeArray(buffer, packet.getPixels(), ByteBuf::writeIntLE, (buf, aHelper, pixel) -> {
         buf.writeIntLE(pixel.getPixel());
         buf.writeShortLE(pixel.getIndex());
      });
   }

   @Override
   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MapInfoRequestPacket packet) {
      super.deserialize(buffer, helper, packet);

      helper.readArray(buffer, packet.getPixels(), ByteBuf::readUnsignedIntLE, (buf, aHelper) -> {
         int pixel = buf.readIntLE();
         int index = buf.readUnsignedShortLE();
         return new MapPixel(pixel, index);
      });
   }
}
