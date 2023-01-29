package com.helloitsmeadm;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
    private static YamlConfiguration config;
    private final Map<String, Boolean> frozenPlayers = new HashMap<>();

    public DatabaseManager() {
        DatabaseManager.config = YamlConfiguration.loadConfiguration(new File("plugins/FreezePlugin/config.yml"));
    }

    public void addFrozenPlayer(String player) {
        frozenPlayers.put(player, true);
    }

    public void removeFrozenPlayer(String player) {
        frozenPlayers.remove(player);
    }

    public boolean isFrozen(String player) {
        return frozenPlayers.containsKey(player);
    }

    public String getConfig(String config) {
        return DatabaseManager.config.getString(config);
    }

    public List<String> getConfigList(String config) {
        return DatabaseManager.config.getStringList(config);
    }
}
