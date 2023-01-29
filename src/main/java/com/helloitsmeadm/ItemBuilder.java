package com.helloitsmeadm;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class ItemBuilder {
    final FreezePlugin freezePlugin;

    public ItemBuilder(FreezePlugin freezePlugin) {
        this.freezePlugin = freezePlugin;
    }

    public org.bukkit.inventory.ItemStack toItemStack() {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(this.freezePlugin.databaseManager.getConfig("itemId"))));
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(this.freezePlugin.databaseManager.getConfig("itemName"));
        meta.setLore(this.freezePlugin.databaseManager.getConfigList("itemLore"));
        if (this.freezePlugin.databaseManager.getConfig("itemGlow").equals("true")) {
            meta.addEnchant(org.bukkit.enchantments.Enchantment.DURABILITY, 1, true);
            meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        return item;
    }
}
