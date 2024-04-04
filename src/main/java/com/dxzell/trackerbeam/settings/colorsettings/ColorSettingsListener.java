package com.dxzell.trackerbeam.settings.colorsettings;

import com.dxzell.trackerbeam.configs.BeaconConfig;
import com.dxzell.trackerbeam.settings.beaconsettings.BeaconSettings;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ColorSettingsListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(ChatColor.BLACK + ">> " + ChatColor.WHITE + "Pick color: ")) {

            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem == null || !clickedItem.hasItemMeta() || !clickedItem.getItemMeta().hasDisplayName()) {
                return;
            }

            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            String clickedName = e.getCurrentItem().getItemMeta().getDisplayName();

            if (clickedName.equals(ChatColor.GREEN + "Return to settings")) {
                BeaconSettings.openSettings(player);
            } else if (e.getCurrentItem().getType().toString().contains("_STAINED_GLASS")) {
                BeaconConfig.getInstance().setColorMaterial(e.getCurrentItem().getType().toString().replace("_PANE", ""));
                BeaconSettings.openSettings(player);
            }
        }
    }
}
