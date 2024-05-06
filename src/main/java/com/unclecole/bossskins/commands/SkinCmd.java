package com.unclecole.bossskins.commands;

import com.unclecole.bossskins.BossSkins;
import com.unclecole.bossskins.utils.C;
import com.unclecole.bossskins.utils.ConfigUtil;
import com.unclecole.bossskins.utils.TL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;

public class SkinCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String string, String[] args) {
        if(!(s instanceof Player)) return false;

        Player player = (Player) s;

        if(!player.hasPermission("bosskin.skin")) {
            TL.NO_PERMISSION.send(player);
            return false;
        }

        if(!player.getInventory().getItemInMainHand().getType().equals(BossSkins.getConfigUtil().getMaterial())) return false;

        InventoryGUI gui = new InventoryGUI(BossSkins.getConfigUtil().getGuiSize(), C.color(BossSkins.getConfigUtil().getGuiTitle()));

        BossSkins.getConfigUtil().getSkins().forEach(skin -> {
            ItemStack itemStack = skin.getItem().clone();
            ItemMeta meta = itemStack.getItemMeta();
            String displayName = itemStack.getItemMeta().getDisplayName();
            if(player.hasPermission(skin.getPermission())) {
                meta.setDisplayName(displayName.replace("%skin_permission%",C.color(BossSkins.getConfigUtil().getSkinAllowed())));
                itemStack.setItemMeta(meta);
                gui.addButton(skin.getSlot(), new ItemButton(itemStack) {
                    @Override
                    public void onClick(InventoryClickEvent e) {
                        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(skin.getItem().getItemMeta().getCustomModelData());
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                    }
                });
            } else {
                meta.setDisplayName(displayName.replace("%skin_permission%",C.color(BossSkins.getConfigUtil().getSkinDenied())));
                itemStack.setItemMeta(meta);
                gui.addButton(skin.getSlot(), new ItemButton(itemStack) {
                    @Override
                    public void onClick(InventoryClickEvent e) {

                    }
                });
            }
        });

        BossSkins.getConfigUtil().getPlaceholders().forEach(item -> {
            item.getSlots().forEach(integer -> {
                gui.addButton(integer, new ItemButton(item.getItemStack()) {
                    @Override
                    public void onClick(InventoryClickEvent e) {

                    }
                });
            });
        });
        gui.open(player);

        return false;
    }



    public boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public boolean validPlayer(String string) {
        return (Bukkit.getPlayer(string) != null);
    }
}
