package com.dragonminez.mod.common.player.stat.model;

import com.dragonminez.mod.common.network.player.stat.s2c.PacketS2CSyncPublicStat;
import com.dragonminez.mod.common.network.player.stat.s2c.PacketS2CSyncStat;

/**
 * Represents different types of player stats in the game.
 * <p>
 * Each stat type is classified as either public or private:
 * <ul>
 *     <li><b>Public</b>: Visible to other players (e.g., race, form, combat state).</li>
 *     <li><b>Private</b>: Only sent to the owning client (e.g., strength, power).</li>
 * </ul>
 * <p>
 * This separation exists to protect sensitive player data and improve network efficiency.
 * For example, {@link PacketS2CSyncPublicStat}
 * handles public data only, while {@link PacketS2CSyncStat}
 * includes full private details and is only sent to the owning client.
 */
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

    private final String id;
    private final String legibleId;
    private final boolean isPublic;

    /**
     * @param isPublic Whether this stat is public (visible to other players).
     */
    StatType(boolean isPublic) {
        this.id = this.name().toLowerCase();
        this.legibleId = this.createLegibleId();
        this.isPublic = isPublic;
    }

    /**
     * Gets the internal ID string of the stat (e.g., "strength", "race").
     */
    public String id() {
        return this.id;
    }

    /**
     * Gets a human-readable version of the stat name (e.g., "Combat Mode").
     */
    public String legibleId() {
        return this.legibleId;
    }

    /**
     * Indicates whether this stat should be synced to other players.
     */
    public boolean isPublic() {
        return this.isPublic;
    }

    /**
     * Converts the enum name to a legible, title-case string with spaces.
     */
    private String createLegibleId() {
        final StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : id.toCharArray()) {
            if (c == '_') {
                result.append(" ");
                capitalizeNext = true;
            } else {
                result.append(capitalizeNext ? Character.toUpperCase(c) : c);
                capitalizeNext = false;
            }
        }

        return result.toString();
    }
}
