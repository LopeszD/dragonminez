package com.dragonminez.mod.common.network.player.stat.s2c;

import com.dragonminez.mod.common.player.stat.StatData;
import com.dragonminez.mod.core.common.network.IPacket;
import net.minecraft.network.FriendlyByteBuf;

public class PacketS2CSyncStat extends PacketS2CSyncPublicStat implements IPacket {

    private final int strength;
    private final int defense;
    private final int constitution;
    private final int energy;
    private final int power;
    private final int alignment;

    public PacketS2CSyncStat(StatData data) {
        super(data, null);
        this.strength = data.getStrength();
        this.defense = data.getDefense();
        this.constitution = data.getConstitution();
        this.energy = data.getEnergy();
        this.power = data.getPower();
        this.alignment = data.getAlignment();
    }

    public PacketS2CSyncStat(FriendlyByteBuf buf) {
        super(buf);
        this.strength = buf.readInt();
        this.defense = buf.readInt();
        this.constitution = buf.readInt();
        this.energy = buf.readInt();
        this.power = buf.readInt();
        this.alignment = buf.readInt();
    }

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

    @Override
    public boolean serializeId() {
        return false;
    }
}
