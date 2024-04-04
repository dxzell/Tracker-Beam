package com.dxzell.trackerbeam.beaconmechanic;

import com.dxzell.trackerbeam.configs.MessageConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UntrackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("untrack")) {
                Player player = (Player) sender;
                Destination playersDestination = TrackManager.getPlayersDestination(player);
                if (playersDestination != null) {
                    playersDestination.stopTracking();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageConfig.getInstance().getUntrack()));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageConfig.getInstance().getNoCurrentTrack()));
                }
            }
        }
        return false;
    }
}
