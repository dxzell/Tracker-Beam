package com.dxzell.trackerbeam.settings.colorsettings;

import com.dxzell.trackerbeam.settings.ColorConverter;
import com.dxzell.trackerbeam.settings.GuiBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ColorSettings {

    // Saves all possible Beacon Beam colors

    private static final List<Material> colorMaterial = Arrays.stream(Material.values())
            .filter(mat -> mat.toString().contains("_STAINED_GLASS_PANE"))
            .collect(Collectors.toList());

    private static final List<String> colors = colorMaterial.stream()
            .map(mat -> mat.toString().replace("STAINED_GLASS_PANE", ""))
            .map(color -> color.replace("_", " "))
            .collect(Collectors.toList());

    private static final Inventory colorInv = Bukkit.createInventory(null, 27, ChatColor.BLACK + ">> " + ChatColor.WHITE + "Pick color: ");

    public static void openColors(Player player) {
        for (int i = 0; i < colors.size(); i++) {
            colorInv.setItem(i, GuiBuilder.buildItemStack(colorMaterial.get(i), ColorConverter.getColorByMaterial(Material.valueOf(colorMaterial.get(i).toString().replace("_PANE", ""))) + colors.get(i), "°" + ChatColor.YELLOW + "[Click to pick]", false));
        }

        colorInv.setItem(26, GuiBuilder.buildItemStack(Material.EMERALD_BLOCK, ChatColor.GREEN + "Return to settings", "", false));

        player.openInventory(colorInv);
    }

}
