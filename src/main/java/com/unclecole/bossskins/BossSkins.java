package com.unclecole.bossskins;

import com.unclecole.bossskins.commands.SkinCmd;
import com.unclecole.bossskins.utils.ConfigUtil;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class BossSkins extends JavaPlugin {

    @Getter public static BossSkins instance;
    @Getter public static ConfigUtil configUtil;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        configUtil = new ConfigUtil();
        configUtil.loadGUI();
        // Plugin startup logic
        getCommand("skin").setExecutor(new SkinCmd());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
