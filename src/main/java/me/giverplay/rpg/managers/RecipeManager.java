package me.giverplay.rpg.managers;

import me.giverplay.rpg.RPG;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RecipeManager {
  private final Map<String, Recipe> recipes = new HashMap<>();
  private final RPG plugin;

  public RecipeManager(RPG plugin) {
    this.plugin = plugin;

    ItemStack item = new ItemStack(Material.PAPER, 1);
    ItemMeta meta = item.getItemMeta();

    meta.setDisplayName(ChatColor.GREEN + "Dinheiro");
    meta.setLore(Arrays.asList(" ", ChatColor.YELLOW + "Valor: " + ChatColor.WHITE + "$10.00", " "));
    item.setItemMeta(meta);

    Recipe money = new FurnaceRecipe(new NamespacedKey(plugin, "money"), item, Material.DIAMOND, 25.0f, 30);
    recipes.put("money", money);
    plugin.getServer().addRecipe(money);
  }


}
