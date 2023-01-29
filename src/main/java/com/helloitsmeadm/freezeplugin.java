package com.helloitsmeadm;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class freezeplugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("FreezePlugin has been enabled!");

        getCommand("freeze").setExecutor(new FreezeCommand());
        //getCommand("unfreeze").setExecutor(new UnfreezeCommand());

        getServer().getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("FreezePlugin has been disabled!");
    }
}
