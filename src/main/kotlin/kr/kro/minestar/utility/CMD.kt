package kr.kro.minestar.utility

import kr.kro.minestar.utility.Main.Companion.pl
import kr.kro.minestar.utility.scheduler.Scheduler
import kr.kro.minestar.utility.scheduler.objectes.later.RunLater
import kr.kro.minestar.utility.scheduler.objectes.later.RunTitle
import kr.kro.minestar.utility.scheduler.objectes.now.RunNow
import kr.kro.minestar.utility.scheduler.objectes.repeat.RunTimer
import kr.kro.minestar.utility.sound.PlaySound
import kr.kro.minestar.utility.string.toPlayerActionBar
import kr.kro.minestar.utility.time.Time
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object CMD : CommandExecutor {
    override fun onCommand(p: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        if (p !is Player) return false
        val sc = Scheduler(pl)
        val players = listOf(p)
        val sound = PlaySound().also {
            it.players = players
            it.sound = Sound.BLOCK_NOTE_BLOCK_BELL
        }
        sc.addRun(RunLater(Runnable { "test start".toPlayerActionBar(p) }, 0))
        sc.addRun(RunTitle(players, " ", "3", 5, 10, 5, 0).setPlaySound(sound.clone().also { it.pitch = 1.2F }))
        sc.addRun(RunTitle(players, " ", "2", 5, 10, 5, 0).setPlaySound(sound.clone().also { it.pitch = 1.1F }))
        sc.addRun(RunTitle(players, " ", "1", 5, 10, 5, 0).setPlaySound(sound))
        sc.addRun(RunNow {
            sound.clone().also { it.pitch = 1.5F }.play()
            val time = Time(3, 0, 0)
            time.startTimer()
            sc.addField("time", time)
        })
        sc.addRun(RunTimer(Runnable { sc.getField<Time>("time").toString().toPlayerActionBar(p) }, 0, 1, "displayingTime"))
        sc.play()
        return false
    }
}