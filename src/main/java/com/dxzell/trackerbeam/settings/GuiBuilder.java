package com.dxzell.trackerbeam.settings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiBuilder {

    public static ItemStack buildItemStack(Material mat, String displayName, String lore, boolean enchantment) {
        ItemStack stack = new ItemStack(mat);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);

        // Splitting the lore

        String[] splitLore = lore.split("°");
        List<String> loreList = new ArrayList<>();
        for (String split : splitLore) {
            loreList.add(split);
        }
        meta.setLore(loreList);

        // End

        if (enchantment) meta.addEnchant(Enchantment.KNOCKBACK, 0, false);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stack.setItemMeta(meta);

        return stack;

    }

    public static String cutString(String text) { // Cuts the string so it fits into the item lore


        ChatColor color = ChatColor.GRAY;
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        for (String string : text.split(" ")) {
            counter += string.length();
            if (!string.contains("&")) {
                if (counter > 20) {
                    builder.append(color + string + "°");
                    counter = 0;
                } else {
                    builder.append(color + string + " ");
                }
            } else {
                if (counter > 20) {
                    builder.append(string + "°");
                    counter = 0;
                } else {
                    builder.append(string + " ");
                }
            }

            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '&') {
                    if (i + 1 <= string.length()) {
                        color = ChatColor.getByChar(string.charAt(i + 1));
                    }
                }
            }
        }
        return builder.toString();
    }
}
