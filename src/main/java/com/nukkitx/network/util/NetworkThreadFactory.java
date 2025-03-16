package com.nukkitx.network.util;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;

public class NetworkThreadFactory implements ThreadFactory {
   private static final ThreadFactory backingFactory = Executors.defaultThreadFactory();
   private final AtomicInteger count = new AtomicInteger(0);
   private final boolean daemon;
   private final String format;
   private final int priority;
   private final Thread.UncaughtExceptionHandler exceptionHandler;

   private static String format(String format, int count) {
      return String.format(Locale.ROOT, format, count);
   }

   public Thread newThread(@Nonnull Runnable runnable) {
      Thread thread = backingFactory.newThread(runnable);
      if (this.format != null) {
         thread.setName(format(this.format, this.count.getAndIncrement()));
      }

      thread.setDaemon(this.daemon);
      thread.setPriority(this.priority);
      if (this.exceptionHandler != null) {
         thread.setUncaughtExceptionHandler(this.exceptionHandler);
      }

      return thread;
   }

   private static int $default$priority() {
      return Thread.currentThread().getPriority();
   }

   NetworkThreadFactory(boolean daemon, String format, int priority, Thread.UncaughtExceptionHandler exceptionHandler) {
      this.daemon = daemon;
      this.format = format;
      this.priority = priority;
      this.exceptionHandler = exceptionHandler;
   }

   public static NetworkThreadFactoryBuilder builder() {
      return new NetworkThreadFactoryBuilder();
   }

   public static class NetworkThreadFactoryBuilder {
      private boolean daemon;
      private String format;
      private boolean priority$set;
      private int priority$value;
      private Thread.UncaughtExceptionHandler exceptionHandler;

      NetworkThreadFactoryBuilder() {
      }

      public NetworkThreadFactoryBuilder daemon(boolean daemon) {
         this.daemon = daemon;
         return this;
      }

      public NetworkThreadFactoryBuilder format(String format) {
         this.format = format;
         return this;
      }

      public NetworkThreadFactoryBuilder priority(int priority) {
         this.priority$value = priority;
         this.priority$set = true;
         return this;
      }

      public NetworkThreadFactoryBuilder exceptionHandler(Thread.UncaughtExceptionHandler exceptionHandler) {
         this.exceptionHandler = exceptionHandler;
         return this;
      }

      public NetworkThreadFactory build() {
         int priority$value = this.priority$value;
         if (!this.priority$set) {
            priority$value = NetworkThreadFactory.$default$priority();
         }

         return new NetworkThreadFactory(this.daemon, this.format, priority$value, this.exceptionHandler);
      }

      public String toString() {
         return "NetworkThreadFactory.NetworkThreadFactoryBuilder(daemon=" + this.daemon + ", format=" + this.format + ", priority$value=" + this.priority$value + ", exceptionHandler=" + this.exceptionHandler + ")";
      }
   }
}
