package com.helloitsmeadm;

import org.bukkit.Material;

import java.util.Objects;

public class ItemBuilder {

    public ItemBuilder() {
    }

    public org.bukkit.inventory.ItemStack toItemStack() {
        org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(Objects.requireNonNull(Material.getMaterial(DatabaseManager.getConfig("itemId"))));
        org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(DatabaseManager.getConfig("itemName"));
        meta.setLore(DatabaseManager.getConfigList("itemLore"));
        if (DatabaseManager.getConfig("itemGlow").equals("true")) {
            meta.addEnchant(org.bukkit.enchantments.Enchantment.DURABILITY, 1, true);
            meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        return item;
    }
}
