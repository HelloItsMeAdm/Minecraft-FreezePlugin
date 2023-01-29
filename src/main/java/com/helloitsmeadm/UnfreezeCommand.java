package com.helloitsmeadm;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class UnfreezeCommand implements CommandExecutor {
    final FreezePlugin freezePlugin;

    public UnfreezeCommand(FreezePlugin freezePlugin) {
        this.freezePlugin = freezePlugin;
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
        } else if (!this.freezePlugin.databaseManager.isFrozen(target.getName())) {
            sender.sendMessage(this.freezePlugin.databaseManager.getConfig("prefix") + this.freezePlugin.databaseManager.getConfig("targetNotFrozen"));
            return true;
        }
        unfreeze(target, sender);
        return true;
    }

    private void unfreeze(Player target, CommandSender sender) {
        // Unfreeze movement
        target.setWalkSpeed(0.2f);

        // Disable damage
        target.setInvulnerable(false);

        // Prevent player from moving items
        target.setCanPickupItems(true);

        // Remove player from database
        this.freezePlugin.databaseManager.removeFrozenPlayer(target.getName());

        sender.sendMessage(this.freezePlugin.databaseManager.getConfig("prefix") + this.freezePlugin.databaseManager.getConfig("targetUnfrozen").replace("%target%", target.getName()));
        target.sendMessage(this.freezePlugin.databaseManager.getConfig("prefix") + this.freezePlugin.databaseManager.getConfig("targetUnfreezeChatMessage"));

        // Close inventory after delay
        Bukkit.getScheduler().scheduleSyncDelayedTask(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("FreezePlugin")), target::closeInventory, 1);
    }
}
