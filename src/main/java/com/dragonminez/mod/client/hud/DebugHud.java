package com.dragonminez.mod.client.hud;

import com.dragonminez.mod.common.Reference;
import com.dragonminez.mod.common.player.stat.StatManager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DebugHud {

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGuiOverlayEvent.Pre event) {
        if (FMLEnvironment.production) {
            return;
        }
        DebugHud.renderDebugInformation(event.getGuiGraphics());
    }

    private static void renderDebugInformation(GuiGraphics graphics) {
        final Minecraft mc = Minecraft.getInstance();
        final LocalPlayer player = mc.player;
        if (player == null || mc.options.hideGui) {
            return;
        }

        StatManager.INSTANCE.retrieveStatData(player, statData -> {
            final Font font = mc.font;
            final PoseStack pose = graphics.pose();

            pose.pushPose();
            pose.scale(0.8f, 0.8f, 0.8f);

            int y = 50;
            int labelX = 10;
            int tabSpacing = 6;

            final String[] values = {
                    statData.getRace(), statData.getForm(),
                    String.valueOf(statData.getStrength()),
                    String.valueOf(statData.getDefense()),
                    String.valueOf(statData.getConstitution()),
                    String.valueOf(statData.getPower()),
                    String.valueOf(statData.getEnergy())
            };

            for (int i = 0; i < Reference.Stat.STATS.length; i++) {
                final String label = Reference.Stat.STATS[i] + ":";
                final String value = values[i];

                graphics.drawString(font, label, labelX, y, 0xaaaaaa, true);
                int labelWidth = font.width(label);
                graphics.drawString(font, value, labelX + labelWidth + tabSpacing, y, 0xff8800, true);
                y += 10;
            }

            pose.popPose();
        });
    }
}
