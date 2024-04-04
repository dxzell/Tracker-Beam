package com.dxzell.trackerbeam.settings.beaconsettings;

import com.dxzell.trackerbeam.settings.ColorConverter;
import com.dxzell.trackerbeam.settings.GuiBuilder;
import com.dxzell.trackerbeam.configs.BeaconConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class BeaconSettings {

    private static final Inventory settingsInv = Bukkit.createInventory(null, 9, ChatColor.BLACK + ">> " + ChatColor.WHITE + "Beacon Settings: ");

    public static void openSettings(Player player) {
        Material colorMat = BeaconConfig.getInstance().getColorMaterial();
        String color = colorMat.toString().replace("_STAINED_GLASS", "").replace("_", " ");
        int distance = BeaconConfig.getInstance().getDistance();

        settingsInv.setItem(2, GuiBuilder.buildItemStack(Material.valueOf(colorMat.toString() + "_PANE"),
                ChatColor.GOLD + "Beam Color", ChatColor.GRAY + "Decide what color the°" + ChatColor.GRAY + "beacon beam projects.°°" + ChatColor.GRAY + "Current color: " + ColorConverter.getColorByMaterial(colorMat) + color  + "°°" + ChatColor.YELLOW  + "[Click to change]", false));

        settingsInv.setItem(4, GuiBuilder.buildItemStack(Material.COMPASS, ChatColor.GOLD + "Meter count", ChatColor.GRAY + "Will show the amount of°" + ChatColor.GRAY + "meters to the destination°" + ChatColor.GRAY + "in the players actionbar°" + ChatColor.GRAY + "when enabled.°°" + ChatColor.GRAY + "Currently: " + (BeaconConfig.getInstance().getMeterCount() ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled") + "°°" + ChatColor.YELLOW + "[Click to toggle]", false));

        settingsInv.setItem(6, GuiBuilder.buildItemStack(Material.ENDER_PEARL,
                ChatColor.GOLD + "Beam Distance", ChatColor.GRAY + "Set in what distance°" + ChatColor.GRAY + "relative to the player°" + ChatColor.GRAY + "the beacon beam will spawn.°°" + ChatColor.GRAY + "Current distance: " + ChatColor.GOLD + distance  + "°°" + ChatColor.YELLOW + "[Click to change]", false));

        player.openInventory(settingsInv);
    }

}
