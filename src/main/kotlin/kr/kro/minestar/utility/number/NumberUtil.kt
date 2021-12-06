package kr.kro.minestar.utility.number

import java.text.DecimalFormat

/**
 * Int에 3자리 수 마다 반점을 추가한 후 String으로 출력합니다.
 */
fun Int.addComma(): String {
    return DecimalFormat("###,###").format(this)
}

/**
 * Long에 3자리 수 마다 반점을 추가한 후 String으로 출력합니다.
 */
fun Long.addComma(): String {
    return DecimalFormat("###,###").format(this)
}

/**
 * Int의 1당 1초로 계산한 후 틱으로 환산합니다.
 */
fun Int.toTick(): Int {
    return this * 20
}

/**
 * Int의 1당 1초로 계산하여 시간을 분 단위까지 나타냅니다.
 */
fun Int.toTime(): String {
    val min = this / 60
    val sec = this - (min * 60)

    return if (sec < 10) "$min : 0$sec"
    else "$min : $sec"
}