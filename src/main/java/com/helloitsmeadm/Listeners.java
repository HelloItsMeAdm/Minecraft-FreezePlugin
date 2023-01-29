package com.helloitsmeadm;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;

import java.util.Objects;

import static com.helloitsmeadm.FreezeCommand.openInventory;

public class Listeners implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryView view = event.getView();
        if (view.getTitle().equals("§c§lByl jsi zmražen!")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onIC(final InventoryCloseEvent e) {
        InventoryView view = e.getView();
        if (view.getTitle().equals("§c§lByl jsi zmražen!")) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("FreezePlugin")), () -> openInventory((Player) e.getPlayer()), 1);
        }
    }
}