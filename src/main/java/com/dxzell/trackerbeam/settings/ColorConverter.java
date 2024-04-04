package com.dxzell.trackerbeam.settings;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

public abstract class ColorConverter {

    public static ChatColor getColorByMaterial(Material mat) {
        String matName = mat.toString();
        return switch (matName) {
            case "WHITE_STAINED_GLASS" -> ChatColor.WHITE;
            case "ORANGE_STAINED_GLASS" -> ChatColor.of("#FD9F1A");
            case "MAGENTA_STAINED_GLASS" -> ChatColor.of("#CC00CC");
            case "LIGHT_BLUE_STAINED_GLASS" -> ChatColor.of("#ADD8E6");
            case "YELLOW_STAINED_GLASS" -> ChatColor.of("#FFE135");
            case "LIME_STAINED_GLASS" -> ChatColor.of("#9EFD38");
            case "PINK_STAINED_GLASS" -> ChatColor.of("#F19CBB");
            case "GRAY_STAINED_GLASS" -> ChatColor.of("#696969");
            case "LIGHT_GRAY_STAINED_GLASS" -> ChatColor.of("#D3D3D3");
            case "CYAN_STAINED_GLASS" -> ChatColor.of("#4E82B4");
            case "PURPLE_STAINED_GLASS" -> ChatColor.of("#A020F0");
            case "BLUE_STAINED_GLASS" -> ChatColor.of("#0018A8");
            case "BROWN_STAINED_GLASS" -> ChatColor.of("#6B4423");
            case "GREEN_STAINED_GLASS" -> ChatColor.of("#8DB600");
            case "RED_STAINED_GLASS" -> ChatColor.of("#D3212D");
            case "BLACK_STAINED_GLASS" -> ChatColor.of("#363136");
            default -> ChatColor.YELLOW;
        };
    }
}
