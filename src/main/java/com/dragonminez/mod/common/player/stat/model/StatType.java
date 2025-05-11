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
    COMBAT_MODE,
    BLOCKING;

    final String id;
    final String legibleId;

    StatType() {
        this.id = this.name().toLowerCase();
        this.legibleId = this.createLegibleId();
    }

    public String id() {
        return this.id;
    }

    public String legibleId() {
        return this.legibleId;
    }

    private String createLegibleId() {
        final StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : id.toCharArray()) {
            if (c == '_') {
                result.append(" ");
                capitalizeNext = true;
            } else if (c == ' ') {
                result.append(c);
                capitalizeNext = true;
            } else {
                result.append(capitalizeNext ? Character.toUpperCase(c) : c);
                capitalizeNext = false;
            }
        }

        return result.toString();
    }
}
