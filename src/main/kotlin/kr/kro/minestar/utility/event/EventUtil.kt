package kr.kro.minestar.utility.event

import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

/**
 * Listener 클래스를 활성화 합니다.
 */
fun Listener.enable(plugin : JavaPlugin) = Bukkit.getPluginManager().registerEvents(this, plugin)

/**
 * Listener 클래스를 비활성화 합니다.
 */
fun Listener.disable() = HandlerList.unregisterAll(this)
