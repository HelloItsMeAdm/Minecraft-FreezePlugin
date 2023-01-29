package com.helloitsmeadm;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

import static com.helloitsmeadm.FreezeCommand.openInventory;

public class Listeners implements Listener {
    final FreezePlugin freezePlugin;

    public Listeners(FreezePlugin freezePlugin) {
        this.freezePlugin = freezePlugin;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (this.freezePlugin.databaseManager.isFrozen(event.getWhoClicked().getName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onIC(final InventoryCloseEvent event) {
        if (this.freezePlugin.databaseManager.isFrozen(event.getPlayer().getName())) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("FreezePlugin")), () -> openInventory((Player) event.getPlayer()), 1);
        }
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (this.freezePlugin.databaseManager.isFrozen(player.getName())) {
            Bukkit.getLogger().info("Player " + player.getName() + " has disconnected while being frozen.");
            this.freezePlugin.databaseManager.removeFrozenPlayer(player.getName());
        }
    }
}