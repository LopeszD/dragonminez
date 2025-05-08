package com.dragonminez.mod.core.client.keybind;

import com.dragonminez.mod.core.common.manager.ListManager;

public class KeybindManager extends ListManager<String, Keybind> {

    public static final KeybindManager INSTANCE = new KeybindManager();

    @Override
    public String identifier() {
        return "keybinds";
    }

    @Override
    public boolean uniqueKeys() {
        return true;
    }

    @Override
    public LogMode logMode() {
        return LogMode.LOG_ALL;
    }
}
