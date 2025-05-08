package com.dragonminez.mod.common.player.stat;

import com.dragonminez.mod.common.Reference;
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

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag nbt = new CompoundTag();
        nbt.putString(Reference.Stat.RACE, this.race);
        nbt.putString(Reference.Stat.FORM, this.form);
        nbt.putInt(Reference.Stat.STRENGTH, this.strength);
        nbt.putInt(Reference.Stat.DEFENSE, this.defense);
        nbt.putInt(Reference.Stat.CONSTITUTION, this.constitution);
        nbt.putInt(Reference.Stat.ENERGY, this.energy);
        nbt.putInt(Reference.Stat.POWER, this.power);
        nbt.putInt(Reference.Stat.ALIGNMENT, this.alignment);
        nbt.putBoolean(Reference.Stat.COMBAT_MODE, this.isInCombatMode);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.race = nbt.getString(Reference.Stat.RACE);
        this.form = nbt.getString(Reference.Stat.FORM);
        this.strength = nbt.getInt(Reference.Stat.STRENGTH);
        this.defense = nbt.getInt(Reference.Stat.DEFENSE);
        this.constitution = nbt.getInt(Reference.Stat.CONSTITUTION);
        this.energy = nbt.getInt(Reference.Stat.ENERGY);
        this.power = nbt.getInt(Reference.Stat.POWER);
        this.alignment = nbt.getInt(Reference.Stat.ALIGNMENT);
        this.isInCombatMode = nbt.getBoolean(Reference.Stat.COMBAT_MODE);
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

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
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
}
