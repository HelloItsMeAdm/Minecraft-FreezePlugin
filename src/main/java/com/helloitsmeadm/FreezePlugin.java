package com.helloitsmeadm;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class FreezePlugin extends JavaPlugin {

    public DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        databaseManager = new DatabaseManager();

        getCommand("freeze").setExecutor(new FreezeCommand(this));
        getCommand("unfreeze").setExecutor(new UnfreezeCommand(this));

        // Register events
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Listeners(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("FreezePlugin has been disabled!");
    }
}
