package kr.kro.minestar.utility.item

import kr.kro.minestar.utility.file.child
import kr.kro.minestar.utility.material.item
import me.arcaniax.hdb.api.HeadDatabaseAPI
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.plugin.java.JavaPlugin

class Head(plugin: JavaPlugin) {
    private val headFile = plugin.dataFolder.child("headItem.yml")
    private fun yaml() = YamlConfiguration.loadConfiguration(headFile)

    private val nullItem = Material.BARRIER.item().display("§c해당 ID의 머리가 없습니다")

    fun item(id: Int) :ItemStack {
        return try {
            headDataBaseItem(id) ?: nullItem
        } catch (_:Exception) {
            yaml().getItemStack("$id")?: nullItem
        }
    }

    fun item(id: Int, material: Material) = headDataBaseItem(id) ?: material.item()

    fun item(player: Player): ItemStack {
        val item = Material.PLAYER_HEAD.item()

        val meta = item.itemMeta as SkullMeta
        meta.owningPlayer = player
        item.itemMeta = meta

        return item
    }

    private fun headDataBaseItem(id: Int): ItemStack? {
        val clazz = Class.forName("me.arcaniax.hdb.api.HeadDatabaseAPI")
        if (clazz != null) {
            val item = HeadDatabaseAPI().getItemHead("$id") ?: return yaml().getItemStack("$id")
            item.clearDisplay()
            val yaml = yaml()
            yaml["$id"] = item
            yaml.save(headFile)
            return item
        }

        return yaml().getItemStack("$id")
    }
}