package com.unclecole.bossskins.objects;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

public class Skin {

    @Getter private ItemStack item;
    @Getter private Integer slot;
    @Getter private String permission;

    public Skin(ItemStack item, Integer slot, String permission) {
        this.item = item;
        this.slot = slot;
        this.permission = permission;
    }
}
