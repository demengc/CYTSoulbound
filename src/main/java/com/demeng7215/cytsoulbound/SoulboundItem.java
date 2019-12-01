package com.demeng7215.cytsoulbound;

import com.demeng7215.cytsoulbound.lib.utils.messages.MessageUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility for the soulbound items.
 */
public class SoulboundItem {

    // Make the item soulbound.
    public static ItemStack setSoulbound(ItemStack item) {

        ItemStack soulboundItem = new ItemStack(item.getType(), 1);

        // The soulbound item's meta.
        ItemMeta meta = soulboundItem.getItemMeta();
        meta = item.getItemMeta();

        // Simply set the lore to the soulbound lore if there is no existing lore.
        if (meta == null || !meta.hasLore() || meta.getLore() == null) {
            meta.setLore(Collections.singletonList(MessageUtils.colorize("&7» &bSoulbound")));

            soulboundItem.setItemMeta(meta);

            return soulboundItem;
        }

        // Add the soulbound lore on top of the current lore.

        List<String> lore = new ArrayList<>(meta.getLore());
        lore.add(MessageUtils.colorize("&7» &bSoulbound"));

        meta.setLore(lore);
        soulboundItem.setItemMeta(meta);

        return soulboundItem;
    }

    // Removes the soulbound lore from the item.
    public static ItemStack removeSoulbound(ItemStack item) {

        ItemStack normalItem = new ItemStack(item.getType(), 1);

        // Remove the soulbound from the lore.
        ItemMeta meta = normalItem.getItemMeta();
        meta = item.getItemMeta();

        List<String> lore = new ArrayList<>(meta.getLore());
        lore.remove(MessageUtils.colorize("&7» &bSoulbound"));

        meta.setLore(lore);
        normalItem.setItemMeta(meta);

        // Return the item with the lore removed.
        return normalItem;
    }

    public static boolean isSoulboundItem(ItemStack item) {

        final ItemMeta meta = item.getItemMeta();

        if (meta == null) return false;

        if (!meta.hasLore()) return false;

        return meta.getLore().contains(MessageUtils.colorize("&7» &bSoulbound"));
    }
}
