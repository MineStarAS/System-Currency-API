package kr.kro.minestar.utility

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.text.DecimalFormat

fun String.toConsole() {
    print(this)
}

fun String.toServer() {
    Bukkit.broadcastMessage(this)
}

fun String.toPlayer(player: Player) {
    player.sendMessage(this)
}

fun String.toPlayers(players: Collection<Player>) {
    for (player in players) player.sendMessage(this)
}

fun String.toComponent(): Component {
    return Component.text(this)
}

fun String.setUnderBar(): String {
    return this.replace(' ', '_')
}

fun String.removeUnderBar(): String {
    return this.replace('_', ' ')
}

fun String.remove(char: Char): String {
    return this.replace(char.toString(), "")
}

fun String.remove(string: String): String {
    return this.replace(string, "")
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

class UtilCode {
    companion object {
        fun addComma(int: Int): String {
            val df = DecimalFormat("###,###")
            return df.format(int)
        }

        fun addComma(long: Long): String {
            val df = DecimalFormat("###,###")
            return df.format(long)
        }
    }
}