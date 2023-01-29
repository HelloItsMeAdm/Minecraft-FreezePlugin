package com.helloitsmeadm;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class freezeplugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Config
        try {
            DatabaseManager.createDefaultConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
