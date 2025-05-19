package com.dragonminez.mod.server.player.stat;

import com.dragonminez.mod.common.Reference;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerStatListener {

  @SubscribeEvent
  public static void onPlayerJoined(PlayerEvent.PlayerLoggedInEvent event) {
  }

  @SubscribeEvent
  public static void onPlayerTrack(PlayerEvent.StartTracking event) {
  }

  @SubscribeEvent
  public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
  }

  @SubscribeEvent
  public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
  }

  @SubscribeEvent
  public static void onPlayerCloned(PlayerEvent.Clone event) {
  }
}
