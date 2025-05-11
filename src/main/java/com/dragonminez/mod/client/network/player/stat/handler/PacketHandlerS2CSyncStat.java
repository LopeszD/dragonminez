package com.dragonminez.mod.client.network.player.stat.handler;

import com.dragonminez.mod.common.network.player.stat.s2c.PacketS2CSyncPublicStat;
import com.dragonminez.mod.common.player.stat.StatManager;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketHandlerS2CSyncStat<T extends PacketS2CSyncPublicStat> {

    public void handle(T packet, Supplier<NetworkEvent.Context> ctx) {
        final NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            final Level level = Minecraft.getInstance().level;
            if (level == null) return;

            Entity entity = Minecraft.getInstance().player;
            if (packet.serializeId()) {
                entity = level.getEntity(packet.getPlayerId());
            }

            if (!(entity instanceof Player player)) return;
            StatManager.INSTANCE.update(player, packet.compactedData());
        });
        context.setPacketHandled(true);
    }
}
