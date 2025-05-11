package com.dragonminez.mod.common.network.player.stat.s2c;

import com.dragonminez.mod.common.player.stat.StatData;
import com.dragonminez.mod.core.common.network.IPacket;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Full stat synchronization packet sent from server to client.
 * <p>
 * This includes both public and private {@link StatData}, such as strength, defense, energy, and more.
 * Unlike {@link PacketS2CSyncPublicStat}, this packet is only sent to the owning client and not to nearby players.
 * This division improves security and performance by avoiding unnecessary or sensitive data transmission.
 */
public class PacketS2CSyncStat extends PacketS2CSyncPublicStat implements IPacket {

    private final int strength;
    private final int defense;
    private final int constitution;
    private final int energy;
    private final int power;
    private final int alignment;

    /**
     * Constructs a packet from a {@link StatData} object for the current player.
     *
     * @param data The stat data to synchronize.
     */
    public PacketS2CSyncStat(StatData data) {
        super(data, null);
        this.strength = data.getStrength();
        this.defense = data.getDefense();
        this.constitution = data.getConstitution();
        this.energy = data.getEnergy();
        this.power = data.getPower();
        this.alignment = data.getAlignment();
    }

    /**
     * Constructs the packet from a byte buffer received over the network.
     *
     * @param buf The buffer to read data from.
     */
    public PacketS2CSyncStat(FriendlyByteBuf buf) {
        super(buf);
        this.strength = buf.readInt();
        this.defense = buf.readInt();
        this.constitution = buf.readInt();
        this.energy = buf.readInt();
        this.power = buf.readInt();
        this.alignment = buf.readInt();
    }

    /**
     * Serializes the full stat data into the network buffer.
     *
     * @param buf The buffer to write data to.
     */
    @Override
    public void encode(FriendlyByteBuf buf) {
        super.encode(buf);
        buf.writeInt(this.strength);
        buf.writeInt(this.defense);
        buf.writeInt(this.constitution);
        buf.writeInt(this.energy);
        buf.writeInt(this.power);
        buf.writeInt(this.alignment);
    }

    /**
     * Reconstructs the stat data from the packet contents.
     *
     * @return A {@link StatData} instance populated with all stat values.
     */
    @Override
    public StatData compactedData() {
        final StatData data = super.compactedData();
        data.setStrength(this.strength);
        data.setDefense(this.defense);
        data.setConstitution(this.constitution);
        data.setEnergy(this.energy);
        data.setPower(this.power);
        data.setAlignment(this.alignment);
        return data;
    }

    /**
     * Indicates that this packet does not include the player ID in serialization,
     * since it is only intended for the owning client.
     *
     * @return {@code false}, because ID is unnecessary for private stat sync.
     */
    @Override
    public boolean serializeId() {
        return false;
    }
}
