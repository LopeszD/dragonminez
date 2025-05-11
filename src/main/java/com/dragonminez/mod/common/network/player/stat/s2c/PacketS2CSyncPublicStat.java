package com.dragonminez.mod.common.network.player.stat.s2c;

import com.dragonminez.mod.common.player.stat.StatData;
import com.dragonminez.mod.core.common.network.IPacket;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Packet for synchronizing public stat data from the server to all clients tracking a player.
 * <p>
 * This includes only render-relevant, non-sensitive fields such as race, form, combat mode,
 * and blocking state. These values are used by other clients to visually represent the player
 * (e.g., morph appearance, combat stance) without leaking internal stats like strength or energy.
 * <p>
 * The use of separate public and private stat packets serves both rendering efficiency and security:
 * - Reduces unnecessary data transmission to clients who don’t need full stat details.
 * - Prevents clients from accessing other players' personal gameplay data.
 */
public class PacketS2CSyncPublicStat implements IPacket {

    private final String race;
    private final String form;
    private final boolean isInCombatMode;
    private final boolean isBlocking;
    private final Integer playerId;

    /**
     * Constructs a new packet using the given stat data and target player ID.
     * The player ID is only serialized if {@link #serializeId()} returns true.
     *
     * @param data     The stat data to send (public fields only).
     * @param playerId The ID of the player whose data is being sent.
     */
    public PacketS2CSyncPublicStat(StatData data, Integer playerId) {
        this.race = data.getRace();
        this.form = data.getForm();
        this.isInCombatMode = data.isInCombatMode();
        this.isBlocking = data.isBlocking();
        this.playerId = this.serializeId() ? playerId : null;
    }

    /**
     * Constructs a new packet from the incoming network buffer.
     *
     * @param buf The buffer to read from.
     */
    public PacketS2CSyncPublicStat(FriendlyByteBuf buf) {
        this.race = buf.readUtf();
        this.form = buf.readUtf();
        this.isInCombatMode = buf.readBoolean();
        this.isBlocking = buf.readBoolean();
        this.playerId = this.serializeId() ? buf.readInt() : null;
    }

    /**
     * Encodes the packet data to the given buffer for transmission.
     *
     * @param buf The buffer to write to.
     */
    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(this.race);
        buf.writeUtf(this.form);
        buf.writeBoolean(this.isInCombatMode);
        buf.writeBoolean(this.isBlocking);
        if (this.serializeId()) {
            buf.writeInt(this.playerId);
        }
    }

    /**
     * Determines whether the player ID should be serialized.
     * Can be overridden in subclasses to change behavior.
     *
     * @return true if the player ID should be serialized.
     */
    public boolean serializeId() {
        return true;
    }

    /**
     * @return The ID of the player associated with this stat packet.
     */
    public int getPlayerId() {
        return this.playerId;
    }

    /**
     * Compacts the packet data into a {@link StatData} instance with only the public fields populated.
     * Used for updating render-focused stat information on the client.
     *
     * @return A new StatData instance containing only public fields.
     */
    public StatData compactedData() {
        return new StatData(this.race, this.form, 0, 0, 0, 0, 0,
                0, this.isInCombatMode, this.isBlocking);
    }
}
