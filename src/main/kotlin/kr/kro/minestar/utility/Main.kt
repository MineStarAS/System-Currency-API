package kr.kro.minestar.utility

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        lateinit var pl: Main
        const val prefix = "§f[§dUtil§f]"
    }

    override fun onEnable() {
        pl = this
        logger.info("$prefix §eThis plugin is for testing purposes only.")
        getCommand("util")?.setExecutor(CMD)
    }

    override fun onDisable() {
    }
}