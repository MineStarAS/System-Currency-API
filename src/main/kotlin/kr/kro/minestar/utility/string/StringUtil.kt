package kr.kro.minestar.utility.string

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player

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