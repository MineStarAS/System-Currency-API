package kr.kro.minestar.utility.gui

import kr.kro.minestar.utility.event.disable
import kr.kro.minestar.utility.event.enable
import kr.kro.minestar.utility.item.Slot
import kr.kro.minestar.utility.item.isSameItem
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

abstract class GUI : Listener {
    abstract val player: Player
    protected abstract val plugin: JavaPlugin
    protected abstract val gui: Inventory

    protected open fun displaying() {}

    fun openGUI() {
        enable(plugin)
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

    /**
     * [Slot] 기능
     */
    protected fun setItem(slot: Slot) = gui.setItem(slot.getIndex(), slot.item)

    protected fun setItems(slots: Array<out Slot>) {
        for (slot in slots) setItem(slot)
    }

    protected fun getSlot(item: ItemStack, slots: Array<out Slot>): Slot? {
        for (slot in slots) if (slot.item.isSameItem(item)) return slot
        return null
    }
}