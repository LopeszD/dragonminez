package com.dragonminez.mod.common.player.stat;

import com.dragonminez.mod.common.Reference;
import com.dragonminez.mod.common.player.stat.model.StatType;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public class StatData implements INBTSerializable<CompoundTag> {

    private String race = Reference.EMPTY;
    private String form = Reference.EMPTY;

    private int strength = 5;
    private int strikePower = 5;
    private int energy = 5;
    private int vitality = 5;
    private int resistance = 5;
    private int kiPower = 5;

    private int alignment = 100;
    private boolean isInCombatMode = false;
    private boolean isBlocking = false;

    public StatData() {}

    public StatData(String race, String form, int strength, int strikePower, int energy, int vitality, int resistance,
                    int kiPower, int alignment, boolean isInCombatMode, boolean isBlocking) {
        this.race = race;
        this.form = form;
        this.strength = strength;
        this.strikePower = strikePower;
        this.energy = energy;
        this.vitality = vitality;
        this.resistance = resistance;
        this.kiPower = kiPower;
        this.alignment = alignment;
        this.isInCombatMode = isInCombatMode;
        this.isBlocking = isBlocking;
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag nbt = new CompoundTag();
        nbt.putString(StatType.RACE.id(), this.race);
        nbt.putString(StatType.FORM.id(), this.form);
        nbt.putInt(StatType.STRENGTH.id(), this.strength);
        nbt.putInt(StatType.STRIKE_POWER.id(), this.strikePower);
        nbt.putInt(StatType.ENERGY.id(), this.energy);
        nbt.putInt(StatType.VITALITY.id(), this.vitality);
        nbt.putInt(StatType.RESISTANCE.id(), this.resistance);
        nbt.putInt(StatType.KI_POWER.id(), this.kiPower);
        nbt.putInt(StatType.ALIGNMENT.id(), this.alignment);
        nbt.putBoolean(StatType.COMBAT_MODE.id(), this.isInCombatMode);
        nbt.putBoolean(StatType.BLOCKING.id(), this.isBlocking);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.race = nbt.getString(StatType.RACE.id());
        this.form = nbt.getString(StatType.FORM.id());
        this.strength = nbt.getInt(StatType.STRENGTH.id());
        this.strikePower = nbt.getInt(StatType.STRIKE_POWER.id());
        this.energy = nbt.getInt(StatType.ENERGY.id());
        this.vitality = nbt.getInt(StatType.VITALITY.id());
        this.resistance = nbt.getInt(StatType.RESISTANCE.id());
        this.kiPower = nbt.getInt(StatType.KI_POWER.id());
        this.alignment = nbt.getInt(StatType.ALIGNMENT.id());
        this.isInCombatMode = nbt.getBoolean(StatType.COMBAT_MODE.id());
        this.isBlocking = nbt.getBoolean(StatType.BLOCKING.id());
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrikePower() {
        return strikePower;
    }

    public void setStrikePower(int strikePower) {
        this.strikePower = strikePower;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getKiPower() {
        return kiPower;
    }

    public void setKiPower(int kiPower) {
        this.kiPower = kiPower;
    }

    public int getAlignment() {
        return alignment;
    }

    public void setAlignment(int alignment) {
        this.alignment = alignment;
    }

    public boolean isInCombatMode() {
        return isInCombatMode;
    }

    public void setCombatMode(boolean inCombatMode) {
        isInCombatMode = inCombatMode;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void setBlocking(boolean blocking) {
        isBlocking = blocking;
    }
}
