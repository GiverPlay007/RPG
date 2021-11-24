package me.giverplay.rpg

import me.giverplay.rpg.managers.RecipeManager
import org.bukkit.plugin.java.JavaPlugin

class RPG : JavaPlugin() {
  override fun onEnable() {
    logger.info("Sup World")
    RecipeManager(this)
  }
}