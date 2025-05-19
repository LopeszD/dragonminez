package com.dragonminez.mod.core.common.network;

import net.minecraft.network.FriendlyByteBuf;

public interface IPacket {

  void encode(FriendlyByteBuf buffer);
}