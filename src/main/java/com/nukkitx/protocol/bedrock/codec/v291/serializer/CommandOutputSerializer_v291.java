package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.command.CommandOutputMessage;
import com.nukkitx.protocol.bedrock.data.command.CommandOutputType;
import com.nukkitx.protocol.bedrock.packet.CommandOutputPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.Objects;

public class CommandOutputSerializer_v291 implements BedrockPacketSerializer<CommandOutputPacket> {
   public static final CommandOutputSerializer_v291 INSTANCE = new CommandOutputSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputPacket packet) {
      helper.writeCommandOrigin(buffer, packet.getCommandOriginData());
      buffer.writeByte(packet.getType().ordinal());
      VarInts.writeUnsignedInt(buffer, packet.getSuccessCount());
      helper.writeArray(buffer, packet.getMessages(), this::writeMessage);
      if (packet.getType() == CommandOutputType.DATA_SET) {
         helper.writeString(buffer, packet.getData());
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputPacket packet) {
      packet.setCommandOriginData(helper.readCommandOrigin(buffer));
      packet.setType(CommandOutputType.values()[buffer.readUnsignedByte()]);
      packet.setSuccessCount(VarInts.readUnsignedInt(buffer));
      helper.readArray(buffer, packet.getMessages(), this::readMessage);
      if (packet.getType() == CommandOutputType.DATA_SET) {
         packet.setData(helper.readString(buffer));
      }

   }

   public CommandOutputMessage readMessage(ByteBuf buffer, BedrockCodecHelper helper) {
      boolean internal = buffer.readBoolean();
      String messageId = helper.readString(buffer);
      String[] var10002 = new String[0];
      Objects.requireNonNull(helper);
      String[] parameters = (String[])helper.readArray(buffer, var10002, helper::readString);
      return new CommandOutputMessage(internal, messageId, parameters);
   }

   public void writeMessage(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputMessage outputMessage) {
      Objects.requireNonNull(outputMessage, "CommandOutputMessage is null");
      buffer.writeBoolean(outputMessage.isInternal());
      helper.writeString(buffer, outputMessage.getMessageId());
      String[] var10002 = outputMessage.getParameters();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeString);
   }

   protected CommandOutputSerializer_v291() {
   }
}
