package me.giverplay.rpg

import me.giverplay.rpg.managers.RecipeManager
import org.bukkit.plugin.java.JavaPlugin

class RPG : JavaPlugin() {
  val recipeManager = RecipeManager(this)

  override fun onEnable() {
    logger.info("Sup World")
    reload()
  }

  fun reload() {
    saveDefaultConfig()
    reloadConfig()

    recipeManager.loadRecipes()
  }
}