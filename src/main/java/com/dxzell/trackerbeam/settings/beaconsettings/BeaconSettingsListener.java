package com.dxzell.trackerbeam.settings.beaconsettings;

import com.dxzell.trackerbeam.configs.BeaconConfig;
import com.dxzell.trackerbeam.settings.colorsettings.ColorSettings;
import com.dxzell.trackerbeam.settings.distancesettings.DistanceSettings;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BeaconSettingsListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(ChatColor.BLACK + ">> " + ChatColor.WHITE + "Beacon Settings: ")) {

            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem == null || !clickedItem.hasItemMeta() || !clickedItem.getItemMeta().hasDisplayName()) {
                return;
            }

            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            String clickedName = e.getCurrentItem().getItemMeta().getDisplayName();

            if (clickedName.equals(ChatColor.GOLD + "Beam Color")) {
                ColorSettings.openColors(player);
            } else if (clickedName.equals(ChatColor.GOLD + "Beam Distance")) {
                DistanceSettings.openDistance(player);
            } else if(clickedName.equals(ChatColor.GOLD + "Meter count")) {
                BeaconConfig.getInstance().toggleMeterCount();
                BeaconSettings.openSettings(player);
            }
        }
    }
}
