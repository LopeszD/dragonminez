package com.dragonminez.mod.common.network.player.stat.s2c;

import com.dragonminez.mod.common.player.stat.StatManager;
import com.dragonminez.mod.core.common.network.IPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public class PacketS2CSyncStat implements IPacket {

    private CompoundTag nbt;
    private final int playerId;

    public PacketS2CSyncStat(Player player) {
        StatManager.INSTANCE.retrieveStatData(player, statData ->
                this.nbt = statData.serializeNBT());
        this.playerId = player.getId();
    }

    public PacketS2CSyncStat(FriendlyByteBuf buf) {
        this.nbt = buf.readNbt();
        this.playerId = buf.readInt();
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeNbt(this.nbt);
        buf.writeInt(this.playerId);
    }

    public CompoundTag getNbt() {
        return this.nbt;
    }

    public int getPlayerId() {
        return this.playerId;
    }
}
