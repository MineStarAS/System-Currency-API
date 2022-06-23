package kr.kro.minestar.utility.command

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * [FunctionalCommand.commanding] 에서 사용되는 클래스 입니다.
 */
class TabCompleteData(
    val sender: CommandSender, private val args: Array<out String>, private val functionalCommand: FunctionalCommand,
) {
    /**
     * [CommandSender] 가 [Player] 가 아닐 경우 null 을 반환 합니다.
     */
    val player = if (sender is Player) sender
    else null

    private val arguments = functionalCommand.arguments

    val argument = argument()

    val valid = isValid()

    val lastIndex = args.lastIndex

    val last = args.lastOrNull() ?: ""

    /**
     * args.first 와 대조하여 argument 를 가져옵니다.
     */
    private fun argument(): Argument? {
        if (args.isEmpty()) return null
        for (argument in arguments) if (argument.name == args.first()) return argument
        return null
    }

    /**
     * args 유효 및 Permission 유무를 확인합니다.
     */
    private fun isValid(): Boolean {
        if (argument == null) return true
        if (argument.permission.hasPermission(sender, functionalCommand)) return true
        return false
    }
}