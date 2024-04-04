package com.dxzell.trackerbeam.beaconmechanic;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.HashMap;

public abstract class TrackManager {

    public static HashMap<Player, Destination> currentTrack = new HashMap<>();
    public static void addTrack(Player player, Destination destination) {
        currentTrack.put(player, destination);
    }

    public static void removeTrack(Player player) {
        currentTrack.remove(player);
    }

    public static Destination getPlayersDestination(Player player) {
        return currentTrack.get(player);
    }
}
