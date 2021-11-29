package kr.kro.minestar.utility.inventory

import kr.kro.minestar.utility.item.isSameItem
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

fun Inventory.getEmptySlot(): Int {
    val size = if (this is PlayerInventory) return 9 * 4
    else this.size
    var empty = 0
    for (slot in 0 until size) if (getItem(slot) == null) ++empty
    return empty
}

fun Inventory.hasSameItem(item: ItemStack): Boolean {
    for (slot in this) {
        if (slot == null) continue
        if (slot.isSameItem(item)) return true
    }
    return false
}

fun Inventory.getNotFullStackItemSlot(item: ItemStack): List<Int> {
    val list = mutableListOf<Int>()
    if (item.maxStackSize <= 1) return list
    if (!this.hasSameItem(item)) return list
    for (slot in 0 until this.size) {
        val i = this.getItem(slot) ?: continue
        if (!i.isSameItem(item)) continue
        if (i.maxStackSize == i.amount) continue
        list.add(slot)
    }
    return list
}

fun Inventory.howMuchToAdd(item: ItemStack): Int {
    val maxStack = item.maxStackSize
    var empty = maxStack * this.getEmptySlot()
    if (maxStack == 1) return empty

}