package com.nukkitx.network.raknet.pipeline;

import com.nukkitx.network.raknet.RakNet;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import java.util.function.Consumer;

@Sharable
public class RakExceptionHandler extends ChannelDuplexHandler {
   public static final String NAME = "rak-exception-handler";
   private final RakNet rakNet;

   public RakExceptionHandler(RakNet rakNet) {
      this.rakNet = rakNet;
   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
      for(Consumer<Throwable> handler : this.rakNet.getExceptionHandlers()) {
         handler.accept(cause);
      }

   }
}
