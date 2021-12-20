package kr.kro.minestar.utility.time

import kr.kro.minestar.utility.number.addComma

class ElapsedTime {
    val startTime: Long = System.currentTimeMillis()

    fun toLong(): Long = System.currentTimeMillis() - startTime

    fun toMilliSecond(): String = (System.currentTimeMillis() - startTime).addComma() + "ms"

    fun toSecond(): String {
        val t = System.currentTimeMillis() - startTime
        val milli = t % 1000
        val s = t / 1000
        return "$s.${milli}s"
    }
}