package com.dragonminez.mod.common.player.stat.model;

public enum StatType {
    RACE,
    FORM,
    STRENGTH,
    DEFENSE,
    CONSTITUTION,
    ENERGY,
    POWER,
    ALIGNMENT,
    COMBAT_MODE;

    public String id() {
        return this.name().toLowerCase();
    }
}