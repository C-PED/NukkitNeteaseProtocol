package com.nukkitx.protocol.bedrock.util;

import java.util.HashMap;
import java.util.Map;

public class NeteasePacketStatistics {
   private Map<String, StatisticsNode> packetStatistics = new HashMap();
   private Map<Long, Map<String, StatisticsNode>> userPacketStatistics = new HashMap();

   public void addSendStatistics(String packetName, int packetSize, long userId) {
      StatisticsNode staticNode = (StatisticsNode)this.packetStatistics.computeIfAbsent(packetName, (k) -> new StatisticsNode());
      staticNode.setNum(staticNode.getNum() + 1L);
      staticNode.setSize(staticNode.getSize() + (long)packetSize);
      Map<String, StatisticsNode> userPacketStatic = (Map)this.userPacketStatistics.computeIfAbsent(userId, (k) -> new HashMap());
      StatisticsNode userStaticNode = (StatisticsNode)userPacketStatic.computeIfAbsent(packetName, (k) -> new StatisticsNode());
      userStaticNode.setNum(userStaticNode.getNum() + 1L);
      userStaticNode.setSize(userStaticNode.getSize() + (long)packetSize);
   }

   private class StatisticsNode {
      private long num = 0L;
      private long size = 0L;

      public StatisticsNode() {
      }

      public long getNum() {
         return this.num;
      }

      public long getSize() {
         return this.size;
      }

      public void setNum(long num) {
         this.num = num;
      }

      public void setSize(long size) {
         this.size = size;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StatisticsNode)) {
            return false;
         } else {
            StatisticsNode other = (StatisticsNode)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.getNum() != other.getNum()) {
               return false;
            } else {
               return this.getSize() == other.getSize();
            }
         }
      }

      protected boolean canEqual(Object other) {
         return other instanceof StatisticsNode;
      }

      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         long $num = this.getNum();
         result = result * 59 + (int)($num >>> 32 ^ $num);
         long $size = this.getSize();
         result = result * 59 + (int)($size >>> 32 ^ $size);
         return result;
      }

      public String toString() {
         return "NeteasePacketStatistics.StatisticsNode(num=" + this.getNum() + ", size=" + this.getSize() + ")";
      }
   }
}
