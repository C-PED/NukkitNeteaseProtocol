package com.nukkitx.protocol.bedrock.codec.v594;

import com.neteasemc.protocol.bedrock.v475.serializer.ConfirmSkinSerializer_v475;
import com.neteasemc.protocol.bedrock.v475.serializer.NeteaseJsonSerializer_v475;
import com.neteasemc.protocol.bedrock.v475.serializer.PyRpcSerializer_v475;
import com.neteasemc.protocol.netgame.NetworkGamePacket;
import com.neteasemc.protocol.netgame.PyRpcPacket;
import com.neteasemc.protocol.netgame.v486.serializer.NetworkGameSerializer_v486;
import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v575.BedrockCodecHelper_v575;
import com.nukkitx.protocol.bedrock.codec.v575.Bedrock_v575;
import com.nukkitx.protocol.bedrock.codec.v582.Bedrock_v582;
import com.nukkitx.protocol.bedrock.codec.v589.Bedrock_v589;
import com.nukkitx.protocol.bedrock.codec.v594.serializer.AgentAnimationSerializer_v594;
import com.nukkitx.protocol.bedrock.codec.v594.serializer.AvailableCommandsSerializer_v594;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.command.CommandParam;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataFormat;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataTypes;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.bedrock.packet.AgentAnimationPacket;
import com.nukkitx.protocol.bedrock.packet.AvailableCommandsPacket;
import com.nukkitx.protocol.bedrock.packet.ConfirmSkinPacket;
import com.nukkitx.protocol.bedrock.packet.NeteaseJsonPacket;
import com.nukkitx.protocol.bedrock.packet.ScriptCustomEventPacket;
import com.nukkitx.protocol.bedrock.transformer.FlagTransformer;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v594 extends Bedrock_v589 {
   protected static final TypeMap<EntityFlag> ENTITY_FLAGS;
   protected static final EntityDataTypeMap ENTITY_DATA;
   protected static final TypeMap<CommandParam> COMMAND_PARAMS;
   public static final BedrockCodec CODEC;

   static {
      ENTITY_FLAGS = Bedrock_v575.ENTITY_FLAGS.toBuilder().insert(114, EntityFlag.CRAWLING).build();
      ENTITY_DATA = Bedrock_v589.ENTITY_DATA.toBuilder().insert(EntityDataTypes.COLLISION_BOX, 130, EntityDataFormat.VECTOR3F).update(EntityDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0)).update(EntityDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1)).build();
      COMMAND_PARAMS = Bedrock_v582.COMMAND_PARAMS.toBuilder().insert(134217728, CommandParam.CHAINED_COMMAND).build();
      CODEC = Bedrock_v589.CODEC.toBuilder().raknetProtocolVersion(11).protocolVersion(594).minecraftVersion("1.20.10").helper(() -> new BedrockCodecHelper_v575(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS)).deregisterPacket(ScriptCustomEventPacket.class).updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v594(COMMAND_PARAMS)).registerPacket(AgentAnimationPacket::new, new AgentAnimationSerializer_v594(), 304, PacketRecipient.CLIENT).registerPacket(PyRpcPacket.class, PyRpcSerializer_v475.INSTANCE, 200, PacketRecipient.BOTH).registerPacket(NeteaseJsonPacket.class, NeteaseJsonSerializer_v475.INSTANCE, 203, PacketRecipient.BOTH).registerPacket(ConfirmSkinPacket.class, ConfirmSkinSerializer_v475.INSTANCE, 228, PacketRecipient.BOTH).registerPacket(NetworkGamePacket.class, NetworkGameSerializer_v486.INSTANCE, 254, PacketRecipient.BOTH).build();
   }
}
