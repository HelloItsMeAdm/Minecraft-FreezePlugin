package com.helloitsmeadm;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    public static void createDefaultConfig() throws IOException {
        // Create config file
        file = new File("plugins/FreezePlugin/config.yml");
        config = YamlConfiguration.loadConfiguration(file);

        // Add default values
        config.addDefault("prefix", "§8[§aFreeze§8] §7- ");
        config.addDefault("inventoryTitle", "§cYou are frozen!");
        config.addDefault("itemId", "PAPER");
        config.addDefault("itemName", "§cUnfreeze");
        config.addDefault("itemLore", new String[]{
                "§7Click to unfreeze",
                "§7yourself!"
        });
        config.addDefault("itemGlow", true);

        // Add messages
        config.addDefault("noTarget", "§cYou must specify a target.");
        config.addDefault("targetNotFound", "§cTargetted player was not found.");
        config.addDefault("targetIsSelf", "§cYou cannot freeze yourself.");
        config.addDefault("targetAlreadyFrozen", "§cTargetted player is already frozen.");
        config.addDefault("targetFrozen", "§aPlayer §e%target% §awas frozen.");

        // Save config
        config.options().copyDefaults(true);
        config.save(file);
    }

    public static String getConfig(String config) {
        file = new File("plugins/FreezePlugin/config.yml");
        DatabaseManager.config = YamlConfiguration.loadConfiguration(file);
        return DatabaseManager.config.getString(config);
    }

    public static List<String> getConfigList(String config) {
        file = new File("plugins/FreezePlugin/config.yml");
        DatabaseManager.config = YamlConfiguration.loadConfiguration(file);
        return DatabaseManager.config.getStringList(config);
    }
}
