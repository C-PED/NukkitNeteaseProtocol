package com.nukkitx.network.raknet;

import com.nukkitx.network.util.DisconnectReason;
import io.netty.buffer.ByteBuf;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface RakNetSessionListener {
   void onSessionChangeState(RakNetState var1);

   void onDisconnect(DisconnectReason var1);

   void onEncapsulated(EncapsulatedPacket var1);

   void onDirect(ByteBuf var1);
}
