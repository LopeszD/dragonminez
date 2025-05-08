package com.dragonminez.mod.server.player.stat;

import com.dragonminez.mod.common.network.NetworkManager;
import com.dragonminez.mod.common.network.player.stat.s2c.PacketS2CSyncStat;
import com.dragonminez.mod.common.player.stat.StatData;
import com.dragonminez.mod.common.player.stat.StatManager;
import com.dragonminez.mod.common.util.LogUtil;
import net.minecraft.nbt.CompoundTag;
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

    public void update(Player player, CompoundTag nbt) {
        this.retrieveStatData(player, statData -> statData.deserializeNBT(nbt), true);
    }

    public void setRace(Player player, String raceIdentifier, boolean log) {
        this.retrieveStatData(player, statData -> {
            statData.setRace(raceIdentifier);
            if (log) {
                LogUtil.info("Race set to {} for player {}", raceIdentifier, player.getName().getString());
            }
        }, true);
    }

    public void setRace(Player player, ResourceLocation location, boolean log) {
        this.setRace(player, location.toString(), log);
    }

    public void retrieveStatData(Player player, Consumer<StatData> consumer, boolean sync) {
        this.retrieveStatData(player, consumer);
        if (sync) {
            NetworkManager.INSTANCE.sendToTrackingAndSelf((ServerPlayer) player, new PacketS2CSyncStat(player));
        }
    }
}
