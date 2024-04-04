package com.dxzell.trackerbeam.beaconmechanic;

import com.dxzell.trackerbeam.TrackerBeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.PortalCreateEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DestinationListener implements Listener {

    private List<Player> cooldown = new ArrayList<>();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Destination playersDestination = TrackManager.getPlayersDestination(e.getPlayer());
        Player player = e.getPlayer();
        if (playersDestination != null) {
            if (!cooldown.contains(player)) {
                playersDestination.checkDistance();
                cooldown.add(player);
                Bukkit.getScheduler().runTaskLater(TrackerBeam.getMainInstance(), () -> {
                    cooldown.remove(player);
                }, 20L);
            }
            playersDestination.hasArrived();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Destination playersDestination = TrackManager.getPlayersDestination(e.getPlayer());
        Player player = e.getPlayer();
        if (playersDestination != null) {
            playersDestination.stopTracking();
        }
    }
}
