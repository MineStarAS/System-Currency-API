package kr.kro.minestar.utility.item

import kr.kro.minestar.utility.material.item
import me.arcaniax.hdb.api.HeadDatabaseAPI
import org.bukkit.Material

object Head {
    private val hdb = HeadDatabaseAPI()
    fun head(id: Int) = hdb.getItemHead("$id") ?: Material.BARRIER.item().display("§c해당 ID의 머리가 없습니다")
    fun head(id: Int, material: Material) = hdb.getItemHead("$id") ?: material.item()
}