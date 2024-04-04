package com.dxzell.trackerbeam.configs;

import com.dxzell.trackerbeam.TrackerBeam;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

// Singleton Pattern
public class BeaconConfig {

    private final static BeaconConfig instance = new BeaconConfig();

    private File file;
    private YamlConfiguration config;

    private BeaconConfig() {
        load();
    }

    // Distance
    public int getDistance() {
        return config.getInt("beacon.beacon-distance");
    }

    public void lowerDistance() {
        if (getDistance() > 10) {
            config.set("beacon.beacon-distance", getDistance() - 1);
            save();
        }
    }

    public void higherDistance() {
        if (getDistance() < 50) {
            config.set("beacon.beacon-distance", getDistance() + 1);
            save();
        }
    }

    // Color
    public Material getColorMaterial() {
        return Material.valueOf(config.getString("beacon.color-material"));
    }

    public void setColorMaterial(String material) {
        set("beacon.color-material", material);
    }


    public void set(String path, Object value) {
        config.set(path, value);
        save();
    }

    // Meter count
    public boolean getMeterCount() {
        return config.getBoolean("beacon.meter-count");
    }

    public void toggleMeterCount() {
        config.set("beacon.meter-count", !getMeterCount());
        save();
    }


    public void load() {
        file = new File(TrackerBeam.getMainInstance().getDataFolder(), "settings.yml");

        if (!file.exists()) {
            TrackerBeam.getMainInstance().saveResource("settings.yml", true);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true); // So it does not nuke the config after changes

        try {
            config.load(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BeaconConfig getInstance() {
        return instance;
    }
}
