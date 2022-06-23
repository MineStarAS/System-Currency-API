package kr.kro.minestar.utility.command

import kr.kro.minestar.utility.string.StringColor
import kr.kro.minestar.utility.string.script
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * [FunctionalCommand.commanding] 에서 사용되는 클래스 입니다.
 */
class CommandData(
    val sender: CommandSender, private val command: Command, private val label: String, private val args: Array<out String>,
    private val functionalCommand: FunctionalCommand,
) {
    /**
     * [CommandSender] 가 [Player] 가 아닐 경우 null 을 반환 합니다.
     */
    val player = if (sender is Player) sender
    else null

    private val plugin = functionalCommand.plugin

    private val arguments = functionalCommand.arguments

    fun String.toSender() = sender.sendMessage(this)

    val argument = argument()

    val valid = isValid()

    /**
     * args.first 와 대조하여 argument 를 가져옵니다.
     */
    private fun argument(): Argument? {
        if (args.isEmpty()) return null
        for (argument in arguments) if (argument.name == args.first()) return argument
        return null
    }

    /**
     * 플레이어에게 커맨드 사용법 리스트를 출력합니다.
     */
    fun printHowToUse() {
        "§eCommand List".script("§6$label").toSender()
        for (argument in arguments) {
            if (!argument.permission.hasPermission(sender, functionalCommand)) continue

            val split = argument.howToUse(label).split(' ')

            val colors = listOf(
                StringColor.LIME,
                StringColor.AQUA,
                StringColor.RED,
                StringColor.MAGENTA,
                StringColor.YELLOW,
            )

            var string = " "

            for ((index, s) in split.withIndex()) string += " ${colors[index % colors.size]}$s"

            string.toSender()
        }
    }

    /**
     * args 유효 및 Permission 유무를 확인합니다.
     */
    private fun isValid(): Boolean {
        if (argument == null) return true
        if (argument.isValid(args)) {
            if (argument.permission.hasPermission(sender, functionalCommand)) return true
            "You do not have permission.".script(plugin.prefix, StringColor.RED).toSender()
            return false
        } else argument.howToUse(label).script(plugin.prefix, StringColor.RED).toSender()
        return false
    }
}