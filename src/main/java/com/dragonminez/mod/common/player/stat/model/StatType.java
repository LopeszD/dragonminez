package com.dragonminez.mod.common.player.stat.model;

public enum StatType {
    RACE(true),
    FORM(true),
    STRENGTH(false),
    DEFENSE(false),
    CONSTITUTION(false),
    ENERGY(false),
    POWER(false),
    ALIGNMENT(true),
    COMBAT_MODE(true),
    BLOCKING(true);

    final String id;
    final String legibleId;
    final boolean isPublic;

    StatType(boolean isPublic) {
        this.id = this.name().toLowerCase();
        this.legibleId = this.createLegibleId();
        this.isPublic = isPublic;
    }

    public String id() {
        return this.id;
    }

    public String legibleId() {
        return this.legibleId;
    }

    public boolean isPublic() {
        return this.isPublic;
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
