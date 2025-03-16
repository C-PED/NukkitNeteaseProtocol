package com.neteasemc.protocol.bedrock.v475.serializer;

import com.neteasemc.protocol.netgame.PyRpcPacket;
import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import io.netty.buffer.ByteBuf;

public class PyRpcSerializer_v475 implements BedrockPacketSerializer<PyRpcPacket> {
   public static final PyRpcSerializer_v475 INSTANCE = new PyRpcSerializer_v475();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PyRpcPacket packet) {
      helper.writeByteArray(buffer, packet.getData());
      buffer.writeIntLE((int)packet.getMsgId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PyRpcPacket packet) {
      packet.setData(helper.readByteArray(buffer));
      packet.setMsgId(buffer.readUnsignedIntLE());
   }
}
