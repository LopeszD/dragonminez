package com.dragonminez.mod.core.client.keybind;

import com.dragonminez.mod.common.Reference;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import javax.swing.text.Keymap;

public abstract class Keybind {

    private KeyMapping mapping;

    public abstract void onPress(LocalPlayer player);

    public abstract String id();

    public abstract String category();

    public abstract int key();

    public abstract boolean canBeHeldDown();

    public abstract boolean requiresCtrl();

    public KeyMapping mapping() {
        if (this.mapping == null) {
            final String keyLang = "key.%s.%s.".formatted(Reference.MOD_ID, this.id());
            final String category = "keys.%s.%s".formatted(Reference.MOD_ID, this.category());
            this.mapping = new ExtensiveKeyMapping(keyLang, InputConstants.Type.KEYSYM, this.key(), category,
                    this.requiresCtrl());
        }
        return this.mapping;
    }

    public boolean isCtrlDown() {
        long window = Minecraft.getInstance().getWindow().getWindow();
        return org.lwjgl.glfw.GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS ||
                org.lwjgl.glfw.GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_CONTROL) == GLFW.GLFW_PRESS;
    }

    public boolean isActive() {
        return this.mapping().isDown() && (!requiresCtrl() || isCtrlDown());
    }

    public static class ExtensiveKeyMapping extends KeyMapping {
        private final boolean ctrlRequired;

        public ExtensiveKeyMapping(String keyLang, InputConstants.Type type, int key, String category,
                                   boolean ctrlRequired) {
            super(keyLang, type, key, category);
            this.ctrlRequired = ctrlRequired;
        }

        @Override
        public @NotNull Component getTranslatedKeyMessage() {
            if (this.ctrlRequired) {
                final String keyName = super.getTranslatedKeyMessage().getString();
                return Component.literal("Ctrl + " + keyName);
            } else {
                return super.getTranslatedKeyMessage();
            }
        }
    }
}