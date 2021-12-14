package kr.kro.minestar.utility.yaml

import org.bukkit.configuration.file.YamlConfiguration

fun YamlConfiguration.clear() {
    val keys = getKeys(false)
    for (key in keys) this[key] = null
}