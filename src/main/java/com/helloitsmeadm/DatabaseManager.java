package com.helloitsmeadm;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class DatabaseManager {
    private static File file;
    private static FileConfiguration config;
    public static void addFrozenPlayer(String player) {
        file = new File("plugins/FreezePlugin/frozenPlayers.yml");
        config = YamlConfiguration.loadConfiguration(file);
        config.set("frozenPlayers." + player, true);
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeFrozenPlayer(String player) {
        file = new File("plugins/FreezePlugin/frozenPlayers.yml");
        config = YamlConfiguration.loadConfiguration(file);
        config.set("frozenPlayers." + player, null);
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isFrozen(String player) {
        file = new File("plugins/FreezePlugin/frozenPlayers.yml");
        config = YamlConfiguration.loadConfiguration(file);
        return config.getBoolean("frozenPlayers." + player);
    }
}
