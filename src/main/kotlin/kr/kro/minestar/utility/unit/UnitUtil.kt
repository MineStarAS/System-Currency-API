package kr.kro.minestar.utility.unit

class UnitUtil {
    fun <E, T> E.set(any: T) = any
    fun <E> E.setTrue() = true
    fun <E> E.setFalse() = false
}