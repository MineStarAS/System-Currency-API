package kr.kro.minestar.utility.unit

class UnitUtil {
    fun  <T> Unit.set(any: T) = any
    fun  Unit.setTrue() = true
    fun  Unit.setFalse() = false
}