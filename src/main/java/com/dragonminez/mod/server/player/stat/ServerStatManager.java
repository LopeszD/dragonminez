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
        super();
    }

    public void setRace(Player player, String raceIdentifier, boolean log) {
        this.setStatInternal(player, StatType.RACE, raceIdentifier, data -> data.setRace(raceIdentifier), log);
    }

    public void setRace(Player player, ResourceLocation location, boolean log) {
        this.setRace(player, location.toString(), log);
    }

    public void setForm(Player player, String formIdentifier, boolean log) {
        this.setStatInternal(player, StatType.FORM, formIdentifier, data -> data.setForm(formIdentifier), log);
    }

    public void setForm(Player player, ResourceLocation location, boolean log) {
        this.setForm(player, location.toString(), log);
    }

    public void setStrength(Player player, int strength, boolean log) {
        this.setStatInternal(player, StatType.STRENGTH, strength, data -> data.setStrength(strength), log);
    }

    public void setStrikePower(Player player, int strikePower, boolean log) {
        this.setStatInternal(player, StatType.STRIKE_POWER, strikePower, data -> data.setStrikePower(strikePower), log);
    }

    public void setEnergy(Player player, int energy, boolean log) {
        this.setStatInternal(player, StatType.ENERGY, energy, data -> data.setEnergy(energy), log);
    }

    public void setVitality(Player player, int vitality, boolean log) {
        this.setStatInternal(player, StatType.VITALITY, vitality, data -> data.setVitality(vitality), log);
    }

    public void setResistance(Player player, int resistance, boolean log) {
        this.setStatInternal(player, StatType.RESISTANCE, resistance, data -> data.setResistance(resistance), log);
    }

    public void setKiPower(Player player, int kiPower, boolean log) {
        this.setStatInternal(player, StatType.KI_POWER, kiPower, data -> data.setKiPower(kiPower), log);
    }

    public void setAlignment(Player player, int alignment, boolean log) {
        this.setStatInternal(player, StatType.ALIGNMENT, alignment, data -> data.setAlignment(alignment), log);
    }

    public void setCombatMode(Player player, boolean combatMode, boolean log) {
        this.setStatInternal(player, StatType.COMBAT_MODE, combatMode,
                data -> data.setCombatMode(combatMode), log);
    }

    public void setBlocking(Player player, boolean blocking, boolean log) {
        this.setStatInternal(player, StatType.BLOCKING, blocking, data -> data.setBlocking(blocking), log);
    }

    private void setStatInternal(Player player, StatType type, Object value, Consumer<StatData> dataConsumer, boolean log) {
        this.modifyStat(player, type, data -> {
            dataConsumer.accept(data);
            if (log) {
                LogUtil.info("{} set to {} for player {}", type.legibleId(), value,
                        player.getName().getString());
            }
        });
    }

    private void modifyStat(Player player, StatType type, Consumer<StatData> consumer) {
        this.retrieveStatData(player, consumer);
        // Make sure to send update after modification
        consumer.andThen(data -> this.sendUpdate(player, data, type.isPublic()));
    }

    private void sendUpdate(Player player, StatData data, boolean isPublic) {
        if (isPublic) {
            NetworkManager.INSTANCE.sendToTracking((ServerPlayer) player,
                    new PacketS2CSyncPublicStat(data, player.getId()));
        }
        NetworkManager.INSTANCE.sendToPlayer(new PacketS2CSyncStat(data), (ServerPlayer) player);
    }
}
