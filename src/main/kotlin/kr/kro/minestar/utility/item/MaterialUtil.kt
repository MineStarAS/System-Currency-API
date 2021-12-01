package kr.kro.minestar.utility.item

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

fun Material.item(): ItemStack {
    return ItemStack(this)
}