package com.nukkitx.protocol.bedrock.codec.v575.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.camera.CameraEase;
import com.nukkitx.protocol.bedrock.data.camera.CameraFadeInstruction;
import com.nukkitx.protocol.bedrock.data.camera.CameraSetInstruction;
import com.nukkitx.protocol.bedrock.packet.CameraInstructionPacket;
import com.nukkitx.protocol.common.NamedDefinition;
import com.nukkitx.protocol.common.util.DefinitionUtils;
import com.nukkitx.protocol.common.util.OptionalBoolean;
import com.nukkitx.protocol.common.util.Preconditions;
import io.netty.buffer.ByteBuf;
import java.awt.Color;
import java.util.List;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;

public class CameraInstructionSerializer_v575 implements BedrockPacketSerializer<CameraInstructionPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
      NbtMapBuilder tag = NbtMap.builder();
      if (packet.getSetInstruction() != null) {
         CameraSetInstruction set = packet.getSetInstruction();
         DefinitionUtils.checkDefinition(helper.getCameraPresetDefinitions(), set.getPreset());
         NbtMapBuilder builder = NbtMap.builder().putInt("preset", set.getPreset().getRuntimeId());
         if (set.getEase() != null) {
            builder.putCompound("ease", NbtMap.builder().putString("type", set.getEase().getEaseType().getSerializeName()).putFloat("time", set.getEase().getTime()).build());
         }

         if (set.getPos() != null) {
            builder.putCompound("pos", NbtMap.builder().putList("pos", NbtType.FLOAT, new Float[]{set.getPos().getX(), set.getPos().getY(), set.getPos().getZ()}).build());
         }

         if (set.getRot() != null) {
            builder.putCompound("rot", NbtMap.builder().putFloat("x", set.getRot().getX()).putFloat("y", set.getRot().getY()).build());
         }

         if (set.getDefaultPreset().isPresent()) {
            builder.putBoolean("default", set.getDefaultPreset().getAsBoolean());
         }

         tag.put("set", builder.build());
      }

      if (packet.getClear().isPresent()) {
         tag.putBoolean("clear", packet.getClear().getAsBoolean());
      }

      if (packet.getFadeInstruction() != null) {
         CameraFadeInstruction fade = packet.getFadeInstruction();
         NbtMapBuilder builder = NbtMap.builder();
         if (fade.getTimeData() != null) {
            builder.putCompound("time", NbtMap.builder().putFloat("fadeIn", fade.getTimeData().getFadeInTime()).putFloat("hold", fade.getTimeData().getWaitTime()).putFloat("fadeOut", fade.getTimeData().getFadeOutTime()).build());
         }

         if (fade.getColor() != null) {
            builder.putCompound("color", NbtMap.builder().putFloat("r", (float)fade.getColor().getRed() / 255.0F).putFloat("g", (float)fade.getColor().getBlue() / 255.0F).putFloat("b", (float)fade.getColor().getGreen() / 255.0F).build());
         }

         tag.put("fade", builder.build());
      }

      helper.writeTag(buffer, tag.build());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraInstructionPacket packet) {
      NbtMap tag = (NbtMap)helper.readTag(buffer, NbtMap.class);
      if (tag.containsKey("set", NbtType.COMPOUND)) {
         CameraSetInstruction set = new CameraSetInstruction();
         NbtMap setTag = tag.getCompound("set");
         int runtimeId = setTag.getInt("preset");
         NamedDefinition definition = (NamedDefinition)helper.getCameraPresetDefinitions().getDefinition(runtimeId);
         Preconditions.checkNotNull(definition, "Unknown camera preset " + runtimeId);
         set.setPreset(definition);
         if (setTag.containsKey("ease", NbtType.COMPOUND)) {
            NbtMap easeTag = setTag.getCompound("ease");
            CameraEase type = CameraEase.fromName(easeTag.getString("type"));
            float time = easeTag.getFloat("time");
            set.setEase(new CameraSetInstruction.EaseData(type, time));
         }

         if (setTag.containsKey("pos", NbtType.COMPOUND)) {
            List<Float> floats = setTag.getCompound("pos").getList("pos", NbtType.FLOAT);
            float x = floats.size() > 0 ? (Float)floats.get(0) : 0.0F;
            float y = floats.size() > 1 ? (Float)floats.get(1) : 0.0F;
            float z = floats.size() > 2 ? (Float)floats.get(2) : 0.0F;
            set.setPos(Vector3f.from(x, y, z));
         }

         if (setTag.containsKey("rot", NbtType.COMPOUND)) {
            NbtMap rot = setTag.getCompound("rot");
            float pitch = rot.containsKey("x", NbtType.FLOAT) ? rot.getFloat("x") : 0.0F;
            float yaw = rot.containsKey("y", NbtType.FLOAT) ? rot.getFloat("y") : 0.0F;
            set.setRot(Vector2f.from(pitch, yaw));
         }

         if (setTag.containsKey("default", NbtType.BYTE)) {
            set.setDefaultPreset(OptionalBoolean.of(setTag.getBoolean("default")));
         }

         packet.setSetInstruction(set);
      }

      if (tag.containsKey("clear", NbtType.BYTE)) {
         packet.setClear(OptionalBoolean.of(tag.getBoolean("clear")));
      }

      if (tag.containsKey("fade", NbtType.COMPOUND)) {
         CameraFadeInstruction fade = new CameraFadeInstruction();
         NbtMap fadeTag = tag.getCompound("fade");
         if (fadeTag.containsKey("time", NbtType.COMPOUND)) {
            NbtMap timeTag = fadeTag.getCompound("time");
            float fadeIn = timeTag.getFloat("fadeIn");
            float wait = timeTag.getFloat("hold");
            float fadeout = timeTag.getFloat("fadeOut");
            fade.setTimeData(new CameraFadeInstruction.TimeData(fadeIn, wait, fadeout));
         }

         if (fadeTag.containsKey("color", NbtType.COMPOUND)) {
            NbtMap colorTag = tag.getCompound("color");
            fade.setColor(new Color((int)(colorTag.getFloat("r") * 255.0F), (int)(colorTag.getFloat("b") * 255.0F), (int)(colorTag.getFloat("g") * 255.0F)));
         }

         packet.setFadeInstruction(fade);
      }

   }
}
