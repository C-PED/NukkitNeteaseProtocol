package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v340.serializer.AvailableCommandsSerializer_v340;
import com.nukkitx.protocol.bedrock.data.command.CommandData;
import com.nukkitx.protocol.bedrock.data.command.CommandEnumConstraint;
import com.nukkitx.protocol.bedrock.data.command.CommandEnumData;
import com.nukkitx.protocol.bedrock.data.command.CommandOverloadData;
import com.nukkitx.protocol.bedrock.data.command.CommandParam;
import com.nukkitx.protocol.bedrock.data.command.CommandParamData;
import com.nukkitx.protocol.bedrock.packet.AvailableCommandsPacket;
import com.nukkitx.protocol.common.util.LongKeys;
import com.nukkitx.protocol.common.util.SequencedHashSet;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.longs.LongObjectPair;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class AvailableCommandsSerializer_v388 extends AvailableCommandsSerializer_v340 {
   private static final CommandEnumConstraint[] CONSTRAINTS = CommandEnumConstraint.values();

   public AvailableCommandsSerializer_v388(TypeMap<CommandParam> paramTypeMap) {
      super(paramTypeMap);
   }

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AvailableCommandsPacket packet) {
      SequencedHashSet<String> enumValues = new SequencedHashSet<String>();
      SequencedHashSet<String> postFixes = new SequencedHashSet<String>();
      SequencedHashSet<CommandEnumData> enums = new SequencedHashSet<CommandEnumData>();
      SequencedHashSet<CommandEnumData> softEnums = new SequencedHashSet<CommandEnumData>();
      SequencedHashSet<LongObjectPair<Set<CommandEnumConstraint>>> enumConstraints = new SequencedHashSet<LongObjectPair<Set<CommandEnumConstraint>>>();

      for(CommandData data : packet.getCommands()) {
         if (data.getAliases() != null) {
            enumValues.addAll(data.getAliases().getValues().keySet());
            enums.add(data.getAliases());
         }

         for(CommandOverloadData overload : data.getOverloads()) {
            for(CommandParamData parameter : overload.getOverloads()) {
               CommandEnumData commandEnumData = parameter.getEnumData();
               if (commandEnumData != null) {
                  if (commandEnumData.isSoft()) {
                     softEnums.add(commandEnumData);
                  } else {
                     enums.add(commandEnumData);
                     int enumIndex = enums.indexOf(commandEnumData);
                     commandEnumData.getValues().forEach((key, constraints) -> {
                        enumValues.add(key);
                        if (!constraints.isEmpty()) {
                           int valueIndex = enumValues.indexOf(key);
                           enumConstraints.add(LongObjectPair.of(LongKeys.key(valueIndex, enumIndex), constraints));
                        }

                     });
                  }
               }

               String postfix = parameter.getPostfix();
               if (postfix != null) {
                  postFixes.add(postfix);
               }
            }
         }
      }

      Objects.requireNonNull(helper);
      helper.writeArray(buffer, enumValues, helper::writeString);
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, postFixes, helper::writeString);
      this.writeEnums(buffer, helper, enumValues, enums);
      helper.writeArray(buffer, packet.getCommands(), (buf, command) -> {
         this.writeCommand(buffer, helper, command, enums, softEnums, postFixes);
      });
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, softEnums, helper::writeCommandEnum);
      helper.writeArray(buffer, enumConstraints, this::writeEnumConstraint);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AvailableCommandsPacket packet) {
      SequencedHashSet<String> enumValues = new SequencedHashSet<String>();
      SequencedHashSet<String> postFixes = new SequencedHashSet<String>();
      SequencedHashSet<CommandEnumData> enums = new SequencedHashSet<CommandEnumData>();
      SequencedHashSet<CommandEnumData> softEnums = new SequencedHashSet<CommandEnumData>();
      Set<Consumer<List<CommandEnumData>>> softEnumParameters = new HashSet();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, enumValues, helper::readString);
      Objects.requireNonNull(helper);
      helper.readArray(buffer, postFixes, helper::readString);
      helper.readArray(buffer, enumValues, helper::readString);
      helper.readArray(buffer, postFixes, helper::readString);
      this.readEnums(buffer, helper, enumValues, enums);
      helper.readArray(buffer, packet.getCommands(), (buf, aHelper) ->
              this.readCommand(buf, aHelper, enums, postFixes, softEnumParameters));
      helper.readArray(buffer, softEnums, buf -> helper.readCommandEnum(buffer, true));
      this.readConstraints(buffer, helper, enums, enumValues);
      softEnumParameters.forEach((consumer) -> consumer.accept(softEnums));
   }

   protected void writeEnumConstraint(ByteBuf buffer, BedrockCodecHelper helper, LongObjectPair<Set<CommandEnumConstraint>> pair) {
      buffer.writeIntLE(LongKeys.high(pair.keyLong()));
      buffer.writeIntLE(LongKeys.low(pair.keyLong()));
      helper.writeArray(buffer, pair.value(), (buf, constraint) -> buf.writeByte(constraint.ordinal()));
   }

   protected void readConstraints(ByteBuf buffer, BedrockCodecHelper helper, List<CommandEnumData> enums, List<String> enumValues) {
      int count = VarInts.readUnsignedInt(buffer);
      for (int i = 0; i < count; i++) {
         String key = enumValues.get(buffer.readIntLE());
         CommandEnumData enumData = enums.get(buffer.readIntLE());
         Set<CommandEnumConstraint> constraints = enumData.getValues().get(key);
         helper.readArray(buffer, constraints, buf -> CONSTRAINTS[buf.readUnsignedByte()]);
      }
   }
}
