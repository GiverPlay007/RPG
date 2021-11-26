package me.giverplay.rpg.commands;

import me.giverplay.rpg.RPG;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class RpgCommand implements CommandExecutor {

  private final RPG plugin;

  public RpgCommand(RPG plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if(args.length == 0) {
      sender.sendMessage(ChatColor.GREEN + plugin.getDescription().getFullName());
      return true;
    }

    if(args[0].equalsIgnoreCase("reload")) {
      if(sender.hasPermission("rpg.reload")) {
        plugin.reload();
        plugin.getLogger().info("Plugin reloaded!");
        sender.sendMessage(ChatColor.GREEN + "Plugin recarregado com sucesso!");
        return true;
      }

      sender.sendMessage(ChatColor.RED + "Sem permissão!");
      return true;
    }

    return true;
  }
}
