package kr.kro.minestar.currency.data

import kr.kro.minestar.utility.collection.toStringList
import kr.kro.minestar.utility.material.item
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class Currency {

    companion object {
        private val set = hashSetOf<Currency>()

        fun getCurrency(unit: String?): Currency? = null

        fun contains(unit: String?): Boolean = false

        fun currencySet() = set.toSet()
        fun currencyUnitList() = set.toStringList()
    }

    //화폐 단위
    val unit = ""

    //화폐 아이콘
    fun icon():ItemStack = Material.GOLD_INGOT.item()

    //송금
    fun canSend() = false

    //지불
    fun canPay() = false
}