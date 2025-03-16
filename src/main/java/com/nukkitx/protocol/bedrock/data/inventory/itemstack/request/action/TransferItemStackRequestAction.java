package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotData;

public interface TransferItemStackRequestAction extends ItemStackRequestAction {
   int getCount();

   ItemStackRequestSlotData getSource();

   ItemStackRequestSlotData getDestination();
}
