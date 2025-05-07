package com.dragonminez.mod.server.config.dimensions;

public class DimensionConfig {

    // The dimension ID
    private final String dimensionID;

    private final Training training;
    private final WorldGen worldgen;

    private DimensionConfig(String dimensionID, Training training, WorldGen worldgen) {
        this.dimensionID = dimensionID;
        this.training = training;
        this.worldgen = worldgen;
    }

    public String dimensionID() {
        return dimensionID;
    }

    public Training training() {
        return training;
    }

    public WorldGen worldGen() {
        return worldgen;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String dimensionID;
        private Training training = new Training.Builder().build();
        private WorldGen worldgen = new WorldGen.Builder().build();

        public Builder dimensionID(String dimensionID) {
            this.dimensionID = dimensionID;
            return this;
        }

        public Builder training(Training training) {
            this.training = training;
            return this;
        }

        public Builder worldgen(WorldGen worldgen) {
            this.worldgen = worldgen;
            return this;
        }

        public DimensionConfig build() {
            return new DimensionConfig(dimensionID, training, worldgen);
        }
    }

    public static class Training {
        /**
         * Multiplier for ZPoints gained by hitting an entity (Min: 1.0 / Max: 20.0 / Default: 1.0)
         */
        private final double hitMultiplier;

        /**
         * Multiplier for ZPoints gained by killing an entity (Min: 1.0 / Max: 20.0 / Default: 1.0)
         */
        private final double killMultiplier;

        private Training(double hitMultiplier, double killMultiplier) {
            this.hitMultiplier = Math.max(1.0, Math.min(20.0, hitMultiplier));
            this.killMultiplier = Math.max(1.0, Math.min(20.0, killMultiplier));
        }

        public double getHitMultiplier() {
            return hitMultiplier;
        }

        public double getKillMultiplier() {
            return killMultiplier;
        }

        public static class Builder {
            private double hitMultiplier = 1.0; // Default value for hit multiplier
            private double killMultiplier = 1.0; // Default value for kill multiplier

            public Builder hitMultiplier(double hitMultiplier) {
                this.hitMultiplier = Math.max(1.0, Math.min(20.0, hitMultiplier)); // Enforce limits
                return this;
            }

            public Builder killMultiplier(double killMultiplier) {
                this.killMultiplier = Math.max(1.0, Math.min(20.0, killMultiplier)); // Enforce limits
                return this;
            }

            public Training build() {
                return new Training(hitMultiplier, killMultiplier);
            }
        }
    }

    public static class WorldGen {
        /**
         * Should the dimension have Dragon Balls? (Default: true)
         */
        private final boolean spawnDragonBalls;

        /**
         * Should the dimension have a unique set of Dragon Balls? (Default: true)
         * <p><b>Disabling</b> this allows multiple sets of Dragon Balls to be placed.</p>
         * <p><b>Warning:</b> This may cause conflicts with tracking systems such as the Dragon Radar, which only detects the first set.
         * Additionally, <b>Dragon Balls may disappear</b> when a new one is placed if this is enabled.</p>
         */
        private final boolean uniqueDragonBalls;

        /**
         * Range in blocks for the Dragon Balls to spawn (Min: 2000 / Max: 20000 / Default: 3000)
         */
        private final int dballSpawnRange;

        /**
         * How many Dragon Balls should spawn (Min: 1 / Default: 7)
         */
        private final int dragonBallCount;

        /**
         * If true, the mod will create a custom block and you should add custom model and texture.
         * If false, the default Earth Dragon Ball model will be used.
         * <p><b>Note:</b> Custom models and textures should be added to the assets folder if this is enabled.</p>
         */
        private final boolean useCustomDragonBalls;

        private WorldGen(boolean spawnDragonBalls, boolean uniqueDragonBalls, int dballSpawnRange, int dragonBallCount, boolean useCustomDragonBalls) {
            this.spawnDragonBalls = spawnDragonBalls;
            this.uniqueDragonBalls = uniqueDragonBalls;
            this.dballSpawnRange = dballSpawnRange;
            this.dragonBallCount = dragonBallCount;
            this.useCustomDragonBalls = useCustomDragonBalls;
        }

        public boolean shouldSpawnDragonBalls() {
            return spawnDragonBalls;
        }

        public boolean hasUniqueDragonBalls() {
            return uniqueDragonBalls;
        }

        public int getDballSpawnRange() {
            return dballSpawnRange;
        }

        public int getDragonBallCount() {
            return dragonBallCount;
        }

        public boolean useCustomDragonBalls() {
            return useCustomDragonBalls;
        }

        public static class Builder {
            private boolean spawnDragonBalls = true; // Default value
            private boolean uniqueDragonBalls = true; // Default value
            private int dballSpawnRange = 3000; // Default value
            private int dragonBallCount = 7; // Default value for Dragon Ball count
            private boolean useCustomDragonBalls = false; // Default to using the default Earth Dragon Ball model

            public Builder spawnDragonBalls(boolean spawnDragonBalls) {
                this.spawnDragonBalls = spawnDragonBalls;
                return this;
            }

            public Builder uniqueDragonBalls(boolean uniqueDragonBalls) {
                this.uniqueDragonBalls = uniqueDragonBalls;
                return this;
            }

            public Builder dballSpawnRange(int dballSpawnRange) {
                this.dballSpawnRange = dballSpawnRange;
                return this;
            }

            public Builder dragonBallCount(int dragonBallCount) {
                this.dragonBallCount = Math.max(1, dragonBallCount); // Ensure at least one Dragon Ball
                return this;
            }

            public Builder useCustomDragonBalls(boolean useCustomDragonBalls) {
                this.useCustomDragonBalls = useCustomDragonBalls;
                return this;
            }

            public WorldGen build() {
                return new WorldGen(spawnDragonBalls, uniqueDragonBalls, dballSpawnRange, dragonBallCount, useCustomDragonBalls);
            }
        }
    }
}
