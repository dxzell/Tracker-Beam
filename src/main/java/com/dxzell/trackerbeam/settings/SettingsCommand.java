package com.dxzell.trackerbeam.settings;

import com.dxzell.trackerbeam.settings.beaconsettings.BeaconSettings;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("beacon")) {
                if (args.length == 1 && args[0].equalsIgnoreCase("settings")) {
                    BeaconSettings.openSettings(player);
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if(args.length == 1)
        {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList(new String[] {"settings"}), new ArrayList<>());
        }
        return list;
    }
}
