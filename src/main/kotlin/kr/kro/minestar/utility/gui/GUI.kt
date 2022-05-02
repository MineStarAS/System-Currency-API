package kr.kro.minestar.utility.gui

import kr.kro.minestar.utility.event.disable
import kr.kro.minestar.utility.event.enable
import kr.kro.minestar.utility.item.Slot
import kr.kro.minestar.utility.item.SlotKey
import kr.kro.minestar.utility.item.isSameItem
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

abstract class GUI(protected val player: Player) : Listener {
    protected abstract val pl: JavaPlugin
    protected abstract val gui: Inventory

    protected abstract fun slots(): Map<out SlotKey, Slot>

    protected fun getSlotKey(item: ItemStack, checkAmount: Boolean): SlotKey? {
        for ((key, slot) in slots()) {
            if (!slot.item.isSameItem(item)) continue
            if (checkAmount) if (item.amount != slot.item.amount) continue
            return key
        }
        return null
    }

    protected fun displaying() {
        gui.clear()
        for (slot in slots().values) gui.setItem(slot.get, slot.item)
    }

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
    protected fun closeGUI(e: InventoryCloseEvent) {
        if (e.player != player) return
        if (e.inventory != gui) return
        disable()
    }
}