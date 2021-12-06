package kr.kro.minestar.utility.item

import org.bukkit.inventory.ItemStack

/**
 * 인벤토리의 setItem 함수를 편하게 사용하기 위해 만든 클래스 입니다.
 * *아래 내용은 기본적인 사용법 예시 입니다.
 *
 * val slot = listof(
 *
 *     Slot(0, 0, Metarial.STONE.item()),
 *     Slot(0, 1, Metarial.COBBLESTONE.item()),
 *     Slot(0, 2, Metarial.DEEPSLATE.item()),
 *     Slot(0, 3, Metarial.COBBLED_DEEPSLATE.item()),
 *
 *     Slot(1, 0, Metarial.RAW_COPPER.item()),
 *     Slot(1, 1, Metarial.RAW_IRON.item()),
 *     Slot(1, 2, Metarial.RAW_GOLD.item()),
 *     )
 * val gui = Bukkit.createInventory(null, 9 * 2, "예제 인벤토리").also {
 * for (slot in slots) it.setItem(slot.get, slot.item)
 * }
 */
class Slot(line: Int, number: Int, val item: ItemStack) {
    val get = line * 9 + number
}