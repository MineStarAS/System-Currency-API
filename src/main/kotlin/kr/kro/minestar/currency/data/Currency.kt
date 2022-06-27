package kr.kro.minestar.currency.data

import kr.kro.minestar.utility.collection.toStringList

class Currency {

    companion object {
        private val set = hashSetOf<Currency>()

        fun getCurrency(unit: String?): Currency? = null

        fun contains(unit: String?): Boolean = false

        fun currencySet() = set.toSet()
        fun currencyUnitList() = set.toStringList()
    }
}