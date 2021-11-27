package me.giverplay.rpg

import me.giverplay.rpg.commands.CreateRecipeCommand
import me.giverplay.rpg.commands.RpgCommand
import me.giverplay.rpg.managers.RecipeManager
import org.bukkit.plugin.java.JavaPlugin

class RPG : JavaPlugin() {
  val recipeManager = RecipeManager(this)

  override fun onEnable() {
    getCommand("rpg")?.setExecutor(RpgCommand(this))
    getCommand("createrecipe")?.setExecutor(CreateRecipeCommand(this))
    reload()
  }

  fun reload() {
    saveDefaultConfig()
    reloadConfig()

    recipeManager.loadRecipes()
  }
}