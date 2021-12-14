package kr.kro.minestar.utility.scheduler.objectes.later

import kr.kro.minestar.utility.scheduler.objectes.interfaces.RunTask
import kr.kro.minestar.utility.scheduler.objectes.now.RunPlaySound
import kr.kro.minestar.utility.sound.PlaySound
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

class RunTitle(players: Collection<Player>, val title: String, val subtitle: String, val fadeIn: Int, val stay: Int, val fadeOut: Int, val wait: Int) : RunTask {
    override val runnable: Runnable = Runnable { for (p in players) p.sendTitle(title, subtitle, fadeIn, stay, fadeOut) }
    override val delay: Long = (fadeIn + stay + fadeOut + wait).toLong()
    private var playSound: PlaySound? = null

    override fun run(pl: JavaPlugin, delay: Long): BukkitTask {
        if (playSound != null) RunPlaySound(playSound!!).run(pl, delay - this.delay)
        return Bukkit.getScheduler().runTaskLater(pl, runnable, delay - this.delay)
    }

    /**
     * run 실행 시 출력되는 소리를 설정합니다.
     */
    fun setPlaySound(playSound: PlaySound): RunTitle {
        this.playSound = playSound
        return this
    }
}