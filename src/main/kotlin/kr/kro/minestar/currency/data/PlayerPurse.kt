package kr.kro.minestar.currency.data

import kr.kro.minestar.utility.bool.BooleanScript
import kr.kro.minestar.utility.bool.addScript
import org.bukkit.entity.Player

class PlayerPurse(val player: Player) {
    companion object {
        private val map = hashMapOf<Player, PlayerPurse>()

        fun getPlayerPurse(player: Player) = map[player]

        fun contains(player: Player) = map.containsKey(player)

    }

    //보유 금액
    fun currencyAmount(currency: Currency): Long = 0

    //보유 금액 설정
    fun currencyAmountSet(currency: Currency, setAmount: Long, cause: String): BooleanScript = false.addScript()

    //보유 금액 추가
    fun currencyAmountAdd(currency: Currency, addAmount: Long, cause: String): BooleanScript = false.addScript()

    //보유 금액 감가
    fun currencyAmountRemove(currency: Currency, removeAmount: Long, cause: String): BooleanScript = false.addScript()

    //지불
    fun currencyAmountPay(currency: Currency, payAmount: Long, cause: String): BooleanScript = false.addScript()

    //수익
    fun currencyAmountEarn(currency: Currency, earnAmount: Long, cause: String): BooleanScript = false.addScript()

    //송금
    fun currencyAmountSend(currency: Currency, sendAmount: Long, targetPlayer: Player, cause: String): BooleanScript = false.addScript()
}