package com.dragonminez.mod.common;

/**
 * The {@code Reference} class stores static constants used throughout the mod.
 * <p>
 * This class primarily contains the mod ID, which is used for namespacing
 * various resources such as blocks, items, and configurations.
 * </p>
 * <p>
 * Since this class only contains constants, it should not be instantiated.
 * </p>
 */
public final class Reference {

    /**
     * The unique identifier for the mod.
     * <p>
     * This ID is used as a namespace for registry objects (e.g., blocks, items, entities)
     * and is referenced in various parts of the mod.
     * </p>
     */
    public static final String MOD_ID = "dragonminez";

    // Private constructor to prevent instantiation
    private Reference() {}
}
