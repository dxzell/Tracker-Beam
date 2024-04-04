package com.dxzell.trackerbeam.settings.distancesettings;

import com.dxzell.trackerbeam.configs.BeaconConfig;
import com.dxzell.trackerbeam.settings.GuiBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DistanceSettings {

    private static final Inventory distanceInv = Bukkit.createInventory(null, 9, ChatColor.BLACK + ">> " + ChatColor.WHITE + "Pick distance: ");

    public static void openDistance(Player player) {

        for (int i = 0; i < distanceInv.getSize(); i++) {
            distanceInv.setItem(i, GuiBuilder.buildItemStack(Material.GRAY_STAINED_GLASS_PANE, " ", "", false));
        }

        distanceInv.setItem(1, GuiBuilder.buildItemStack(Material.REDSTONE_TORCH,
                ChatColor.RED.toString() + ChatColor.BOLD.toString() + "-", ChatColor.GRAY + "Lower the beacon distance.°" + ChatColor.GRAY + "The minimum is 10 blocks.°°" +
                        ChatColor.GRAY + "Current distance: " + ChatColor.GOLD + BeaconConfig.getInstance().getDistance() + "°°" + ChatColor.YELLOW + "[Click to lower]", false));

        distanceInv.setItem(4, GuiBuilder.buildItemStack(Material.EMERALD_BLOCK, ChatColor.GOLD + "Return to settings", "", false));

        distanceInv.setItem(7, GuiBuilder.buildItemStack(Material.TORCH,
                ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "+", ChatColor.GRAY + "Higher the beacon distance.°" + ChatColor.GRAY + "The maximum is 50 blocks.°°" +
                        ChatColor.GRAY + "Current distance: " + ChatColor.GOLD + BeaconConfig.getInstance().getDistance() + "°°" + ChatColor.YELLOW + "[Click to higher]", false));

        player.openInventory(distanceInv);
    }

    public static void updateDistance() {
        distanceInv.setItem(1, GuiBuilder.buildItemStack(Material.REDSTONE_TORCH,
                ChatColor.RED.toString() + ChatColor.BOLD.toString() + "-", ChatColor.GRAY + "Lower the beacon distance.°" + ChatColor.GRAY + "The minimum is 10 blocks.°°" +
                        ChatColor.GRAY + "Current distance: " + ChatColor.GOLD + BeaconConfig.getInstance().getDistance() + "°°" + ChatColor.YELLOW + "[Click to lower]", false));

        distanceInv.setItem(7, GuiBuilder.buildItemStack(Material.TORCH,
                ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "+", ChatColor.GRAY + "Higher the beacon distance.°" + ChatColor.GRAY + "The maximum is 50 blocks.°°" +
                        ChatColor.GRAY + "Current distance: " + ChatColor.GOLD + BeaconConfig.getInstance().getDistance() + "°°" + ChatColor.YELLOW + "[Click to higher]", false));
    }
}
