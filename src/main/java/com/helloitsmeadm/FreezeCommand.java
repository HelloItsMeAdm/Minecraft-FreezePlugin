package com.helloitsmeadm;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class FreezeCommand implements CommandExecutor {
    static Inventory inv;

    public static void openInventory(Player player) {
        player.openInventory(inv);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0] == null) {
            sender.sendMessage(DatabaseManager.getConfig("prefix") + DatabaseManager.getConfig("noTarget"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            sender.sendMessage(DatabaseManager.getConfig("prefix") + DatabaseManager.getConfig("targetNotFound"));
            return true;
        } else if (target == sender) {
            sender.sendMessage(DatabaseManager.getConfig("prefix") + DatabaseManager.getConfig("targetIsSelf"));
            return true;
        } else if (DatabaseManager.isFrozen(target.getName())) {
            sender.sendMessage(DatabaseManager.getConfig("prefix") + DatabaseManager.getConfig("targetAlreadyFrozen"));
            return true;
        }
        freeze(target, sender);
        return true;
    }

    private void freeze(Player target, CommandSender sender) {
        // Freeze movement
        target.setWalkSpeed(0);
        target.setAllowFlight(false);
        target.setFlying(false);

        // Disable damage
        target.setInvulnerable(true);

        // Prevent player from moving items
        target.setCanPickupItems(false);

        // Create new inventory
        buildInventory(target);

        // Add player to database
        DatabaseManager.addFrozenPlayer(target.getName());

        sender.sendMessage(DatabaseManager.getConfig("prefix") + DatabaseManager.getConfig("targetFrozen").replace("%target%", target.getName()));
        target.sendMessage(DatabaseManager.getConfig("prefix") + DatabaseManager.getConfig("targetFreezeChatMessage"));
    }

    private void buildInventory(Player target) {
        inv = Bukkit.createInventory(null, 9, DatabaseManager.getConfig("inventoryTitle"));
        inv.setItem(4, new ItemBuilder().toItemStack());
        openInventory(target);
    }
}
