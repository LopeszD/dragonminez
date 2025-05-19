package com.dragonminez.mod.server.config.food;

/**
 * Represents the configuration for a food item that provides regeneration effects. This includes
 * health, ki, and stamina regeneration values.
 */
public class FoodConfig {

  /**
   * The item ID (e.g., "minecraft:apple") associated with this food configuration.
   */
  private final String itemID;

  /**
   * The amount of health regenerated when this food is consumed.
   */
  private final double healthRegen;

  /**
   * The amount of ki regenerated when this food is consumed.
   */
  private final double kiRegen;

  /**
   * The amount of stamina regenerated when this food is consumed.
   */
  private final double staminaRegen;

  /**
   * Constructs a new {@link FoodConfig} with the specified values.
   *
   * @param itemID       the identifier of the item
   * @param healthRegen  the amount of health regeneration
   * @param kiRegen      the amount of ki regeneration
   * @param staminaRegen the amount of stamina regeneration
   */
  private FoodConfig(String itemID, double healthRegen, double kiRegen, double staminaRegen) {
    this.itemID = itemID;
    this.healthRegen = healthRegen;
    this.kiRegen = kiRegen;
    this.staminaRegen = staminaRegen;
  }

  /**
   * @return the item ID for this food configuration
   */
  public String getItemID() {
    return itemID;
  }

  /**
   * @return the health regeneration value
   */
  public double getHealthRegen() {
    return healthRegen;
  }

  /**
   * @return the ki regeneration value
   */
  public double getKiRegen() {
    return kiRegen;
  }

  /**
   * @return the stamina regeneration value
   */
  public double getStaminaRegen() {
    return staminaRegen;
  }

  /**
   * Builder class for creating instances of {@link FoodConfig}. Use the builder to fluently set
   * values before building the final object.
   */
  public static class Builder {

    private String itemID;
    private double healthRegen;
    private double kiRegen;
    private double staminaRegen;

    /**
     * Sets the item ID.
     *
     * @param itemID the item identifier
     * @return the builder instance
     */
    public Builder setItemID(String itemID) {
      this.itemID = itemID;
      return this;
    }

    /**
     * Sets the health regeneration value.
     *
     * @param healthRegen amount of health to regenerate
     * @return the builder instance
     */
    public Builder setHealthRegen(double healthRegen) {
      this.healthRegen = healthRegen;
      return this;
    }

    /**
     * Sets the ki regeneration value.
     *
     * @param kiRegen amount of ki to regenerate
     * @return the builder instance
     */
    public Builder setKiRegen(double kiRegen) {
      this.kiRegen = kiRegen;
      return this;
    }

    /**
     * Sets the stamina regeneration value.
     *
     * @param staminaRegen amount of stamina to regenerate
     * @return the builder instance
     */
    public Builder setStaminaRegen(double staminaRegen) {
      this.staminaRegen = staminaRegen;
      return this;
    }

    /**
     * Builds the {@link FoodConfig} object with the current builder values.
     *
     * @return a new instance of {@link FoodConfig}
     */
    public FoodConfig build() {
      return new FoodConfig(itemID, healthRegen, kiRegen, staminaRegen);
    }
  }

  /**
   * Shared builder instance.
   */
  private static final Builder builder = new Builder();

  /**
   * Returns the shared {@link Builder} instance.
   *
   * @return the builder
   */
  public static Builder builder() {
    return builder;
  }
}
