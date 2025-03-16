package com.nukkitx.protocol.bedrock.codec.compat.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.LoginPacket;
import com.nukkitx.protocol.common.util.Preconditions;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import io.netty.util.AsciiString;
import java.nio.charset.StandardCharsets;
import org.jose4j.json.internal.json_simple.JSONArray;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.JSONValue;

public class LoginSerializerCompat implements BedrockPacketSerializer<LoginPacket> {
   public static final LoginSerializerCompat INSTANCE = new LoginSerializerCompat();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LoginPacket packet) {
      throw new UnsupportedOperationException();
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LoginPacket packet) {
      packet.setProtocolVersion(buffer.readInt());
      ByteBuf jwt = buffer.readSlice(VarInts.readUnsignedInt(buffer));
      Object json = JSONValue.parse(this.readString(jwt).toString());
      Preconditions.checkArgument(json instanceof JSONObject && ((JSONObject)json).containsKey("chain"), "Invalid login chain");
      Object chain = ((JSONObject)json).get("chain");
      Preconditions.checkArgument(chain instanceof JSONArray, "Expected JSON array for login chain");

      for(Object node : (JSONArray)chain) {
         Preconditions.checkArgument(node instanceof String, "Expected String in login chain");
         packet.getChain().add((String)node);
      }

      String value = (String)jwt.readCharSequence(jwt.readIntLE(), StandardCharsets.UTF_8);
      packet.setExtra(value);
   }

   protected AsciiString readString(ByteBuf buffer) {
      return (AsciiString)buffer.readCharSequence(buffer.readIntLE(), StandardCharsets.US_ASCII);
   }

   private LoginSerializerCompat() {
   }
}
