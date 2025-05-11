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
    private int defense = 5;
    private int constitution = 5;
    private int energy = 5;
    private int power = 5;
    private int alignment = 100;
    private boolean isInCombatMode = false;
    private boolean isBlocking = false;

    public StatData() {}

    public StatData(String race, String form, int defense, int strength, int constitution, int energy, int power,
                    int alignment, boolean isInCombatMode, boolean isBlocking) {
        this.race = race;
        this.form = form;
        this.defense = defense;
        this.strength = strength;
        this.constitution = constitution;
        this.energy = energy;
        this.power = power;
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
        nbt.putInt(StatType.DEFENSE.id(), this.defense);
        nbt.putInt(StatType.CONSTITUTION.id(), this.constitution);
        nbt.putInt(StatType.ENERGY.id(), this.energy);
        nbt.putInt(StatType.POWER.id(), this.power);
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
        this.defense = nbt.getInt(StatType.DEFENSE.id());
        this.constitution = nbt.getInt(StatType.CONSTITUTION.id());
        this.energy = nbt.getInt(StatType.ENERGY.id());
        this.power = nbt.getInt(StatType.POWER.id());
        this.alignment = nbt.getInt(StatType.ALIGNMENT.id());
        this.isInCombatMode = nbt.getBoolean(StatType.COMBAT_MODE.id());
        this.isBlocking = nbt.getBoolean(StatType.BLOCKING.id());
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getRace() {
        return race;
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

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
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

    public void setInCombatMode(boolean inCombatMode) {
        isInCombatMode = inCombatMode;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void setBlocking(boolean blocking) {
        isBlocking = blocking;
    }
}
