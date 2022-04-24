package kr.kro.minestar.utility.item

import kr.kro.minestar.utility.material.item
import me.arcaniax.hdb.api.HeadDatabaseAPI
import org.bukkit.Material

object Head {
    fun get(id: Int) = HeadDatabaseAPI().getItemHead("$id") ?: Material.BARRIER.item().display("§c해당 ID의 머리가 없습니다")
    fun get(id: Int, material: Material) = HeadDatabaseAPI().getItemHead("$id") ?: material.item()
}