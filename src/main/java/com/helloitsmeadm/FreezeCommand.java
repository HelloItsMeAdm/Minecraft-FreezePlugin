package com.helloitsmeadm;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class FreezeCommand implements CommandExecutor {
    static Inventory inv;
    final FreezePlugin freezePlugin;

    public FreezeCommand(FreezePlugin freezePlugin) {
        inv = Bukkit.createInventory(null, 9, freezePlugin.databaseManager.getConfig("inventoryTitle"));
        this.freezePlugin = freezePlugin;
    }

    public static void openInventory(Player player) {
        player.openInventory(inv);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0] == null) {
            sender.sendMessage(this.freezePlugin.databaseManager.getConfig("prefix") + this.freezePlugin.databaseManager.getConfig("noTarget"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            sender.sendMessage(this.freezePlugin.databaseManager.getConfig("prefix") + this.freezePlugin.databaseManager.getConfig("targetNotFound"));
            return true;
        } else if (target == sender) {
            sender.sendMessage(this.freezePlugin.databaseManager.getConfig("prefix") + this.freezePlugin.databaseManager.getConfig("targetIsSelf"));
            return true;
        } else if (this.freezePlugin.databaseManager.isFrozen(target.getName())) {
            sender.sendMessage(this.freezePlugin.databaseManager.getConfig("prefix") + this.freezePlugin.databaseManager.getConfig("targetAlreadyFrozen"));
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
        this.freezePlugin.databaseManager.addFrozenPlayer(target.getName());

        sender.sendMessage(this.freezePlugin.databaseManager.getConfig("prefix") + this.freezePlugin.databaseManager.getConfig("targetFrozen").replace("%target%", target.getName()));
        target.sendMessage(this.freezePlugin.databaseManager.getConfig("prefix") + this.freezePlugin.databaseManager.getConfig("targetFreezeChatMessage"));
    }

    private void buildInventory(Player target) {
        inv = Bukkit.createInventory(null, 9, this.freezePlugin.databaseManager.getConfig("inventoryTitle"));
        inv.setItem(4, new ItemBuilder(this.freezePlugin).toItemStack());
        openInventory(target);
    }
}
