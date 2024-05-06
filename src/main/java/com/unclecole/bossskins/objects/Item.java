package com.unclecole.bossskins.objects;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Item {

    @Getter private List<Integer> slots;
    @Getter private ItemStack itemStack;

    public Item(List<Integer> slots, ItemStack itemStack) {
        this.slots = slots;
        this.itemStack = itemStack;
    }
}
