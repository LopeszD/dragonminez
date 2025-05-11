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

/**
 * Server-side implementation of {@link StatManager} responsible for handling
 * and synchronizing player stat updates.
 * <p>
 * Automatically determines whether a stat should be sent to all tracking players
 * or only the player themselves based on the stat's {@link StatType#isPublic()} flag.
 * <p>
 * This division reduces bandwidth, improves performance, and prevents sensitive
 * player information from being shared with others unnecessarily.
 */
public class ServerStatManager extends StatManager {

    /**
     * Global instance of the server-side stat manager.
     */
    public static ServerStatManager INSTANCE = new ServerStatManager();

    /**
     * Private constructor to enforce singleton pattern.
     */
    private ServerStatManager() {
        super();
    }

    /**
     * Sets the race for the specified player and optionally logs the change.
     *
     * @param player         The player whose race is being set.
     * @param raceIdentifier The new race identifier.
     * @param log            Whether to log this change to console.
     */
    public void setRace(Player player, String raceIdentifier, boolean log) {
        this.setStatInternal(player, StatType.RACE, raceIdentifier, data -> data.setRace(raceIdentifier), log);
    }

    /**
     * Overload of {@link #setRace(Player, String, boolean)} to allow using a {@link ResourceLocation}.
     *
     * @param player   The player whose race is being set.
     * @param location The new race identifier as a ResourceLocation.
     * @param log      Whether to log this change to console.
     */
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

    public void setDefense(Player player, int defense, boolean log) {
        this.setStatInternal(player, StatType.DEFENSE, defense, data -> data.setDefense(defense), log);
    }

    public void setConstitution(Player player, int constitution, boolean log) {
        this.setStatInternal(player, StatType.CONSTITUTION, constitution,
                data -> data.setConstitution(constitution), log);
    }

    public void setEnergy(Player player, int energy, boolean log) {
        this.setStatInternal(player, StatType.ENERGY, energy, data -> data.setEnergy(energy), log);
    }

    public void setPower(Player player, int power, boolean log) {
        this.setStatInternal(player, StatType.POWER, power, data -> data.setPower(power), log);
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

    /**
     * Retrieves and modifies the stat data for a player, then triggers sync based on stat visibility.
     *
     * @param player   The player whose stat will be modified.
     * @param type     The type of stat being changed.
     * @param consumer A consumer that applies changes to the {@link StatData}.
     */
    private void modifyStat(Player player, StatType type, Consumer<StatData> consumer) {
        this.retrieveStatData(player, consumer);
        // Ensure update is sent to correct targets (public vs private)
        var ignored = consumer.andThen(data -> this.sendUpdate(player, data, type.isPublic()));
    }

    /**
     * Sends updated stat data to the correct recipients based on visibility:
     * <ul>
     *     <li>Public stats: Sent to all tracking players and the player.</li>
     *     <li>Private stats: Sent only to the player.</li>
     * </ul>
     *
     * @param player   The player whose stats are being updated.
     * @param data     The updated stat data.
     * @param isPublic Whether the stat should be synced publicly.
     */
    private void sendUpdate(Player player, StatData data, boolean isPublic) {
        if (isPublic) {
            NetworkManager.INSTANCE.sendToTracking((ServerPlayer) player,
                    new PacketS2CSyncPublicStat(data, player.getId()));
        }
        NetworkManager.INSTANCE.sendToPlayer(new PacketS2CSyncStat(data), (ServerPlayer) player);
    }
}
