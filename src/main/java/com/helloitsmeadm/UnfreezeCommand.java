package com.helloitsmeadm;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class UnfreezeCommand implements CommandExecutor {
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
        } else if (!DatabaseManager.isFrozen(target.getName())) {
            sender.sendMessage(DatabaseManager.getConfig("prefix") + DatabaseManager.getConfig("targetNotFrozen"));
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
        DatabaseManager.removeFrozenPlayer(target.getName());

        sender.sendMessage(DatabaseManager.getConfig("prefix") + DatabaseManager.getConfig("targetUnfrozen").replace("%target%", target.getName()));
        target.sendMessage(DatabaseManager.getConfig("prefix") + DatabaseManager.getConfig("targetUnfreezeChatMessage"));

        // Close inventory after delay
        Bukkit.getScheduler().scheduleSyncDelayedTask(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("FreezePlugin")), target::closeInventory, 1);
    }
}
