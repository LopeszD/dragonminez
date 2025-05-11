package com.dragonminez.mod.common.player.stat;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class StatManager implements ICapabilityProvider {

    public static StatManager INSTANCE = new StatManager();
    private final Capability<StatData> capability = CapabilityManager.get(new CapabilityToken<>() {
    });

    protected StatManager() {
        // Private constructor to prevent instantiation
    }

    public void update(Player player, StatData newStatData) {
        this.retrieveStatData(player, oldStatData -> oldStatData.deserializeNBT(newStatData.serializeNBT()));
    }

    public void retrieveStatData(Player player, Consumer<StatData> consumer) {
        player.getCapability(this.capability)
                .ifPresent(consumer::accept);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return this.capability.orEmpty(cap, LazyOptional.of(StatData::new));
    }
}
