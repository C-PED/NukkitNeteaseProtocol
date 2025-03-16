package com.nukkitx.network.raknet;

import com.nukkitx.network.raknet.util.IntRange;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RakNetHistory {
   private final int SIZE = 2000;
   private int index = -1;
   private final HistoryNode[] history = new HistoryNode[2000];

   RakNetHistory() {
      this.pushNewTick(0L);
   }

   public void pushNewTick(long time) {
      this.index = (this.index + 1) % 2000;
      this.history[this.index] = new HistoryNode();
      this.history[this.index].timeMs = time;
   }

   public void pushUnackedBytes(int bytes) {
      this.history[this.index].unackedBytes = bytes;
   }

   public void pushCwnd(double cwnd) {
      this.history[this.index].cwnd = cwnd;
   }

   public void pushOutgoingPackets(int size) {
      this.history[this.index].outgoingPackets = size;
   }

   public void pushAcks(Queue<IntRange> queue) {
      this.history[this.index].incomingAcks = queue.toArray();
   }

   public void pushNaks(Queue<IntRange> queue) {
      this.history[this.index].incomingNaks = queue.toArray();
   }

   public void pushResend(List<RakNetDatagram> resend) {
      for(RakNetDatagram datagram : resend) {
         this.history[this.index].resend.add(datagram.sequenceIndex);
      }

   }

   public void pushResendNew(int sequenceIndex) {
      this.history[this.index].resendNew.add(sequenceIndex);
   }

   public void pushSend(int sequenceIndex) {
      this.history[this.index].send.add(sequenceIndex);
   }

   public void pushDatagram(String data) {
      this.history[this.index].sendDatagrams.add(data);
   }

   static class HistoryNode {
      private long timeMs;
      private int unackedBytes;
      private double cwnd;
      private int outgoingPackets;
      private Object[] incomingAcks;
      private Object[] incomingNaks;
      private final LinkedList<Integer> resend = new LinkedList();
      private final LinkedList<Integer> resendNew = new LinkedList();
      private final LinkedList<Integer> send = new LinkedList();
      private ArrayList<String> sendDatagrams = new ArrayList();
   }
}
