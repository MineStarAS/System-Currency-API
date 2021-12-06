package kr.kro.minestar.utility.item

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Material 클래스에서 new ItemStack 없이 아이템을 생성합니다.
 */
fun Material.item(): ItemStack {
    return ItemStack(this)
}