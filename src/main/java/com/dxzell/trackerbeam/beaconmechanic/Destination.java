package com.dxzell.trackerbeam.beaconmechanic;

import com.dxzell.trackerbeam.TrackerBeam;
import com.dxzell.trackerbeam.configs.BeaconConfig;
import com.dxzell.trackerbeam.configs.MessageConfig;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Destination {

    private final Player player;
    private final Location destination;
    private Vector unitVector;
    private double currentDistance; // Distance to current beacon
    private HashMap<Player, List<Location>> changedBlocks = new HashMap<>();
    private List<Player> delayPlayers = new ArrayList<>();
    private Location beamLoc;
    private BukkitTask meterRunnable;

    public Destination(final Player player, final Location destination, final Vector unitVector) {
        this.player = player;
        this.destination = destination;
        this.unitVector = unitVector;

        startTracking();
    }

    public void updateVector() { // Needed because vectors are changing whenever player moves
        int x = destination.getBlockX();
        int z = destination.getBlockZ();
        Location currentPlayerLoc = player.getLocation();
        Vector vector = new Vector(x - currentPlayerLoc.getBlockX(), 0, z - currentPlayerLoc.getBlockZ());
        unitVector = vector.clone().normalize();
    }

    public void startTracking() {
        createBeam();
        startMeterCount();
    }

    private void startMeterCount() { // Shows the amount of meters to the destination in the players actionbar
        meterRunnable = Bukkit.getScheduler().runTaskTimer(TrackerBeam.getMainInstance(), () -> {
            if (BeaconConfig.getInstance().getMeterCount()) {
                Location currentPlayersLoc = player.getLocation().clone();
                currentPlayersLoc.setY(0);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "" + ((int) (destination.distance(currentPlayersLoc))) + "m"));
            }
        }, 0L, 5L);
    }

    private void stopMeterCount() { // Stops the meter count
        if (meterRunnable != null && !meterRunnable.isCancelled()) {
            meterRunnable.cancel();
        }
    }

    /*
    This method spawns a new beacon once the player moves away from the current beacon. The beacon
    only stays unupdated when the player is running closer to it. It will be updated once the player is
    really close to the beacon (f.e. 5 blocks)

    It will also spawn a new beacon when the player is really close to the current beacon
     */

    public void checkDistance() {
        Location current = player.getLocation().clone();
        current.setY(0);
        Location beam = beamLoc.clone();
        beam.setY(0);

        // Player moved away from the current Beacon or is really close -> spawn new Beacon
        if (current.distance(beam) > currentDistance || (current.distance(beam) <= 5
                && !(beam.getBlockX() == destination.getBlockX() && beam.getBlockZ() == destination.getBlockZ()))) {
            createBeam();
        }
    }

    public void hasArrived() { // Checkst whether the player is standing on the final destination
        Location playerLocation = player.getLocation().clone();
        if (playerLocation.getWorld().equals(destination.getWorld()) &&
                (playerLocation.getBlockX() == destination.getBlockX()) && (playerLocation.getBlockZ() == destination.getBlockZ())) {
            // Player has reached his location
            stopTracking();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageConfig.getInstance().getReachedLocation()));
        }
    }

    public void stopTracking() { // Reset block changes and remove from TrackManager
        if (changedBlocks.get(player) != null) {
            resetBlockChanges(changedBlocks.get(player));
        }
        stopMeterCount();
        TrackManager.removeTrack(player);
    }

    private void createBeam() {
        updateVector();
        int spawnDistance = BeaconConfig.getInstance().getDistance();
        beamLoc = player.getLocation().clone().add(unitVector.multiply(spawnDistance)); // Beam Spawn Location
        beamLoc = beamLoc.getWorld().getHighestBlockAt(beamLoc).getLocation(); // Get highest Location

        Location current = player.getLocation().clone();
        current.setY(0);
        Location beam = beamLoc.clone();
        beam.setY(0);
        currentDistance = current.distance(beam); // Save this distance from the Player to the Beacon

        /*
        If the distance from the player to the final destination is smaller than
        the distance from the player to the beacon, then the beacon should just spawn exactly
        on the final destination.
         */
        if (current.distance(destination) < currentDistance) {
            beamLoc = destination.clone();
            beamLoc = beamLoc.getWorld().getHighestBlockAt(beamLoc).getLocation();
        }

        if (changedBlocks.get(player) != null) {
            resetBlockChanges(changedBlocks.get(player));
        }

        List<Location> changed = new ArrayList<>();

        player.sendBlockChange(beamLoc.clone().add(0, -1, 0), Material.BEACON.createBlockData());
        changed.add(beamLoc.clone().add(0, -1, 0));

        player.sendBlockChange(beamLoc.clone().add(0, 0, 0), BeaconConfig.getInstance().getColorMaterial().createBlockData());
        changed.add(beamLoc.clone().add(0, 0, 0));

        player.sendBlockChange(beamLoc.clone().add(-1, -2, -1), Material.IRON_BLOCK.createBlockData());
        changed.add(beamLoc.clone().add(-1, -2, -1));

        player.sendBlockChange(beamLoc.clone().add(-1, -2, 1), Material.IRON_BLOCK.createBlockData());
        changed.add(beamLoc.clone().add(-1, -2, 1));

        player.sendBlockChange(beamLoc.clone().add(1, -2, 1), Material.IRON_BLOCK.createBlockData());
        changed.add(beamLoc.clone().add(1, -2, 1));

        player.sendBlockChange(beamLoc.clone().add(1, -2, -1), Material.IRON_BLOCK.createBlockData());
        changed.add(beamLoc.clone().add(1, -2, -1));

        player.sendBlockChange(beamLoc.clone().add(0, -2, -1), Material.IRON_BLOCK.createBlockData());
        changed.add(beamLoc.clone().add(0, -2, -1));

        player.sendBlockChange(beamLoc.clone().add(0, -2, 1), Material.IRON_BLOCK.createBlockData());
        changed.add(beamLoc.clone().add(0, -2, 1));

        player.sendBlockChange(beamLoc.clone().add(-1, -2, 0), Material.IRON_BLOCK.createBlockData());
        changed.add(beamLoc.clone().add(-1, -2, 0));

        player.sendBlockChange(beamLoc.clone().add(0, -2, 0), Material.IRON_BLOCK.createBlockData());
        changed.add(beamLoc.clone().add(0, -2, 0));

        player.sendBlockChange(beamLoc.clone().add(1, -2, 0), Material.IRON_BLOCK.createBlockData());
        changed.add(beamLoc.clone().add(1, -2, 0));

        player.sendBlockChange(beamLoc.clone().add(0, -2, 0), Material.IRON_BLOCK.createBlockData());
        changed.add(beamLoc.clone().add(0, -2, 0));

        changedBlocks.put(player, changed);
    }

    private void resetBlockChanges(List<Location> changes) { // Resets the block changes at the in the list given locations
        delayPlayers.add(player);

        changes.forEach(loc -> {
            player.sendBlockChange(loc, loc.getBlock().getBlockData());
        });

        delayPlayers.remove(player);
    }
}
