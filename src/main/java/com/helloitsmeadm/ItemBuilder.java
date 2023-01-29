package com.helloitsmeadm;

import org.bukkit.Material;

public class ItemBuilder {
    private final Material material;
    private String name;
    private String[] lore;
    private boolean enchanted;

    public ItemBuilder(Material material) {
        this.material = material;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder setEnchanted(boolean enchanted) {
        this.enchanted = enchanted;
        return this;
    }

    public org.bukkit.inventory.ItemStack toItemStack() {
        org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(material);
        org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();
        assert meta != null;
        assert name != null;
        meta.setDisplayName(name);
        meta.setLore(java.util.Arrays.asList(lore));
        if (enchanted) {
            meta.addEnchant(org.bukkit.enchantments.Enchantment.DURABILITY, 1, true);
            meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        return item;
    }
}
