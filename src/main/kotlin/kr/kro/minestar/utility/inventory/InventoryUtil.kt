package kr.kro.minestar.utility.inventory

import kr.kro.minestar.utility.item.isSameItem
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

/**
 * 인벤토리의 빈 슬롯 갯수를 집계합니다.
 */
fun Inventory.getEmptySlot(): Int {
    val size = if (this is PlayerInventory) 9 * 4
    else this.size
    var empty = 0
    for (slot in 0 until size) if (getItem(slot) == null) ++empty
    return empty
}

/**
 * 인벤토리에서 입력아이템과 같은 아이템의 갯수를 집계합니다.
 */
fun Inventory.hasSameItem(item: ItemStack): Boolean {
    for (slot in this) {
        if (slot == null) continue
        if (slot.isSameItem(item)) return true
    }
    return false
}

/**
 * 인벤토리에서 입력아이템과 같은 아이템이 있는 슬롯 중 최대 중첩 상태가 아닌 슬롯을 집계합니다.
 */
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

/**
 * 인벤토리에서 입력아이템이 얼마나 들어갈 수 있는지 계산합니다.
 */
fun Inventory.howManyToAdd(item: ItemStack): Int {
    val maxStack = item.maxStackSize
    var empty = maxStack * this.getEmptySlot()
    if (maxStack == 1) return empty
    val list = this.getNotFullStackItemSlot(item)
    if (list.isEmpty()) return empty
    for (slot in list) {
        val i = this.getItem(slot) ?: continue
        if (i.amount == maxStack) continue
        empty += maxStack - i.amount
    }
    return empty
}

/**
 * 인벤토리에서 입력아이템을 얼마나 가지고 있는지 집계합니다.
 */
fun Inventory.howManyHasSameItem(item: ItemStack): Int {
    var has = 0
    for (i in this) {
        i?: continue
        if (i.isSameItem(item)) has += i.amount
    }
    return has
}

object InventoryUtil {
    /**
     * 인벤토리를 생성합니다.
     */
    fun gui(line: Int, title: String): Inventory {
        if (line <= 0) return Bukkit.createInventory(null, 9, title)
        if (line > 6) return Bukkit.createInventory(null, 9 * 6, title)
        return Bukkit.createInventory(null, 9 * line, title)
    }
}