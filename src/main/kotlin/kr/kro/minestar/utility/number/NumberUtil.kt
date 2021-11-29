package kr.kro.minestar.utility.number

import java.text.DecimalFormat

fun Int.addComma(): String {
    return DecimalFormat("###,###").format(this)
}

fun Long.addComma(): String {
    return DecimalFormat("###,###").format(this)
}

fun Int.toTick(): Int {
    return this * 20
}

fun Int.toTime(): String {
    val min = this / 60
    val sec = this - (min * 60)

    return if (sec < 10) "$min : 0$sec"
    else "$min : $sec"
}