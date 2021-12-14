package kr.kro.minestar.utility.block

import org.bukkit.Material
import org.bukkit.block.Block

fun Block.isCrop(): Boolean {
    return when (type) {
        Material.WHEAT,
        Material.CARROTS,
        Material.POTATOES,
        Material.BEETROOTS,
        Material.MELON,
        Material.MELON_STEM,
        Material.PUMPKIN,
        Material.PUMPKIN_STEM,
        Material.COCOA,
        Material.SUGAR_CANE,
        Material.SWEET_BERRY_BUSH -> true
        else -> false
    }
}

fun Block.isOre(): Boolean {
    if (type.toString().contains("ORE")) return true
    return false
}

fun Block.isLog(): Boolean {
    if (type.toString().contains("LOG")) return true
    return false
}
