package me.giverplay.rpg.commands;

import me.giverplay.rpg.RPG;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CreateRecipeCommand implements CommandExecutor {

  private final RPG plugin;

  public CreateRecipeCommand(RPG plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if(!(sender instanceof Player player)) {
      sender.sendMessage(ChatColor.RED + "Somento jogadores podem usar este comando.");
      return true;
    }

    if(!player.hasPermission("rpg.createrecipe")) {
      player.sendMessage(ChatColor.RED + "Sem permissão!");
      return true;
    }

    if(args.length == 0) {
      player.sendMessage(ChatColor.GREEN + "Opções: furnace");
      return true;
    }

    if(args[0].equalsIgnoreCase("furnace")) {
      if(args.length < 4) {
        player.sendMessage(ChatColor.RED + "Uso correto: /createrecipe furnace <nome> <item> <tempo> <xp>");
        return true;
      }

      ItemStack result = player.getInventory().getItemInMainHand();

      if(result.getType() == Material.AIR) {
        player.sendMessage(ChatColor.RED + "Segure o item da receita não mão!");
        return true;
      }

      if(args[1].length() == 0) {
        player.sendMessage(ChatColor.RED + "O nome da receita é inválido!");
        return true;
      }

      Material source;

      try {
        source = Material.getMaterial(args[2]);
      } catch (IllegalArgumentException e) {
        player.sendMessage(ChatColor.RED + "Item desconhecido...");
        return true;
      }

      int time;

      try {
        time = Math.abs(Integer.parseInt(args[3]));
      } catch (NumberFormatException e) {
        player.sendMessage(ChatColor.RED + "O tempo deve ser um número inteiro...");
        return true;
      }

      float exp;

      try {
        exp = Math.abs(Float.parseFloat(args[4]));
      } catch (NumberFormatException e) {
        player.sendMessage(ChatColor.RED + "O xp deve ser um número inteiro...");
        return true;
      }

      plugin.getRecipeManager().registerRecipe(args[0], source, result, exp, time);
      player.sendMessage(ChatColor.GREEN + "Receita criada com sucesso!");
      return true;
    }

    player.sendMessage(ChatColor.RED + "Ferramenta desconhecida...");
    return true;
  }
}
