package com.demeng7215.cytsoulbound;

import com.demeng7215.cytsoulbound.commands.SoulboundCmd;
import com.demeng7215.cytsoulbound.lib.DemLib;
import com.demeng7215.cytsoulbound.lib.utils.Registerer;
import com.demeng7215.cytsoulbound.lib.utils.files.CustomConfig;
import com.demeng7215.cytsoulbound.lib.utils.messages.MessageUtils;
import com.demeng7215.cytsoulbound.listeners.DeathEvent;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class CYTSoulbound extends JavaPlugin {

    @Getter
    private static CYTSoulbound plugin;

    // settings.yml
    private CustomConfig settings;

    @Override
    public void onEnable() {

        DemLib.setPlugin(this);
        MessageUtils.setPrefix("");

        MessageUtils.log(null, "Starting enable for CYTSoulbound...");

        plugin = this;

        MessageUtils.log(null, "Loading settings file...");

        // Create or load the file.
        try {
            this.settings = new CustomConfig("settings.yml");
        } catch (final Exception ex) {
            ex.printStackTrace();
            return;
        }

        MessageUtils.log(null, "Registering commands...");
        Registerer.registerCommand(new SoulboundCmd());

        MessageUtils.log(null, "Registering listeners...");
        Registerer.registerListeners(new DeathEvent());

        MessageUtils.console("&aCYTSoulbound has been successfully enabled.");
    }

    @Override
    public void onDisable() {
        MessageUtils.console("&cCYTSoulbound has been successfully disabled.");
    }

    public FileConfiguration getSettings() {
        return settings.getConfig();
    }
}
