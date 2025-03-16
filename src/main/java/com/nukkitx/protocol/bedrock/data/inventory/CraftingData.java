package com.nukkitx.protocol.bedrock.data.inventory;

import com.nukkitx.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class CraftingData {
   private final CraftingDataType type;
   private final String recipeId;
   private final int width;
   private final int height;
   private final int inputId;
   private final int inputDamage;
   private final List<ItemData> inputs;
   private final List<ItemData> outputs;
   private final UUID uuid;
   private final String craftingTag;
   private final int priority;
   private final int networkId;

   public CraftingData(CraftingDataType type, String recipeId, int width, int height, int inputId, int inputDamage, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int priority) {
      this(type, recipeId, width, height, inputId, inputDamage, inputs, outputs, uuid, craftingTag, priority, -1);
   }

   public CraftingData(CraftingDataType type, int width, int height, int inputId, int inputDamage, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int networkId) {
      this(type, (String)null, width, height, inputId, inputDamage, inputs, outputs, uuid, craftingTag, 0, networkId);
   }

   public CraftingData(CraftingDataType type, int width, int height, int inputId, int inputDamage, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag) {
      this(type, (String)null, width, height, inputId, inputDamage, inputs, outputs, uuid, craftingTag, 0, -1);
   }

   public static CraftingData fromFurnaceData(int inputId, int inputDamage, ItemData output, String craftingTag, int networkId) {
      return new CraftingData(CraftingDataType.FURNACE_DATA, (String)null, -1, -1, inputId, inputDamage, (List)null, new ObjectArrayList(Collections.singleton(output)), (UUID)null, craftingTag, -1, networkId);
   }

   public static CraftingData fromFurnaceData(int inputId, int inputDamage, ItemData output, String craftingTag) {
      return new CraftingData(CraftingDataType.FURNACE_DATA, (String)null, -1, -1, inputId, inputDamage, (List)null, new ObjectArrayList(Collections.singleton(output)), (UUID)null, craftingTag, -1, -1);
   }

   public static CraftingData fromFurnace(int inputId, ItemData input, String craftingTag, int networkId) {
      return new CraftingData(CraftingDataType.FURNACE, (String)null, -1, -1, inputId, -1, (List)null, new ObjectArrayList(Collections.singleton(input)), (UUID)null, craftingTag, -1, networkId);
   }

   public static CraftingData fromFurnace(int inputId, ItemData input, String craftingTag) {
      return new CraftingData(CraftingDataType.FURNACE, (String)null, -1, -1, inputId, -1, (List)null, new ObjectArrayList(Collections.singleton(input)), (UUID)null, craftingTag, -1, -1);
   }

   public static CraftingData fromShapeless(String recipeId, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int priority, int networkId) {
      return new CraftingData(CraftingDataType.SHAPELESS, recipeId, -1, -1, -1, -1, inputs, outputs, uuid, craftingTag, priority, networkId);
   }

   public static CraftingData fromShapeless(String recipeId, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int priority) {
      return new CraftingData(CraftingDataType.SHAPELESS, recipeId, -1, -1, -1, -1, inputs, outputs, uuid, craftingTag, priority, -1);
   }

   public static CraftingData fromShaped(String recipeId, int width, int height, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int priority, int networkId) {
      return new CraftingData(CraftingDataType.SHAPED, recipeId, width, height, -1, -1, inputs, outputs, uuid, craftingTag, priority, networkId);
   }

   public static CraftingData fromShaped(String recipeId, int width, int height, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int priority) {
      return new CraftingData(CraftingDataType.SHAPED, recipeId, width, height, -1, -1, inputs, outputs, uuid, craftingTag, priority, -1);
   }

   public static CraftingData fromShapelessChemistry(String recipeId, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int priority, int networkId) {
      return new CraftingData(CraftingDataType.SHAPELESS_CHEMISTRY, recipeId, -1, -1, -1, -1, inputs, outputs, uuid, craftingTag, priority, networkId);
   }

   public static CraftingData fromShapelessChemistry(String recipeId, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int priority) {
      return new CraftingData(CraftingDataType.SHAPELESS_CHEMISTRY, recipeId, -1, -1, -1, -1, inputs, outputs, uuid, craftingTag, priority, -1);
   }

   public static CraftingData fromShapedChemistry(String recipeId, int width, int height, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int priority, int networkId) {
      return new CraftingData(CraftingDataType.SHAPED_CHEMISTRY, recipeId, width, height, -1, -1, inputs, outputs, uuid, craftingTag, priority, networkId);
   }

   public static CraftingData fromShapedChemistry(String recipeId, int width, int height, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int priority) {
      return new CraftingData(CraftingDataType.SHAPED_CHEMISTRY, recipeId, width, height, -1, -1, inputs, outputs, uuid, craftingTag, priority, -1);
   }

   public static CraftingData fromShulkerBox(String recipeId, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int priority, int networkId) {
      return new CraftingData(CraftingDataType.SHULKER_BOX, recipeId, -1, -1, -1, -1, inputs, outputs, uuid, craftingTag, priority, networkId);
   }

   public static CraftingData fromShulkerBox(String recipeId, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int priority) {
      return new CraftingData(CraftingDataType.SHULKER_BOX, recipeId, -1, -1, -1, -1, inputs, outputs, uuid, craftingTag, priority, -1);
   }

   public static CraftingData fromMulti(UUID uuid, int networkId) {
      return new CraftingData(CraftingDataType.MULTI, (String)null, -1, -1, -1, -1, (List)null, (List)null, uuid, (String)null, -1, networkId);
   }

   public static CraftingData fromMulti(UUID uuid) {
      return new CraftingData(CraftingDataType.MULTI, (String)null, -1, -1, -1, -1, (List)null, (List)null, uuid, (String)null, -1, -1);
   }

   public static CraftingData fromShapeless(List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int networkId) {
      return new CraftingData(CraftingDataType.SHAPELESS, "", -1, -1, -1, -1, inputs, outputs, uuid, craftingTag, 0, networkId);
   }

   public static CraftingData fromShapeless(List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag) {
      return new CraftingData(CraftingDataType.SHAPELESS, "", -1, -1, -1, -1, inputs, outputs, uuid, craftingTag, 0, -1);
   }

   public static CraftingData fromShaped(int width, int height, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int networkId) {
      return new CraftingData(CraftingDataType.SHAPED, "", width, height, -1, -1, inputs, outputs, uuid, craftingTag, 0, networkId);
   }

   public static CraftingData fromShaped(int width, int height, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag) {
      return new CraftingData(CraftingDataType.SHAPED, "", width, height, -1, -1, inputs, outputs, uuid, craftingTag, 0, -1);
   }

   public static CraftingData fromShapelessChemistry(List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int networkId) {
      return new CraftingData(CraftingDataType.SHAPELESS_CHEMISTRY, "", -1, -1, -1, -1, inputs, outputs, uuid, craftingTag, 0, networkId);
   }

   public static CraftingData fromShapelessChemistry(List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag) {
      return new CraftingData(CraftingDataType.SHAPELESS_CHEMISTRY, "", -1, -1, -1, -1, inputs, outputs, uuid, craftingTag, 0, -1);
   }

   public static CraftingData fromShapedChemistry(int width, int height, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int networkId) {
      return new CraftingData(CraftingDataType.SHAPED_CHEMISTRY, "", width, height, -1, -1, inputs, outputs, uuid, craftingTag, 0, networkId);
   }

   public static CraftingData fromShapedChemistry(int width, int height, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag) {
      return new CraftingData(CraftingDataType.SHAPED_CHEMISTRY, "", width, height, -1, -1, inputs, outputs, uuid, craftingTag, 0, -1);
   }

   public static CraftingData fromShulkerBox(List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int networkId) {
      return new CraftingData(CraftingDataType.SHULKER_BOX, "", -1, -1, -1, -1, inputs, outputs, uuid, craftingTag, 0, networkId);
   }

   public static CraftingData fromShulkerBox(List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag) {
      return new CraftingData(CraftingDataType.SHULKER_BOX, "", -1, -1, -1, -1, inputs, outputs, uuid, craftingTag, 0, -1);
   }

   public CraftingDataType getType() {
      return this.type;
   }

   public String getRecipeId() {
      return this.recipeId;
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public int getInputId() {
      return this.inputId;
   }

   public int getInputDamage() {
      return this.inputDamage;
   }

   public List<ItemData> getInputs() {
      return this.inputs;
   }

   public List<ItemData> getOutputs() {
      return this.outputs;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public String getCraftingTag() {
      return this.craftingTag;
   }

   public int getPriority() {
      return this.priority;
   }

   public int getNetworkId() {
      return this.networkId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CraftingData)) {
         return false;
      } else {
         CraftingData other = (CraftingData)o;
         if (this.getWidth() != other.getWidth()) {
            return false;
         } else if (this.getHeight() != other.getHeight()) {
            return false;
         } else if (this.getInputId() != other.getInputId()) {
            return false;
         } else if (this.getInputDamage() != other.getInputDamage()) {
            return false;
         } else if (this.getPriority() != other.getPriority()) {
            return false;
         } else if (this.getNetworkId() != other.getNetworkId()) {
            return false;
         } else {
            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$recipeId = this.getRecipeId();
            Object other$recipeId = other.getRecipeId();
            if (this$recipeId == null) {
               if (other$recipeId != null) {
                  return false;
               }
            } else if (!this$recipeId.equals(other$recipeId)) {
               return false;
            }

            Object this$inputs = this.getInputs();
            Object other$inputs = other.getInputs();
            if (this$inputs == null) {
               if (other$inputs != null) {
                  return false;
               }
            } else if (!this$inputs.equals(other$inputs)) {
               return false;
            }

            Object this$outputs = this.getOutputs();
            Object other$outputs = other.getOutputs();
            if (this$outputs == null) {
               if (other$outputs != null) {
                  return false;
               }
            } else if (!this$outputs.equals(other$outputs)) {
               return false;
            }

            Object this$uuid = this.getUuid();
            Object other$uuid = other.getUuid();
            if (this$uuid == null) {
               if (other$uuid != null) {
                  return false;
               }
            } else if (!this$uuid.equals(other$uuid)) {
               return false;
            }

            Object this$craftingTag = this.getCraftingTag();
            Object other$craftingTag = other.getCraftingTag();
            if (this$craftingTag == null) {
               if (other$craftingTag != null) {
                  return false;
               }
            } else if (!this$craftingTag.equals(other$craftingTag)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getWidth();
      result = result * 59 + this.getHeight();
      result = result * 59 + this.getInputId();
      result = result * 59 + this.getInputDamage();
      result = result * 59 + this.getPriority();
      result = result * 59 + this.getNetworkId();
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $recipeId = this.getRecipeId();
      result = result * 59 + ($recipeId == null ? 43 : $recipeId.hashCode());
      Object $inputs = this.getInputs();
      result = result * 59 + ($inputs == null ? 43 : $inputs.hashCode());
      Object $outputs = this.getOutputs();
      result = result * 59 + ($outputs == null ? 43 : $outputs.hashCode());
      Object $uuid = this.getUuid();
      result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
      Object $craftingTag = this.getCraftingTag();
      result = result * 59 + ($craftingTag == null ? 43 : $craftingTag.hashCode());
      return result;
   }

   public String toString() {
      return "CraftingData(type=" + this.getType() + ", recipeId=" + this.getRecipeId() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ", inputId=" + this.getInputId() + ", inputDamage=" + this.getInputDamage() + ", inputs=" + this.getInputs() + ", outputs=" + this.getOutputs() + ", uuid=" + this.getUuid() + ", craftingTag=" + this.getCraftingTag() + ", priority=" + this.getPriority() + ", networkId=" + this.getNetworkId() + ")";
   }

   public CraftingData(CraftingDataType type, String recipeId, int width, int height, int inputId, int inputDamage, List<ItemData> inputs, List<ItemData> outputs, UUID uuid, String craftingTag, int priority, int networkId) {
      this.type = type;
      this.recipeId = recipeId;
      this.width = width;
      this.height = height;
      this.inputId = inputId;
      this.inputDamage = inputDamage;
      this.inputs = inputs;
      this.outputs = outputs;
      this.uuid = uuid;
      this.craftingTag = craftingTag;
      this.priority = priority;
      this.networkId = networkId;
   }
}
