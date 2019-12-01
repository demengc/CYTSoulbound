package com.demeng7215.cytsoulbound.listeners;

import com.demeng7215.cytsoulbound.SoulboundItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * The main listener, handles soulbounding.
 */
public class DeathEvent implements Listener {

    private Map<Player, List<ItemStack>> soulboundItems = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(final PlayerDeathEvent e) {

        final Player p = e.getEntity();

        List<ItemStack> items = new ArrayList<>();

        Iterator<ItemStack> iterator = e.getDrops().iterator();
        while (iterator.hasNext()) {
            ItemStack i = iterator.next();
            if (SoulboundItem.isSoulboundItem(i)) {
                items.add(i);
                iterator.remove();
            }
        }

        soulboundItems.put(p, items);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(final PlayerRespawnEvent e) {

        final Player p = e.getPlayer();

        if (soulboundItems.get(p) == null) return;

        for (ItemStack item : soulboundItems.get(p)) p.getInventory().addItem(item);

        soulboundItems.remove(p);
    }
}
