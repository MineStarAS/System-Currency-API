package kr.kro.minestar.utility.item

import org.bukkit.inventory.ItemStack

/**
 *   사용 예시
 *
 *    enum class TestSlot(override val line: Int,
 *    override val number: Int, override val item: ItemStack): Slot {
 *
 *    T1(0, 0, Material.STONE.item()),
 *    T2(0, 1, Material.STONE.item()),
 *    T3(0, 2, Material.STONE.item()),
 *    ;
 *    }
 *
 *
 *
 *
 *
 */
interface Slot {
    val item: ItemStack

    val line: Int
    val number: Int

    fun getIndex() = line * 9 + number
}