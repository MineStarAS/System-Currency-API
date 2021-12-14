package kr.kro.minestar.utility.string

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * Stringd을 콘솔에만 출력합니다.
 */
fun String.toConsole() {
    println(this)
}

/**
 * String을 콘솔 및 서버 내 플레이어들에게 출력합니다.
 */
fun String.toServer() {
    Bukkit.broadcastMessage(this)
}

/**
 * String을 입력된 플레이어에게만 출력합니다.
 */
fun String.toPlayer(player: Player) {
    player.sendMessage(this)
}

/**
 * String을 입력된 플레이어들에게만 출력합니다.
 */
fun String.toPlayers(players: Collection<Player>) {
    for (player in players) player.sendMessage(this)
}

/**
 * String을 입력된 플레이어의 액션바에 출력합니다.
 */
fun String.toPlayerActionBar(player: Player) {
    player.sendActionBar(this)
}

/**
 * String을 입력된 플레이어들의 액션바에 출력합니다.
 */
fun String.toPlayersActionBar(players: Collection<Player>) {
    for (player in players) player.sendActionBar(this)
}

/**
 * String을 Component로 변환합니다.
 */
fun String.toComponent(): Component {
    return Component.text(this)
}

/**
 * String에 있는 공백을 밑줄로 치환합니다.
 */
fun String.setUnderBar(): String {
    return this.replace(' ', '_')
}

/**
 * String에 있는 밑줄을 공백으로 치환합니다.
 */
fun String.removeUnderBar(): String {
    return this.replace('_', ' ')
}

/**
 * String에 포함된 문자 중 입력된 문자와 같은 문자를 모두 삭제합니다.
 */
fun String.remove(char: Char): String {
    return this.replace(char.toString(), "")
}

/**
 * String에 포함된 문자 중 입력된 문자열에 해당 되는 부분들을 모두 삭제합니다.
 */
fun String.remove(string: String): String {
    return this.replace(string, "")
}