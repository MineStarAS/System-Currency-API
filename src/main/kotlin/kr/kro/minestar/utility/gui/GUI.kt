package kr.kro.minestar.utility.gui

import kr.kro.minestar.utility.event.disable
import kr.kro.minestar.utility.event.enable
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.java.JavaPlugin

abstract class GUI(protected val player: Player) : Listener {
    protected abstract val pl: JavaPlugin
    protected abstract val gui: Inventory

    protected open fun displaying() {}

    fun openGUI() {
        enable(pl)
        displaying()
        player.openInventory(gui)
    }

    @EventHandler
    protected abstract fun clickGUI(e: InventoryClickEvent)

    protected fun isSameGUI(e: InventoryClickEvent): Boolean {
        if (e.whoClicked != player) return false
        if (e.inventory != gui) return false
        return true
    }

    @EventHandler
    protected open fun closeGUI(e: InventoryCloseEvent) {
        if (e.player != player) return
        if (e.inventory != gui) return
        disable()
    }
}