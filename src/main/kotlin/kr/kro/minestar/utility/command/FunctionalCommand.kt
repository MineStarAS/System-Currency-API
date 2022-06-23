package kr.kro.minestar.utility.command

import kr.kro.minestar.utility.main.FunctionalJavaPlugin
import kr.kro.minestar.utility.string.StringColor
import kr.kro.minestar.utility.string.script
import kr.kro.minestar.utility.string.toPlayer
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import java.io.File

interface FunctionalCommand : TabExecutor {
    abstract class Arg : Argument

    val plugin: FunctionalJavaPlugin
    val arguments: Array<out Argument>

    /**
     * true 를 반환할 경우, [Player] 의 Permission 유무 상관 없이 오직 Op 유무로만
     *
     * 구분 합니다.
     */
    fun isSimplePermission(): Boolean = false

    /**
     * [CommandSender]에게 메세지를 보냅니다.
     */
    fun String.toSender(sender: CommandSender) = sender.sendMessage(this)

    /**
     * 코드 실행 중에 올바르지 않는 경우가 생길 경우 아래 코드로 플레이어에게 알립니다.
     */
    fun String.warningScript(player: Player) = script(plugin.prefix, StringColor.RED).toPlayer(player)
    fun String.warningScript(sender: CommandSender) = script(plugin.prefix, StringColor.RED).toSender(sender)


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        commanding(CommandData(sender, command, label, args, this), args)
        return false
    }

    fun commanding(data: CommandData, args: Array<out String>)

    /**
     * [onTabComplete] 에 사용되는 기능들 입니다.
     */
    fun Collection<*>.add(list: MutableList<String>, last: String) {
        if (isEmpty()) return
        when (first()!!::class) {
            String::class -> for (element in this as Collection<String>) if (element.lowercase().contains(last)) list.add(element)
            Player::class -> for (element in this as Collection<Player>) if (element.name.lowercase().contains(last)) list.add(element.name)
            File::class -> for (element in this as Collection<File>) if (element.name.lowercase().contains(last)) list.add(element.name)

            else -> for (element in this) if (element.toString().lowercase().contains(last)) list.add(element.toString())
        }
    }

    fun Array<out Argument>.add(list: MutableList<String>, last: String) {
        for (s in this) if (s.name.lowercase().contains(last)) list.add(s.name)
    }

    fun Array<out Enum<*>>.add(list: MutableList<String>, last: String) {
        for (s in this) if (s.name.lowercase().contains(last)) list.add(s.name)
    }
}