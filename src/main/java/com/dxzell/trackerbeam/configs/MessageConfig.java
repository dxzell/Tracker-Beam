package com.dxzell.trackerbeam.configs;

import com.dxzell.trackerbeam.TrackerBeam;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageConfig {

    public static MessageConfig instance = new MessageConfig();
    private File file;
    private YamlConfiguration config;

    private MessageConfig() {
        load();
    }

    // Track Command Messages

    public String getWrongTrack() {
        return config.getString("messages.track-command.wrong-track");
    }

    public String getNotNumbers() {
        return config.getString("messages.track-command.not-numbers");
    }

    public String getSuccessfullyTracking(int x, int z) {
        return config.getString("messages.track-command.successfully-tracking").replace("[X]", x + "").replace("[Z]", z + "");
    }

    public String getReachedLocation() {
        return config.getString("messages.track-command.reached-location");
    }


    // Untrack Command Messages

    public String getUntrack() {
        return config.getString("messages.untrack-command.successfully-untracked");
    }

    public String getNoCurrentTrack() {
        return config.getString("messages.untrack-command.not-tracking");
    }


    public void load() {
        file = new File(TrackerBeam.getMainInstance().getDataFolder(), "messages.yml");

        if (!file.exists()) {
            TrackerBeam.getMainInstance().saveResource("messages.yml", true);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true); // So it does not nuke the config after changes

        try {
            config.load(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void set(String path, Object value) {
        config.set(path, value);
        save();
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static MessageConfig getInstance() {
        return instance;
    }
}
