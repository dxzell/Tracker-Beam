package com.dxzell.trackerbeam;

import com.dxzell.trackerbeam.beaconmechanic.DestinationListener;
import com.dxzell.trackerbeam.beaconmechanic.TrackerCommand;
import com.dxzell.trackerbeam.beaconmechanic.UntrackCommand;
import com.dxzell.trackerbeam.settings.SettingsCommand;
import com.dxzell.trackerbeam.settings.beaconsettings.BeaconSettingsListener;
import com.dxzell.trackerbeam.settings.colorsettings.ColorSettingsListener;
import com.dxzell.trackerbeam.settings.distancesettings.DistanceSettingsListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TrackerBeam extends JavaPlugin {

    private static TrackerBeam main;
    @Override
    public void onEnable() {
        main = this;

        // Commands
        getCommand("beacon").setExecutor(new SettingsCommand());
        getCommand("beacon").setTabCompleter(new SettingsCommand());
        getCommand("track").setExecutor(new TrackerCommand());
        getCommand("untrack").setExecutor(new UntrackCommand());

        // Events
        Bukkit.getPluginManager().registerEvents(new BeaconSettingsListener(), this);
        Bukkit.getPluginManager().registerEvents(new ColorSettingsListener(), this);
        Bukkit.getPluginManager().registerEvents(new DistanceSettingsListener(), this);
        Bukkit.getPluginManager().registerEvents(new DestinationListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static TrackerBeam getMainInstance() {
        return main;
    }
}
