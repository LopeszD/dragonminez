package com.dragonminez.mod.common.network.player.stat.s2c;

import com.dragonminez.mod.common.player.stat.StatData;
import com.dragonminez.mod.core.common.network.IPacket;
import net.minecraft.network.FriendlyByteBuf;

public class PacketS2CSyncPublicStat implements IPacket {

    private final String race;
    private final String form;
    private final boolean isInCombatMode;
    private final boolean isBlocking;
    private final Integer playerId;

    public PacketS2CSyncPublicStat(StatData data, Integer playerId) {
        this.race = data.getRace();
        this.form = data.getForm();
        this.isInCombatMode = data.isInCombatMode();
        this.isBlocking = data.isBlocking();
        this.playerId = this.serializeId() ? playerId : null;
    }

    public PacketS2CSyncPublicStat(FriendlyByteBuf buf) {
        this.race = buf.readUtf();
        this.form = buf.readUtf();
        this.isInCombatMode = buf.readBoolean();
        this.isBlocking = buf.readBoolean();
        this.playerId = this.serializeId() ? buf.readInt() : null;
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(this.form);
        buf.writeUtf(this.form);
        buf.writeBoolean(this.isInCombatMode);
        buf.writeBoolean(this.isBlocking);
        if (this.serializeId()) {
            buf.writeInt(this.playerId);
        }
    }

    public boolean serializeId() {
        return true;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public StatData compactedData() {
        return new StatData(this.race, this.form, 0, 0, 0, 0, 0,
                0, this.isInCombatMode, this.isBlocking);
    }
}
