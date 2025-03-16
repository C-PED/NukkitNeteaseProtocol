package com.nukkitx.protocol.bedrock.data.command;

import com.nukkitx.protocol.common.util.TypeMap;

public class CommandParam {
   public static final CommandParam UNKNOWN;
   public static final CommandParam INT;
   public static final CommandParam FLOAT;
   public static final CommandParam VALUE;
   public static final CommandParam R_VALUE;
   public static final CommandParam WILDCARD_INT;
   public static final CommandParam OPERATOR;
   public static final CommandParam COMPARE_OPERATOR;
   public static final CommandParam TARGET;
   public static final CommandParam UNKNOWN_STANDALONE;
   public static final CommandParam WILDCARD_TARGET;
   public static final CommandParam UNKNOWN_NON_ID;
   public static final CommandParam SCORE_ARG;
   public static final CommandParam SCORE_ARGS;
   public static final CommandParam SCORE_SELECT_PARAM;
   public static final CommandParam SCORE_SELECTOR;
   public static final CommandParam TAG_SELECTOR;
   public static final CommandParam FILE_PATH;
   public static final CommandParam FILE_PATH_VAL;
   public static final CommandParam FILE_PATH_CONT;
   public static final CommandParam INT_RANGE_VAL;
   public static final CommandParam INT_RANGE_POST_VAL;
   public static final CommandParam INT_RANGE;
   public static final CommandParam INT_RANGE_FULL;
   public static final CommandParam SEL_ARGS;
   public static final CommandParam ARGS;
   public static final CommandParam ARG;
   public static final CommandParam MARG;
   public static final CommandParam MVALUE;
   public static final CommandParam NAME;
   public static final CommandParam TYPE;
   public static final CommandParam FAMILY;
   public static final CommandParam PERMISSION;
   public static final CommandParam PERMISSIONS;
   public static final CommandParam PERMISSION_SELECTOR;
   public static final CommandParam PERMISSION_ELEMENT;
   public static final CommandParam PERMISSION_ELEMENTS;
   public static final CommandParam TAG;
   public static final CommandParam HAS_ITEM_ELEMENT;
   public static final CommandParam HAS_ITEM_ELEMENTS;
   public static final CommandParam HAS_ITEM;
   public static final CommandParam HAS_ITEMS;
   public static final CommandParam HAS_ITEM_SELECTOR;
   public static final CommandParam EQUIPMENT_SLOTS;
   public static final CommandParam STRING;
   public static final CommandParam ID_CONT;
   public static final CommandParam COORD_X_INT;
   public static final CommandParam COORD_Y_INT;
   public static final CommandParam COORD_Z_INT;
   public static final CommandParam COORD_X_FLOAT;
   public static final CommandParam COORD_Y_FLOAT;
   public static final CommandParam COORD_Z_FLOAT;
   public static final CommandParam BLOCK_POSITION;
   public static final CommandParam POSITION;
   public static final CommandParam MESSAGE_XP;
   public static final CommandParam MESSAGE;
   public static final CommandParam MESSAGE_ROOT;
   public static final CommandParam POST_SELECTOR;
   public static final CommandParam TEXT;
   public static final CommandParam TEXT_CONT;
   public static final CommandParam JSON_VALUE;
   public static final CommandParam JSON_FIELD;
   public static final CommandParam JSON;
   public static final CommandParam JSON_OBJECT_FIELDS;
   public static final CommandParam JSON_OBJECT_CONT;
   public static final CommandParam JSON_ARRAY;
   public static final CommandParam JSON_ARRAY_VALUES;
   public static final CommandParam JSON_ARRAY_CONT;
   public static final CommandParam BLOCK_STATE;
   public static final CommandParam BLOCK_STATE_KEY;
   public static final CommandParam BLOCK_STATE_VALUE;
   public static final CommandParam BLOCK_STATE_VALUES;
   public static final CommandParam BLOCK_STATES;
   public static final CommandParam BLOCK_STATES_CONT;
   public static final CommandParam COMMAND;
   public static final CommandParam SLASH_COMMAND;
   public static final CommandParam CHAINED_COMMAND;
   public static final CommandParam RATIONAL_RANGE_VAL;
   public static final CommandParam RATIONAL_RANGE_POST_VAL;
   public static final CommandParam RATIONAL_RANGE;
   public static final CommandParam RATIONAL_RANGE_FULL;
   public static final CommandParam PROPERTY_VALUE;
   public static final CommandParam HAS_PROPERTY_PARAM_VALUE;
   public static final CommandParam HAS_PROPERTY_PARAM_ENUM_VALUE;
   public static final CommandParam HAS_PROPERTY_ARG;
   public static final CommandParam HAS_PROPERTY_ARGS;
   public static final CommandParam HAS_PROPERTY_ELEMENT;
   public static final CommandParam HAS_PROPERTY_ELEMENTS;
   public static final CommandParam HAS_PROPERTY_SELECTOR;
   private final CommandParamType paramType;
   private final int defaultValue;

