package me.giverplay.rpg.managers;

import me.giverplay.rpg.RPG;
import me.giverplay.rpg.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static me.giverplay.rpg.utils.ChatUtils.translateColors;

public class RecipeManager {
  private final Map<NamespacedKey, Recipe> recipes = new HashMap<>();
  private final YamlConfiguration config = new YamlConfiguration();
  private final RPG plugin;

  public RecipeManager(RPG plugin) {
    this.plugin = plugin;
  }

  public void loadRecipes() {
    recipes.keySet().forEach(plugin.getServer()::removeRecipe);
    recipes.clear();

    try {
      config.load(new File(plugin.getDataFolder(), "recipes.yml"));
    } catch (IOException | InvalidConfigurationException e) {
      plugin.getLog4JLogger().error("Failed to load recipes.", e);
      return;
    }

    ConfigurationSection furnace = config.getConfigurationSection("Furnace");

    for(String key : furnace.getKeys(false)) {
      ConfigurationSection recipeSection = furnace.getConfigurationSection(key);

      String displayName = translateColors(recipeSection.getString("DisplayName"));
      List<String> lore = recipeSection
        .getStringList("Lore")
        .stream()
        .map(ChatUtils::translateColors)
        .collect(Collectors.toList());

      float exp = (float) recipeSection.getDouble("Exp");
      int time = recipeSection.getInt("Time");

      Material source = Material.getMaterial(config.getString("Source"));
      Material result = Material.getMaterial(config.getString("Result"));
      ItemStack resultItem = new ItemStack(result);
      ItemMeta meta = resultItem.getItemMeta();
      meta.setDisplayName(displayName);
      meta.setLore(lore);
      resultItem.setItemMeta(meta);

      registerRecipe(key, source, resultItem, exp, time);
      plugin.getLogger().fine("Loaded furnace recipe %s → %s (%s)".formatted(source.name(), result.name(), key));
    }
  }

  public void registerRecipe(String name, Material source, ItemStack result, float exp, int time) {
    NamespacedKey namespace = new NamespacedKey(plugin, name);
    FurnaceRecipe recipe = new FurnaceRecipe(namespace, result, source, exp, time);
    plugin.getServer().addRecipe(recipe);
    recipes.put(namespace, recipe);
  }
}
