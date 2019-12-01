package com.demeng7215.cytsoulbound.commands;

import com.demeng7215.cytsoulbound.Message;
import com.demeng7215.cytsoulbound.SoulboundItem;
import com.demeng7215.cytsoulbound.lib.utils.CustomCommand;
import com.demeng7215.cytsoulbound.lib.utils.messages.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SoulboundCmd extends CustomCommand {

    public SoulboundCmd() {
        super("soulbound");
    }

    @Override
    protected void run(CommandSender sender, String[] args) {

        if (!checkArgsStrict(args, 2, sender, Message.INVALID_USAGE.toString())) return;

        if (!checkHasPerm("cytsoulbound.admin", sender, Message.NO_PERMISSION.toString())) return;

        if (Bukkit.getPlayer(args[1]) == null) {
            MessageUtils.tell(sender, Message.NOT_ONLINE.toString().replace("%player%", args[1]));
            return;
        }

        final Player p = Bukkit.getPlayer(args[1]);

        if (args[0].equalsIgnoreCase("add")) {

            // If the player is holding nothing, stop.
            if (isHoldingNothing(p, sender)) return;

            if(SoulboundItem.isSoulboundItem(p.getInventory().getItemInMainHand())) {
                MessageUtils.tell(sender, Message.ALREADY_SOULBOUND.toString());
                return;
            }

            final ItemStack soulboundItem = SoulboundItem.setSoulbound(p.getInventory().getItemInMainHand());

            // Replace the normal item with the soulbound item.
            p.getInventory().remove(p.getInventory().getItemInMainHand());
            p.getInventory().setItemInMainHand(soulboundItem);

            MessageUtils.tell(sender, Message.SUCCESSFULLY_ADDED.toString());

            return;
        }

        if (args[0].equalsIgnoreCase("remove")) {

            // If the player is holding nothing, stop.
            if (isHoldingNothing(p, sender)) return;

            if (!SoulboundItem.isSoulboundItem(p.getInventory().getItemInMainHand())) {
                MessageUtils.tell(sender, Message.NOT_SOULBOUND.toString());
                return;
            }

            final ItemStack normalItem = SoulboundItem.removeSoulbound(p.getInventory().getItemInMainHand());

            // Replace the soulbound item with the normal item.
            p.getInventory().remove(p.getInventory().getItemInMainHand());
            p.getInventory().setItemInMainHand(normalItem);

            MessageUtils.tell(sender, Message.SUCCESSFULLY_REMOVED.toString());
        }
    }

    // Checks if the player is actually holding something.
    private boolean isHoldingNothing(Player p, CommandSender sender) {

        if (p.getInventory().getItemInMainHand().getType().name().endsWith("AIR")) {
            MessageUtils.tell(sender, Message.EMPTY_HAND.toString());
            return true;
        }

        return false;
    }
}
