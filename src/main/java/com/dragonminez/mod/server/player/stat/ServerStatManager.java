package com.dragonminez.mod.server.player.stat;

import com.dragonminez.mod.common.network.NetworkManager;
import com.dragonminez.mod.common.network.player.stat.s2c.PacketS2CSyncPublicStat;
import com.dragonminez.mod.common.network.player.stat.s2c.PacketS2CSyncStat;
import com.dragonminez.mod.common.player.stat.StatData;
import com.dragonminez.mod.common.player.stat.StatManager;
import com.dragonminez.mod.common.player.stat.model.StatType;
import com.dragonminez.mod.common.util.LogUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.function.Consumer;

public class ServerStatManager extends StatManager {

    public static ServerStatManager INSTANCE = new ServerStatManager();

    private ServerStatManager() {
        // Private constructor to prevent instantiation
        super();
    }

    public void setRace(Player player, String raceIdentifier, boolean log) {
        this.modifyStat(player, StatType.RACE, data -> {
            data.setRace(raceIdentifier);
            if (log) {
                LogUtil.info("Race set to {} for player {}", raceIdentifier, player.getName().getString());
            }
        });
    }

    public void setRace(Player player, ResourceLocation location, boolean log) {
        this.setRace(player, location.toString(), log);
    }

    private void modifyStat(Player player, StatType type, Consumer<StatData> consumer) {
        this.retrieveStatData(player, consumer);
        var ignored = consumer.andThen(data -> this.sendUpdate(player, data, type.isPublic()));
    }

    private void sendUpdate(Player player, StatData data, boolean isPublic) {
        if (isPublic) {
            NetworkManager.INSTANCE.sendToTracking((ServerPlayer) player, new PacketS2CSyncPublicStat(data,
                    player.getId()));
        }
        NetworkManager.INSTANCE.sendToPlayer(new PacketS2CSyncStat(data), (ServerPlayer) player);
    }
}
