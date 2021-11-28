package kr.kro.minestar.utility

import java.text.DecimalFormat

fun String.remove(char: Char): String {
    return this.replace(char.toString(), "")
}

fun String.remove(string: String): String {
    return this.replace(string, "")
}

fun Int.addComma(): String {
    return DecimalFormat("###,###").format(this)
}

fun Long.addComma(): String {
    return DecimalFormat("###,###").format(this)
}