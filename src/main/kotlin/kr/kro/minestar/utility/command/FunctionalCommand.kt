package kr.kro.minestar.utility.command

import kr.kro.minestar.utility.string.toPlayer
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

interface FunctionalCommand : TabExecutor {
    abstract class Arg: Argument

    /**
     * 플레이어에게 커맨드 사용법 리스트를 출력합니다.
     */
    fun printHowToUse(arguments: Array<out Argument>, player: Player, label: String, prefix: String): Boolean {
        "$prefix §6$label Command List".toPlayer(player)
        for (argument in arguments) "§e◇ ${argument.howToUse(label)}".toPlayer(player)
        return false
    }

    /**
     * args.first 와 대조하여 argument 를 가져옵니다.
     */
    fun argument(arguments: Array<out Argument>, args: Array<out String>): Argument? {
        if (args.isEmpty()) return null
        for (argument in arguments) if (argument.name == args.first()) return argument
        return null
    }
}