package com.unclecole.bossskins.utils;

import com.unclecole.bossskins.BossSkins;
import com.unclecole.bossskins.objects.Item;
import com.unclecole.bossskins.objects.Skin;
import jdk.jpackage.internal.Log;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Boss;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.A;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class ConfigUtil {

    @Getter private int guiSize;
    @Getter private String guiTitle;
    @Getter private Material material;
    @Getter private List<Skin> skins;
    @Getter private List<Item> placeholders;

    @Getter private String skinAllowed;
    @Getter private String skinDenied;

    public void loadGUI() {
        skins = new ArrayList<>();
        placeholders = new ArrayList<>();
        if(Material.getMaterial(BossSkins.getInstance().getConfig().getString("Material")) == null) {
            System.out.println("Invalid Material: " + BossSkins.getInstance().getConfig().getString("Material") + " plugin disabled!");
            Bukkit.getServer().getPluginManager().disablePlugin(BossSkins.getInstance());
            return;
        }
        material = Material.getMaterial(BossSkins.getInstance().getConfig().getString("Material"));
        guiSize = BossSkins.getInstance().getConfig().getInt("SkinGUI.Size");
        guiTitle = BossSkins.getInstance().getConfig().getString("SkinGUI.Title");
        skinAllowed = BossSkins.getInstance().getConfig().getString("SkinAllowed");
        skinDenied = BossSkins.getInstance().getConfig().getString("SkinDenied");


        for(String key : BossSkins.getInstance().getConfig().getConfigurationSection("Skins").getKeys(false)) {
            ItemBuilder item = new ItemBuilder(BossSkins.getConfigUtil().getMaterial());
            item.setName(C.color(BossSkins.getInstance().getConfig().getString("Skins." + key + ".Name")));
            BossSkins.getInstance().getConfig().getStringList("Skins." + key + ".Lore").forEach(String -> {
                item.addLore(C.color(String));
            });
            item.setCustomModelData(BossSkins.getInstance().getConfig().getInt("Skins." + key + ".CustomModelData"));
            skins.add(new Skin(item, BossSkins.getInstance().getConfig().getInt("Skins." + key + ".Slot"), BossSkins.getInstance().getConfig().getString("Skins." + key + ".Permission")));
        }
        for(String key : BossSkins.getInstance().getConfig().getConfigurationSection("Placeholders").getKeys(false)) {
            ItemBuilder item = new ItemBuilder(Material.getMaterial(BossSkins.getInstance().getConfig().getString("Placeholders." + key + ".Material")));
            item.setName(C.color(BossSkins.getInstance().getConfig().getString("Placeholders." + key + ".Name")));
            BossSkins.getInstance().getConfig().getStringList("Placeholders." + key + ".Lore").forEach(String -> {
                item.addLore(C.color(String));
            });
            placeholders.add(new Item(BossSkins.getInstance().getConfig().getIntegerList("Placeholders." + key + ".Slots"), item));
        }
    }

}
