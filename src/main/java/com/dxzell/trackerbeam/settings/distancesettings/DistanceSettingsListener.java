package com.dxzell.trackerbeam.settings.distancesettings;

import com.dxzell.trackerbeam.configs.BeaconConfig;
import com.dxzell.trackerbeam.settings.beaconsettings.BeaconSettings;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class DistanceSettingsListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(ChatColor.BLACK + ">> " + ChatColor.WHITE + "Pick distance: ")) {

            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem == null || !clickedItem.hasItemMeta() || !clickedItem.getItemMeta().hasDisplayName()) {
                return;
            }

            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            String clickedName = e.getCurrentItem().getItemMeta().getDisplayName();

            if (clickedName.contains("+")) {
                BeaconConfig.getInstance().higherDistance();
                DistanceSettings.updateDistance();
            } else if (clickedName.contains("-")) {
                BeaconConfig.getInstance().lowerDistance();
                DistanceSettings.updateDistance();
            } else if (clickedName.equalsIgnoreCase(ChatColor.GOLD + "Return to settings")) {
                BeaconSettings.openSettings(player);
            }
        }
    }
}