   public CommandParam(CommandParamType paramType) {
      this.paramType = paramType;
      this.defaultValue = -1;
   }

   public CommandParam(int defaultValue) {
      this.defaultValue = defaultValue;
      this.paramType = null;
   }

   public int getValue(TypeMap<CommandParam> typeMap) {
      return this.defaultValue <= 0 && this.paramType != null ? typeMap.getId(this) : this.defaultValue;
   }

   public String toString() {
      return "CommandParam(type=" + (this.paramType == null ? "UNKNOWN" : this.paramType.name()) + ", defaultValue=" + this.defaultValue + ")";
   }

   public CommandParam(CommandParamType paramType, int defaultValue) {
      this.paramType = paramType;
      this.defaultValue = defaultValue;
   }

   public CommandParamType getParamType() {
      return this.paramType;
   }

   public int getDefaultValue() {
      return this.defaultValue;
   }

   static {
      UNKNOWN = new CommandParam(CommandParamType.UNKNOWN);
      INT = new CommandParam(CommandParamType.INT);
      FLOAT = new CommandParam(CommandParamType.FLOAT);
      VALUE = new CommandParam(CommandParamType.VALUE);
      R_VALUE = new CommandParam(CommandParamType.R_VALUE);
      WILDCARD_INT = new CommandParam(CommandParamType.WILDCARD_INT);
      OPERATOR = new CommandParam(CommandParamType.OPERATOR);
      COMPARE_OPERATOR = new CommandParam(CommandParamType.COMPARE_OPERATOR);
      TARGET = new CommandParam(CommandParamType.TARGET);
      UNKNOWN_STANDALONE = new CommandParam(CommandParamType.UNKNOWN_STANDALONE);
      WILDCARD_TARGET = new CommandParam(CommandParamType.WILDCARD_TARGET);
      UNKNOWN_NON_ID = new CommandParam(CommandParamType.UNKNOWN_NON_ID);
      SCORE_ARG = new CommandParam(CommandParamType.SCORE_ARG);
      SCORE_ARGS = new CommandParam(CommandParamType.SCORE_ARGS);
      SCORE_SELECT_PARAM = new CommandParam(CommandParamType.SCORE_SELECT_PARAM);
      SCORE_SELECTOR = new CommandParam(CommandParamType.SCORE_SELECTOR);
      TAG_SELECTOR = new CommandParam(CommandParamType.TAG_SELECTOR);
      FILE_PATH = new CommandParam(CommandParamType.FILE_PATH);
      FILE_PATH_VAL = new CommandParam(CommandParamType.FILE_PATH_VAL);
      FILE_PATH_CONT = new CommandParam(CommandParamType.FILE_PATH_CONT);
      INT_RANGE_VAL = new CommandParam(CommandParamType.INT_RANGE_VAL);
      INT_RANGE_POST_VAL = new CommandParam(CommandParamType.INT_RANGE_POST_VAL);
      INT_RANGE = new CommandParam(CommandParamType.INT_RANGE);
      INT_RANGE_FULL = new CommandParam(CommandParamType.INT_RANGE_FULL);
      SEL_ARGS = new CommandParam(CommandParamType.SEL_ARGS);
      ARGS = new CommandParam(CommandParamType.ARGS);
      ARG = new CommandParam(CommandParamType.ARG);
      MARG = new CommandParam(CommandParamType.MARG);
      MVALUE = new CommandParam(CommandParamType.MVALUE);
      NAME = new CommandParam(CommandParamType.NAME);
      TYPE = new CommandParam(CommandParamType.TYPE);
      FAMILY = new CommandParam(CommandParamType.FAMILY);
      PERMISSION = new CommandParam(CommandParamType.PERMISSION);
      PERMISSIONS = new CommandParam(CommandParamType.PERMISSIONS);
      PERMISSION_SELECTOR = new CommandParam(CommandParamType.PERMISSION_SELECTOR);
      PERMISSION_ELEMENT = new CommandParam(CommandParamType.PERMISSION_ELEMENT);
      PERMISSION_ELEMENTS = new CommandParam(CommandParamType.PERMISSION_ELEMENTS);
      TAG = new CommandParam(CommandParamType.TAG);
      HAS_ITEM_ELEMENT = new CommandParam(CommandParamType.HAS_ITEM_ELEMENT);
      HAS_ITEM_ELEMENTS = new CommandParam(CommandParamType.HAS_ITEM_ELEMENTS);
      HAS_ITEM = new CommandParam(CommandParamType.HAS_ITEM);
      HAS_ITEMS = new CommandParam(CommandParamType.HAS_ITEMS);
      HAS_ITEM_SELECTOR = new CommandParam(CommandParamType.HAS_ITEM_SELECTOR);
      EQUIPMENT_SLOTS = new CommandParam(CommandParamType.EQUIPMENT_SLOTS);
      STRING = new CommandParam(CommandParamType.STRING);
      ID_CONT = new CommandParam(CommandParamType.ID_CONT);
      COORD_X_INT = new CommandParam(CommandParamType.COORD_X_INT);
      COORD_Y_INT = new CommandParam(CommandParamType.COORD_Y_INT);
      COORD_Z_INT = new CommandParam(CommandParamType.COORD_Z_INT);
      COORD_X_FLOAT = new CommandParam(CommandParamType.COORD_X_FLOAT);
      COORD_Y_FLOAT = new CommandParam(CommandParamType.COORD_Y_FLOAT);
      COORD_Z_FLOAT = new CommandParam(CommandParamType.COORD_Z_FLOAT);
      BLOCK_POSITION = new CommandParam(CommandParamType.BLOCK_POSITION);
      POSITION = new CommandParam(CommandParamType.POSITION);
      MESSAGE_XP = new CommandParam(CommandParamType.MESSAGE_XP);
      MESSAGE = new CommandParam(CommandParamType.MESSAGE);
      MESSAGE_ROOT = new CommandParam(CommandParamType.MESSAGE_ROOT);
      POST_SELECTOR = new CommandParam(CommandParamType.POST_SELECTOR);
      TEXT = new CommandParam(CommandParamType.TEXT);
      TEXT_CONT = new CommandParam(CommandParamType.TEXT_CONT);
      JSON_VALUE = new CommandParam(CommandParamType.JSON_VALUE);
      JSON_FIELD = new CommandParam(CommandParamType.JSON_FIELD);
      JSON = new CommandParam(CommandParamType.JSON);
      JSON_OBJECT_FIELDS = new CommandParam(CommandParamType.JSON_OBJECT_FIELDS);
      JSON_OBJECT_CONT = new CommandParam(CommandParamType.JSON_OBJECT_CONT);
      JSON_ARRAY = new CommandParam(CommandParamType.JSON_ARRAY);
      JSON_ARRAY_VALUES = new CommandParam(CommandParamType.JSON_ARRAY_VALUES);
      JSON_ARRAY_CONT = new CommandParam(CommandParamType.JSON_ARRAY_CONT);
      BLOCK_STATE = new CommandParam(CommandParamType.BLOCK_STATE);
      BLOCK_STATE_KEY = new CommandParam(CommandParamType.BLOCK_STATE_KEY);
      BLOCK_STATE_VALUE = new CommandParam(CommandParamType.BLOCK_STATE_VALUE);
      BLOCK_STATE_VALUES = new CommandParam(CommandParamType.BLOCK_STATE_VALUES);
      BLOCK_STATES = new CommandParam(CommandParamType.BLOCK_STATES);
      BLOCK_STATES_CONT = new CommandParam(CommandParamType.BLOCK_STATES_CONT);
      COMMAND = new CommandParam(CommandParamType.COMMAND);
      SLASH_COMMAND = new CommandParam(CommandParamType.SLASH_COMMAND);
      CHAINED_COMMAND = new CommandParam(CommandParamType.CHAINED_COMMAND);
      RATIONAL_RANGE_VAL = new CommandParam(CommandParamType.RATIONAL_RANGE_VAL);
      RATIONAL_RANGE_POST_VAL = new CommandParam(CommandParamType.RATIONAL_RANGE_POST_VAL);
      RATIONAL_RANGE = new CommandParam(CommandParamType.RATIONAL_RANGE);
      RATIONAL_RANGE_FULL = new CommandParam(CommandParamType.RATIONAL_RANGE_FULL);
      PROPERTY_VALUE = new CommandParam(CommandParamType.PROPERTY_VALUE);
      HAS_PROPERTY_PARAM_VALUE = new CommandParam(CommandParamType.HAS_PROPERTY_PARAM_VALUE);
      HAS_PROPERTY_PARAM_ENUM_VALUE = new CommandParam(CommandParamType.HAS_PROPERTY_PARAM_ENUM_VALUE);
      HAS_PROPERTY_ARG = new CommandParam(CommandParamType.HAS_PROPERTY_ARG);
      HAS_PROPERTY_ARGS = new CommandParam(CommandParamType.HAS_PROPERTY_ARGS);
      HAS_PROPERTY_ELEMENT = new CommandParam(CommandParamType.HAS_PROPERTY_ELEMENT);
      HAS_PROPERTY_ELEMENTS = new CommandParam(CommandParamType.HAS_PROPERTY_ELEMENTS);
      HAS_PROPERTY_SELECTOR = new CommandParam(CommandParamType.HAS_PROPERTY_SELECTOR);
   }
}
