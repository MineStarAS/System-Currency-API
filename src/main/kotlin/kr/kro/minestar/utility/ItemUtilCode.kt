package kr.kro.minestar.utility

import org.bukkit.inventory.ItemStack

fun ItemStack.setDisplay(display: String): ItemStack {
    val meta = this.itemMeta
    meta.setDisplayName("§f$display")
    this.itemMeta = meta
    return this
}

fun ItemStack.clearDisplay(): ItemStack {
    val meta = this.itemMeta
    meta.setDisplayName("")
    this.itemMeta = meta
    return this
}

fun ItemStack.addPrefix(prefix: String): ItemStack {
    val meta = this.itemMeta
    val display = meta.displayName
    if (meta.hasDisplayName()) meta.setDisplayName("§f$prefix $display")
    else meta.setDisplayName(prefix)
    this.itemMeta = meta
    return this
}

fun ItemStack.addSuffix(suffix: String): ItemStack {
    val meta = this.itemMeta
    val display = meta.displayName
    if (meta.hasDisplayName()) meta.setDisplayName("$display $suffix")
    else meta.setDisplayName(suffix)
    this.itemMeta = meta
    return this
}

fun ItemStack.addLore(string: String): ItemStack {
    val meta = this.itemMeta
    val lore = meta.lore ?: mutableListOf()
    lore.add("§f$string")
    meta.lore = lore
    this.itemMeta = meta
    return this
}

fun ItemStack.clearLore(): ItemStack {
    val meta = this.itemMeta
    meta.lore = listOf()
    this.itemMeta = meta
    return this
}

fun ItemStack.amount(int: Int): ItemStack {
    if (int <= 0) this.amount = 1.also { return this }
    if (int > 64) this.amount = 64.also { return this }
    this.amount = int
    return this
}