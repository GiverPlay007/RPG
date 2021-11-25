package me.giverplay.rpg.utils;

import org.bukkit.ChatColor;

public final class ChatUtils {
  private ChatUtils() { }

  public static String translateColors(String source) {
    return ChatColor.translateAlternateColorCodes('&', source);
  }
}
