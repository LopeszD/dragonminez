package com.dragonminez.mod.common.network.player.stat.s2c;

import com.dragonminez.mod.common.player.stat.StatData;
import com.dragonminez.mod.core.common.network.IPacket;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Full stat synchronization packet sent from server to client.
 * <p>
 * This includes private {@link StatData} like strength, strike power, energy, etc. Unlike
 * {@link PacketS2CSyncPublicStat}, this packet is only sent to the owning client and not to nearby
 * players.
 */
public class PacketS2CSyncStat extends PacketS2CSyncPublicStat implements IPacket {

  private final int strength;
  private final int strikePower;
  private final int energy;
  private final int vitality;
  private final int resistance;
  private final int kiPower;

  public PacketS2CSyncStat(StatData data) {
    super(data, null);
    this.strength = data.getStrength();
    this.strikePower = data.getStrikePower();
    this.energy = data.getEnergy();
    this.vitality = data.getVitality();
    this.resistance = data.getResistance();
    this.kiPower = data.getKiPower();
  }

  public PacketS2CSyncStat(FriendlyByteBuf buf) {
    super(buf);
    this.strength = buf.readInt();
    this.strikePower = buf.readInt();
    this.energy = buf.readInt();
    this.vitality = buf.readInt();
    this.resistance = buf.readInt();
    this.kiPower = buf.readInt();
  }

  @Override
  public void encode(FriendlyByteBuf buf) {
    super.encode(buf);
    buf.writeInt(this.strength);
    buf.writeInt(this.strikePower);
    buf.writeInt(this.energy);
    buf.writeInt(this.vitality);
    buf.writeInt(this.resistance);
    buf.writeInt(this.kiPower);
  }

  @Override
  public StatData compactedData() {
    final StatData data = super.compactedData();
    data.setStrength(this.strength);
    data.setStrikePower(this.strikePower);
    data.setEnergy(this.energy);
    data.setVitality(this.vitality);
    data.setResistance(this.resistance);
    data.setKiPower(this.kiPower);
    return data;
  }

  @Override
  public boolean serializeId() {
    return false;
  }
}
