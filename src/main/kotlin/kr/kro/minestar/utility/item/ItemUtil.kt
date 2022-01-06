package kr.kro.minestar.utility.item

import kr.kro.minestar.utility.material.item
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * 아이템의 이름을 가져옵니다. 이름이 없을 경우 타입값을 가져옵니다.
 */
fun ItemStack.display(): String {
    val meta = this.itemMeta
    if (meta.hasDisplayName()) return meta.displayName
    return type.name
}

/**
 * 아이템의 이름을 설정합니다.
 */
fun ItemStack.display(display: String): ItemStack {
    val meta = this.itemMeta
    meta.setDisplayName("§f$display")
    this.itemMeta = meta
    return this
}

/**
 * 아이템의 이름을 삭제합니다.
 */
fun ItemStack.clearDisplay(): ItemStack {
    val meta = this.itemMeta
    meta.setDisplayName("")
    this.itemMeta = meta
    return this
}

/**
 * 아이템의 이름에 접두사를 추가합니다.
 */
fun ItemStack.addPrefix(prefix: String): ItemStack {
    val meta = this.itemMeta
    val display = meta.displayName
    if (meta.hasDisplayName()) meta.setDisplayName("§f$prefix $display")
    else meta.setDisplayName(prefix)
    this.itemMeta = meta
    return this
}

/**
 * 아이템의 이름에 접미사를 추가합니다.
 */
fun ItemStack.addSuffix(suffix: String): ItemStack {
    val meta = this.itemMeta
    val display = meta.displayName
    if (meta.hasDisplayName()) meta.setDisplayName("$display $suffix")
    else meta.setDisplayName(suffix)
    this.itemMeta = meta
    return this
}

/**
 * 아이템의 로어를 추가 합니다.
 */
fun ItemStack.addLore(string: String): ItemStack {
    val meta = this.itemMeta
    val lore = meta.lore ?: mutableListOf()
    lore.add("§f$string")
    meta.lore = lore
    this.itemMeta = meta
    return this
}

/**
 * 아이템의 로어를 모두 삭제합니다.
 */
fun ItemStack.clearLore(): ItemStack {
    val meta = this.itemMeta
    meta.lore = listOf()
    this.itemMeta = meta
    return this
}

/**
 * 아이템의 갯수를 설정합니다.
 */
fun ItemStack.amount(int: Int): ItemStack {
    this.amount = int
    return this
}

/**
 * 아이템에 모든 아이템 플래그를 추가 합니다.
 */
fun ItemStack.flagAll():ItemStack {
    for (flag in ItemFlag.values()) addItemFlags(flag)
    return this
}

/**
 * 아이템에 있는 모든 아이템 플래그를 제거 합니다.
 */
fun ItemStack.flagClear():ItemStack {
    for (flag in ItemFlag.values()) removeItemFlags(flag)
    return this
}

/**
 * 아이템이 입력아이템과 같은 아이템인지 구분합니다.
 */
fun ItemStack.isSameItem(item: ItemStack): Boolean {
    return this.clone().amount(1) == item.clone().amount(1)
}

/**
 * 아이템이 자연적인 아이템인지 구분합니다.
 */
fun ItemStack.isDefaultItem(): Boolean {
    return this.isSameItem(this.type.item())
}