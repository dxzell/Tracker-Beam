package com.dxzell.trackerbeam.beaconmechanic;

import com.dxzell.trackerbeam.configs.MessageConfig;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TrackerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("track")) {
                if (args.length == 2) {
                    try {
                        int x = Integer.parseInt(args[0]);
                        int z = Integer.parseInt(args[1]);
                        Location currentPlayerLoc = player.getLocation();
                        Location trackLoc = player.getLocation().clone();
                        trackLoc.setX(x);
                        trackLoc.setZ(z);
                        trackLoc.setY(0);
                        Vector vector = new Vector(x - currentPlayerLoc.getBlockX(), 0, z - currentPlayerLoc.getBlockZ());

                        Vector unitVector = vector.clone().normalize();

                        Destination playersDestination = TrackManager.getPlayersDestination(player);
                        if (playersDestination != null) {
                            playersDestination.stopTracking();
                        }
                        TrackManager.addTrack(player, new Destination(player, trackLoc, unitVector)); // Starts tracking
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageConfig.getInstance().getSuccessfullyTracking(x, z)));

                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageConfig.getInstance().getNotNumbers()));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageConfig.getInstance().getWrongTrack()));
                }
            }
        }
        return false;
    }
}
