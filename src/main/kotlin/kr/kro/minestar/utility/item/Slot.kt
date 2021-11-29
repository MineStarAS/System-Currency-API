package kr.kro.minestar.utility.item

import org.bukkit.inventory.ItemStack

class Slot(private val line: Int, private val number: Int, val item: ItemStack) {
    val get = line * 9 + number
}