package com.dragonminez.mod.common.player.stat;

import java.util.function.Consumer;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Central manager for accessing and updating {@link StatData} on players via Forge's capability
 * system.
 * <p>
 * Serves as a common superclass for both client- and server-specific stat managers. Intended to be
 * accessed statically via {@link StatManager#INSTANCE}.
 */
public class StatManager implements ICapabilityProvider {

  /**
   * Global shared instance of the stat manager.
   */
  public static StatManager INSTANCE = new StatManager();

  /**
   * Reference to the registered capability for {@link StatData}.
   */
  private final Capability<StatData> capability = CapabilityManager.get(new CapabilityToken<>() {
  });

  /**
   * Protected constructor to restrict instantiation (supports singleton use).
   */
  protected StatManager() {
  }

  /**
   * Updates a player's {@link StatData} by overwriting it with the contents of another
   * {@link StatData} instance.
   *
   * @param player      The player whose data is being updated.
   * @param newStatData The new stat data to apply to the player.
   */
  public void update(Player player, StatData newStatData) {
    this.retrieveStatData(player, oldStatData ->
        oldStatData.deserializeNBT(newStatData.serializeNBT()));
  }

  /**
   * Retrieves a player's {@link StatData} and applies a consumer to it if present.
   *
   * @param player   The player whose stat data is to be retrieved.
   * @param consumer The consumer to apply if the capability exists.
   */
  public void retrieveStatData(Player player, Consumer<StatData> consumer) {
    player.getCapability(this.capability)
        .ifPresent(consumer::accept);
  }

  /**
   * Provides the {@link StatData} capability implementation to the capability system. This method
   * is part of Forge's {@link ICapabilityProvider} interface.
   *
   * @param cap  The requested capability type.
   * @param side The direction (not used for player entities).
   * @param <T>  The generic type of the capability.
   * @return A {@link LazyOptional} containing the capability if it matches.
   */
  @Override
  public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap,
      @Nullable Direction side) {
    return this.capability.orEmpty(cap, LazyOptional.of(StatData::new));
  }
}
