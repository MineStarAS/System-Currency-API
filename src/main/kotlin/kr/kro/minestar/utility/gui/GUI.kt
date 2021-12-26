package kr.kro.minestar.utility.gui

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.java.JavaPlugin

interface GUI :Listener {
    val pl : JavaPlugin
    val player : Player
    val gui : Inventory


    fun openGUI () {
        Bukkit.getPluginManager().registerEvents(this, pl)
        displaying()
        player.openInventory(gui)
    }

    @EventHandler
    fun clickGUI (e: InventoryClickEvent) {}

    @EventHandler
    fun closeGUI (e: InventoryCloseEvent) {
        if (e.player != player) return
        if (e.inventory != gui) return
        HandlerList.unregisterAll(this)
    }

    fun displaying () {}
}