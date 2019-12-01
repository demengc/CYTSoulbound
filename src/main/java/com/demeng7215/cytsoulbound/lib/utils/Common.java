package com.demeng7215.cytsoulbound.lib.utils;

import com.demeng7215.cytsoulbound.lib.DemLib;
import org.bukkit.plugin.Plugin;

/**
 * Just some common methods that are useful or used frequently.
 */
public class Common {

    private static final Plugin i = DemLib.getPlugin();

    /**
     * Gets the plugin name, as stated in the plugin description file (plugin.yml).
     *
     * @return The plugin name, stated in plugin.yml
     */
    public static String getName() {
        return i.getDescription().getName();
    }

    /**
     * Gets the plugin version, as stated in the plugin description file (plugin.yml).
     *
     * @return The plugin version, stated in plugin.yml
     */
    public static String getVersion() {
        return i.getDescription().getVersion();
    }

    /**
     * If value is null, return the default value. Otherwise, return the given value.
     *
     * @param nullable The object that may be null
     * @param def If the nullable is in fact null, return this default object
     * @return Nullable or def
     */
    public static <T> T getOrDefault(T nullable, T def) {
        return nullable != null ? nullable : def;
    }
}
