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