package com.dragonminez.mod.core.client.keybind;

import com.dragonminez.mod.common.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class KeybindListener {
    
    private static final Map<Keybind, Boolean> keyStateMap = new HashMap<>();

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        final LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        for (Keybind keybind : KeybindManager.INSTANCE.values()) {
            final boolean isActive = keybind.isActive();
            final boolean wasActive = keyStateMap.getOrDefault(keybind, false);

            if (isActive) {
                if (keybind.canBeHeldDown()) {
                    keybind.onPress(player);
                    continue;
                }
                if (!wasActive) {
                    keybind.onPress(player);
                }
            }

            keyStateMap.put(keybind, isActive);
        }
    }
}
